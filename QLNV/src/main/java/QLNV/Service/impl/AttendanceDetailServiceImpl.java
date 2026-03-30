package QLNV.Service.impl;

import QLNV.DTO.response.AttendanceDetailResponse;
import QLNV.Entity.AttendanceDetail;
import QLNV.Entity.AttendanceSession;
import QLNV.Repository.AttendanceDetailRepository;
import QLNV.Repository.EmployeeRepository;
import QLNV.Repository.AttendanceSessionRepository;
import QLNV.Service.AttendanceDetailService;
import QLNV.Service.QRCodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceDetailServiceImpl implements AttendanceDetailService {

    @Autowired
    private AttendanceDetailRepository repository;

    @Autowired private AttendanceSessionRepository phienRepository;

    @Autowired
    private EmployeeRepository nhanVienRepository ;

    @Autowired
    private QRCodeGeneratorService qrService;

    private AttendanceDetailResponse mapToResponse(AttendanceDetail entity) {
        if (entity == null) return null;
        return AttendanceDetailResponse.builder()
                .id(entity.getId())
                .maNv(entity.getNhanVien() != null ? entity.getNhanVien().getMaNv() : null)
                .hoTen(entity.getNhanVien() != null ? entity.getNhanVien().getHoTen() : null)
                .ngay(entity.getNgay())
                .gioCheckIn(entity.getGioCheckIn())
                .gioCheckOut(entity.getGioCheckOut())
                .soPhutDiTre(entity.getSoPhutDiTre())
                .soPhutVeSom(entity.getSoPhutVeSom())
                .congThuong(entity.getCongThuong())
                .gioTangCa(entity.getGioTangCa())
                .loaiNgay(entity.getLoaiNgay() != null ? entity.getLoaiNgay().name() : null)
                .build();
    }



    @Override
    public List<AttendanceDetailResponse> findAll() {
        List<AttendanceDetail> entities = repository.findAll();
        List<AttendanceDetailResponse> responses = new ArrayList<>();
        for (AttendanceDetail entity : entities) {
            responses.add(mapToResponse(entity));
        }

        return responses;
    }

    @Override
    public AttendanceDetailResponse findById(Long id) {
        Optional<AttendanceDetail> entity = repository.findById(id);
        if (entity.isPresent()) {
            return mapToResponse(entity.get());
        }
        return null;
    }

    @Override
    public AttendanceDetail save(AttendanceDetail c) {
        return repository.save(c);
    }

    @Override
    public AttendanceDetailResponse quetMaDiemDanh(Long nhanVienId, String token) {
        if (token != null) token = token.replace(" ", "+");
        if (!qrService.isValidToken(token)) {
            throw new RuntimeException("Mã QR đã hết hạn hoặc không hợp lệ!");
        }

        AttendanceSession phien = phienRepository.findTopByDangHoatDongTrueOrderByNgayTaoDesc()
                .orElseThrow(() -> new RuntimeException("Không có phiên điểm danh nào mở!"));

        LocalDate homNay = LocalDate.now();
        LocalTime bayGio = LocalTime.now();

        // Tìm bản ghi chấm công của nhân viên trong ngày hôm nay
        List<AttendanceDetail> existingRecords = repository.findByNhanVien_IdAndNgay(nhanVienId, homNay);
        AttendanceDetail record;

        if (existingRecords.isEmpty()) {
            // Trường hợp chưa có bản ghi (Check-in lần đầu)
            record = new AttendanceDetail();
            record.setNhanVien(nhanVienRepository.findById(nhanVienId).get());
            record.setNgay(homNay);
            record.setGioCheckIn(bayGio);
            record.setLoaiNgay(phien.getLoaiNgay());
            record.setCongThuong(1.0f);

            if (bayGio.isAfter(phien.getGioBatDauCauHinh())) {
                long tre = Duration.between(phien.getGioBatDauCauHinh(), bayGio).toMinutes();
                record.setSoPhutDiTre((int) tre);
            } else {
                record.setSoPhutDiTre(0);
            }
        } else {
            // Trường hợp đã có bản ghi (Check-out)
            record = existingRecords.get(0);
            record.setGioCheckOut(bayGio);

            if (bayGio.isBefore(phien.getGioKetThucCauHinh())) {
                long som = Duration.between(bayGio, phien.getGioKetThucCauHinh()).toMinutes();
                record.setSoPhutVeSom((int) som);
            } else {
                record.setSoPhutVeSom(0);
                float ot = (float) Duration.between(phien.getGioKetThucCauHinh(), bayGio).toHours();
                record.setGioTangCa(ot > 0 ? ot : 0);
            }
        }

        AttendanceDetail savedEntity = repository.save(record);
        return mapToResponse(savedEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<AttendanceDetailResponse> findByNhanVienId(Long nhanVienId) {
        List<AttendanceDetail> entities = repository.findByNhanVien_Id(nhanVienId);
        List<AttendanceDetailResponse> responses = new ArrayList<>();

        for (AttendanceDetail entity : entities) {
            responses.add(mapToResponse(entity));
        }
        return responses;
    }

    @Override
    @Transactional
    public AttendanceDetailResponse update(Long id, AttendanceDetailResponse data) {

        AttendanceDetail existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bản ghi chấm công ID: " + id));

        existing.setNgay(data.getNgay());
        existing.setGioCheckIn(data.getGioCheckIn());
        existing.setGioCheckOut(data.getGioCheckOut());
        existing.setSoPhutDiTre(data.getSoPhutDiTre());
        existing.setSoPhutVeSom(data.getSoPhutVeSom());
        existing.setCongThuong(data.getCongThuong());
        existing.setGioTangCa(data.getGioTangCa());

        if (data.getLoaiNgay() != null) {
            existing.setLoaiNgay(QLNV.Entity.Enum.DayType.valueOf(data.getLoaiNgay()));
        }

        return mapToResponse(repository.save(existing));
    }
}

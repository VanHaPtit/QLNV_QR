package QLNV.Service.impl;

import QLNV.Entity.ChamCongChiTiet;
import QLNV.Entity.PhienDiemDanh;
import QLNV.Repository.ChamCongChiTietRepository;
import QLNV.Repository.NhanVienRepository;
import QLNV.Repository.PhienDiemDanhRepository;
import QLNV.Service.ChamCongChiTietService;
import QLNV.Service.QRCodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ChamCongChiTietServiceImpl implements ChamCongChiTietService {

    @Autowired
    private ChamCongChiTietRepository repository;

    @Autowired private PhienDiemDanhRepository phienRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository ;

    @Autowired
    private QRCodeGeneratorService qrService;

    @Override
    public List<ChamCongChiTiet> findAll() {
        return repository.findAll();
    }

    @Override
    public ChamCongChiTiet findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ChamCongChiTiet save(ChamCongChiTiet c) {
        return repository.save(c);
    }

    @Override
    public ChamCongChiTiet quetMaDiemDanh(Long nhanVienId, String token) {

        if (token != null) {
            token = token.replace(" ", "+");
        }

        if (!qrService.isValidToken(token)) {
            throw new RuntimeException("Mã QR đã hết hạn hoặc không hợp lệ. Vui lòng quét mã mới!");
        }

        // 1. Kiểm tra phiên đang hoạt động
        PhienDiemDanh phien = phienRepository.findTopByDangHoatDongTrueOrderByNgayTaoDesc()
                .orElseThrow(() -> new RuntimeException("Không có phiên điểm danh nào đang mở!"));

        LocalDate homNay = LocalDate.now();
        LocalTime bayGio = LocalTime.now();

        // 2. Tìm hoặc tạo mới bản ghi chấm công
        ChamCongChiTiet record = repository.findByNhanVien_IdAndNgay(nhanVienId, homNay)
                .stream().findFirst().orElse(new ChamCongChiTiet());

        if (record.getId() == null) {
            // --- XỬ LÝ CHECK-IN ---
            record.setNhanVien(nhanVienRepository.findById(nhanVienId).get());
            record.setNgay(homNay);
            record.setGioCheckIn(bayGio);
            record.setLoaiNgay(phien.getLoaiNgay());

            // Tính phút đi trễ so với giờ sếp setup
            if (bayGio.isAfter(phien.getGioBatDauCauHinh())) {
                long tre = java.time.Duration.between(phien.getGioBatDauCauHinh(), bayGio).toMinutes();
                record.setSoPhutDiTre((int) tre);
            } else {
                record.setSoPhutDiTre(0);
            }
            record.setCongThuong(1.0f);
        } else {
            // --- XỬ LÝ CHECK-OUT ---
            record.setGioCheckOut(bayGio);

            // Tính phút về sớm
            if (bayGio.isBefore(phien.getGioKetThucCauHinh())) {
                long som = java.time.Duration.between(bayGio, phien.getGioKetThucCauHinh()).toMinutes();
                record.setSoPhutVeSom((int) som);
            } else {
                record.setSoPhutVeSom(0);
                // Tính tăng ca (OT) nếu sếp quy định
                float ot = (float) java.time.Duration.between(phien.getGioKetThucCauHinh(), bayGio).toHours();
                record.setGioTangCa(ot > 0 ? ot : 0);
            }
        }
        return repository.save(record);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ChamCongChiTiet> findByNhanVienId(Long nhanVienId) {
        return repository.findByNhanVien_Id(nhanVienId);
    }
}

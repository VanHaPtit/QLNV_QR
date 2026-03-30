package QLNV.Service.impl;

import QLNV.DTO.response.EmployeeResponse;
import QLNV.DTO.response.PayrollResponse;
import QLNV.Entity.Position;
import QLNV.Entity.Employee;
import QLNV.Entity.Department;
import QLNV.Repository.PositionRepository;
import QLNV.Repository.EmployeeRepository;
import QLNV.Repository.DepartmentRepository;
import QLNV.Service.CloudinaryService;
import QLNV.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository nhanVienRepository;
    @Autowired
    private MonthlyPayrollServiceImpl monthlyPayrollService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private DepartmentRepository phongBanRepository;
    @Autowired
    private PositionRepository chucVuRepository;

    @Override
    public List<EmployeeResponse> getAll() {
        List<Employee> employees = nhanVienRepository.findAll();
        List<EmployeeResponse> responseList = new ArrayList<>();

        for (Employee nv : employees) {
            if (nv != null) {
                EmployeeResponse res = mapToEmployeeResponse(nv);
                responseList.add(res);
            }
        }

        return responseList;
    }

    @Override
    public Optional<EmployeeResponse> getById(Long id) {
        Optional<Employee> nvOpt = nhanVienRepository.findById(id);
        if (nvOpt.isPresent()) {
            Employee nv = nvOpt.get();
            EmployeeResponse res = mapToEmployeeResponse(nv);
            return Optional.of(res);
        }
        return Optional.empty();
    }

    @Override
    public EmployeeResponse save(EmployeeResponse dto, MultipartFile file) throws IOException {
        Employee nv = new Employee();
        updateEntityFromDto(nv, dto);

        if (file != null && !file.isEmpty()) {
            nv.setAvatarUrl(cloudinaryService.uploadImage(file));
        }

        handleDepartmentAndPosition(nv, dto);
        return mapToEmployeeResponse(nhanVienRepository.save(nv));
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeResponse dto, MultipartFile file) throws IOException {
        Employee existing = nhanVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));

        updateEntityFromDto(existing, dto);

        if (file != null && !file.isEmpty()) {
            existing.setAvatarUrl(cloudinaryService.uploadImage(file));
        }

        handleDepartmentAndPosition(existing, dto);
        return mapToEmployeeResponse(nhanVienRepository.save(existing));
    }

    private void updateEntityFromDto(Employee nv, EmployeeResponse dto) {
        nv.setMaNv(dto.getMaNv());
        nv.setHoTen(dto.getHoTen());
        nv.setNgaySinh(dto.getNgaySinh());
        nv.setGioiTinh(dto.getGioiTinh());
        nv.setFaceData(dto.getFaceData());
        nv.setCccd(dto.getCccd());
        nv.setNgayCap(dto.getNgayCap());
        nv.setNoiCap(dto.getNoiCap());
        nv.setDiaChiThuongTru(dto.getDiaChiThuongTru());
        nv.setDiaChiHienTai(dto.getDiaChiHienTai());
        nv.setSoDienThoai(dto.getSoDienThoai());
        nv.setEmailCongTy(dto.getEmailCongTy());
        nv.setEmailCaNhan(dto.getEmailCaNhan());
        nv.setMaSoThue(dto.getMaSoThue());
        nv.setSoTaiKhoan(dto.getSoTaiKhoan());
        nv.setNganHang(dto.getNganHang());
        if(dto.getTrangThai() != null) {
            nv.setTrangThai(QLNV.Entity.Enum.EmployeeStatus.valueOf(dto.getTrangThai()));
        }
    }

    private void handleDepartmentAndPosition(Employee nv, EmployeeResponse dto) {
        if (dto.getTenPhongBan() != null && !dto.getTenPhongBan().isEmpty()) {
            String tenPB = dto.getTenPhongBan();

            Department pb = phongBanRepository.findByTenPhongBan(tenPB)
                    .orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy phòng ban có tên: " + tenPB));
            nv.setPhongBan(pb);
        }

        if (dto.getTenChucVu() != null && !dto.getTenChucVu().isEmpty()) {
            String tenCV = dto.getTenChucVu();
            Position cv = chucVuRepository.findByTenChucVu(tenCV)
                    .orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy chức vụ có tên: " + tenCV));
            nv.setChucVu(cv);
        }
    }

    @Override
    public void delete(Long id) {
        if (!nhanVienRepository.existsById(id)) throw new RuntimeException("Không tìm thấy");
        nhanVienRepository.deleteById(id);
    }

    @Override
    public Employee findByMaNv(String maNv) {
        return nhanVienRepository.findByMaNv(maNv).orElse(null);
    }

    private EmployeeResponse mapToEmployeeResponse(Employee nv) {
        if (nv == null) return null;

        EmployeeResponse res = new EmployeeResponse();

        res.setId(nv.getId());
        res.setMaNv(nv.getMaNv());
        res.setHoTen(nv.getHoTen());
        res.setNgaySinh(nv.getNgaySinh());
        res.setGioiTinh(nv.getGioiTinh());
        res.setAvatarUrl(nv.getAvatarUrl());
        res.setFaceData(nv.getFaceData());

        res.setCccd(nv.getCccd());
        res.setNgayCap(nv.getNgayCap());
        res.setNoiCap(nv.getNoiCap());

        res.setDiaChiThuongTru(nv.getDiaChiThuongTru());
        res.setDiaChiHienTai(nv.getDiaChiHienTai());

        res.setSoDienThoai(nv.getSoDienThoai());
        res.setEmailCongTy(nv.getEmailCongTy());
        res.setEmailCaNhan(nv.getEmailCaNhan());

        res.setMaSoThue(nv.getMaSoThue());
        res.setSoTaiKhoan(nv.getSoTaiKhoan());
        res.setNganHang(nv.getNganHang());

        res.setTenPhongBan(
                nv.getPhongBan() != null ? nv.getPhongBan().getTenPhongBan() : "Chưa phân bổ"
        );

        res.setTenChucVu(
                nv.getChucVu() != null ? nv.getChucVu().getTenChucVu() : "Chưa có chức vụ"
        );

        res.setTrangThai(
                nv.getTrangThai() != null ? nv.getTrangThai().name() : "N/A"
        );

        if (nv.getBangLuongThangs() != null) {
            List<PayrollResponse> payrollResponses = nv.getBangLuongThangs()
                    .stream()
                    .map(monthlyPayrollService::mapToPayrollResponse)
                    .toList();

            res.setBangLuongThangs(payrollResponses);
        }

        return res;
    }

}


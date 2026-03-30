package QLNV.Service.impl;

import QLNV.DTO.request.LaborContractRequest;
import QLNV.DTO.response.LaborContractResponse;
import QLNV.Entity.Employee;
import QLNV.Entity.LaborContract;
import QLNV.Entity.Enum.ContractType;
import QLNV.Repository.LaborContractRepository;
import QLNV.Repository.EmployeeRepository;
import QLNV.Service.CloudinaryService;
import QLNV.Service.LaborContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class LaborContractServiceImpl implements LaborContractService {

    private final LaborContractRepository repo;
    private final EmployeeRepository nhanVienRepo;
    private final CloudinaryService cloudinaryService;

    public LaborContractServiceImpl(LaborContractRepository repo, EmployeeRepository nhanVienRepo, CloudinaryService cloudinaryService) {
        this.repo = repo;
        this.nhanVienRepo = nhanVienRepo;
        this.cloudinaryService = cloudinaryService;
    }

    // --- Helper: Map Entity to Response ---
    private LaborContractResponse mapToResponse(LaborContract hd) {
        if (hd == null) return null;

        String tinhTrang = "Còn hạn";
        LocalDate today = LocalDate.now();
        if (hd.getNgayHetHan() != null) {
            if (today.isAfter(hd.getNgayHetHan())) {
                tinhTrang = "Hết hạn";
            } else if (ChronoUnit.DAYS.between(today, hd.getNgayHetHan()) <= 30) {
                tinhTrang = "Sắp hết hạn";
            }
        }

        return LaborContractResponse.builder()
                .id(hd.getId())
                .soHopDong(hd.getSoHopDong())
                .maNv(hd.getNhanVien() != null ? hd.getNhanVien().getMaNv() : "N/A")
                .hoTen(hd.getNhanVien() != null ? hd.getNhanVien().getHoTen() : "N/A")
                .loaiHopDong(hd.getLoaiHopDong() != null ? hd.getLoaiHopDong().name() : null)
                .ngayHieuLuc(hd.getNgayHieuLuc())
                .ngayHetHan(hd.getNgayHetHan())
                .luongCoBan(hd.getLuongCoBan())
                .luongDongBhxh(hd.getLuongDongBhxh())
                .fileDinhKemUrl(hd.getFileDinhKem())
                .tinhTrangHieuLuc(tinhTrang)
                .build();
    }

    @Override
    @Transactional
    public LaborContractResponse create(LaborContractRequest request, MultipartFile file) throws IOException {
        LaborContract hd = new LaborContract();
        hd.setSoHopDong(request.getSoHopDong());
        hd.setNgayHieuLuc(request.getNgayHieuLuc());
        hd.setNgayHetHan(request.getNgayHetHan());
        hd.setLuongCoBan(request.getLuongCoBan());
        hd.setLuongDongBhxh(request.getLuongDongBhxh());
        hd.setLoaiHopDong(ContractType.valueOf(request.getLoaiHopDong()));

        Employee nv = nhanVienRepo.findById(request.getNhanVienId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        hd.setNhanVien(nv);

        if (file != null && !file.isEmpty()) {
            hd.setFileDinhKem(cloudinaryService.uploadImage(file));
        }

        return mapToResponse(repo.save(hd));
    }

    @Override
    @Transactional
    public LaborContractResponse update(Long id, LaborContractRequest request, MultipartFile file) throws IOException {
        LaborContract hd = repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng"));

        hd.setSoHopDong(request.getSoHopDong());
        hd.setNgayHieuLuc(request.getNgayHieuLuc());
        hd.setNgayHetHan(request.getNgayHetHan());
        hd.setLuongCoBan(request.getLuongCoBan());
        hd.setLuongDongBhxh(request.getLuongDongBhxh());
        hd.setLoaiHopDong(ContractType.valueOf(request.getLoaiHopDong()));

        if (file != null && !file.isEmpty()) {
            hd.setFileDinhKem(cloudinaryService.uploadImage(file));
        }

        return mapToResponse(repo.save(hd));
    }

    @Override
    public List<LaborContractResponse> getAll() {
        List<LaborContract> entities = repo.findAll();
        List<LaborContractResponse> responses = new ArrayList<>();

        // Sử dụng vòng lặp for thay vì map
        for (LaborContract hd : entities) {
            responses.add(mapToResponse(hd));
        }
        return responses;
    }

    @Override
    public List<LaborContractResponse> getByNhanVienId(Long nhanVienId) {
        List<LaborContract> entities = repo.findByNhanVien_Id(nhanVienId);
        List<LaborContractResponse> responses = new ArrayList<>();

        for (LaborContract hd : entities) {
            responses.add(mapToResponse(hd));
        }
        return responses;
    }

    @Override
    public LaborContractResponse getById(Long id) {
        return mapToResponse(repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng")));
    }

    @Override
    public void delete(Long id) { repo.deleteById(id); }
}
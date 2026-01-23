package QLNV.Service.impl;

import QLNV.Entity.LaborContract;
import QLNV.Repository.LaborContractRepository;
import QLNV.Service.CloudinaryService; // Import CloudinaryService
import QLNV.Service.LaborContractService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class LaborContractServiceImpl implements LaborContractService {

    private final LaborContractRepository repo;
    private final CloudinaryService cloudinaryService; // 1. Khai báo service upload

    // 2. Inject qua Constructor
    public LaborContractServiceImpl(LaborContractRepository repo, CloudinaryService cloudinaryService) {
        this.repo = repo;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public LaborContract create(LaborContract hopDong, MultipartFile file) throws IOException {
        // 3. Nếu có file thì upload và lấy URL set vào entity
        if (file != null && !file.isEmpty()) {
            String url = cloudinaryService.uploadImage(file);
            hopDong.setFileDinhKem(url);
        }
        return repo.save(hopDong);
    }

    @Override
    public LaborContract update(Long id, LaborContract hopDong, MultipartFile file) throws IOException {
        LaborContract hd = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng"));

        // Cập nhật các thông tin cơ bản
        hd.setSoHopDong(hopDong.getSoHopDong());
        hd.setNhanVien(hopDong.getNhanVien());
        hd.setLoaiHopDong(hopDong.getLoaiHopDong());
        hd.setNgayHieuLuc(hopDong.getNgayHieuLuc());
        hd.setNgayHetHan(hopDong.getNgayHetHan());
        hd.setLuongCoBan(hopDong.getLuongCoBan());
        hd.setLuongDongBhxh(hopDong.getLuongDongBhxh());

        // 4. Xử lý file khi update:
        // - Nếu có file mới -> Upload file mới -> Cập nhật URL mới
        // - Nếu không có file mới -> Giữ nguyên URL cũ (không làm gì cả)
        if (file != null && !file.isEmpty()) {
            String url = cloudinaryService.uploadImage(file);
            hd.setFileDinhKem(url);
        }
        // Lưu ý: Nếu muốn xóa file cũ trên Cloudinary thì cần thêm logic delete ở đây trước khi set mới.

        return repo.save(hd);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public LaborContract getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng"));
    }

    @Override
    public List<LaborContract> getAll() {
        return repo.findAll();
    }

    @Override
    public List<LaborContract> getByNhanVienId(Long nhanVienId) {
        return repo.findByNhanVien_Id(nhanVienId);
    }
}
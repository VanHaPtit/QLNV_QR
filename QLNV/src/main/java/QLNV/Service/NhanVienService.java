package QLNV.Service;

import QLNV.Entity.NhanVien;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NhanVienService {

    List<NhanVien> getAll();
    Optional<NhanVien> getById(Long id);
    NhanVien save(NhanVien nv, MultipartFile file) throws IOException;
    NhanVien update(Long id, NhanVien nv, MultipartFile file) throws IOException;
    void delete(Long id);

    // Tìm kiếm
    NhanVien findByMaNv(String maNv);
}


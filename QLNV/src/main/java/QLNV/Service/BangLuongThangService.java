package QLNV.Service;

import QLNV.Entity.BangLuongThang;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BangLuongThangService {

    List<BangLuongThang> getAll();

    Optional<BangLuongThang> getById(Long id);

    BangLuongThang save(BangLuongThang bangLuongThang);

    BangLuongThang update(Long id, BangLuongThang data);

    void delete(Long id);

    List<BangLuongThang> findByNhanVien(Long nvId);

    List<BangLuongThang> findByThangNam(Integer thang, Integer nam);

    List<BangLuongThang> findByNhanVienAndThangNam(Long nvId, Integer thang, Integer nam);
    public void importFromExcel(MultipartFile file) throws Exception ;


}

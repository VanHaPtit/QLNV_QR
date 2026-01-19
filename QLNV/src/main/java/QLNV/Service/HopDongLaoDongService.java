package QLNV.Service;

import QLNV.Entity.HopDongLaoDong;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface HopDongLaoDongService {
    // Thêm tham số MultipartFile và ném ngoại lệ IOException
    HopDongLaoDong create(HopDongLaoDong hopDong, MultipartFile file) throws IOException;

    HopDongLaoDong update(Long id, HopDongLaoDong hopDong, MultipartFile file) throws IOException;

    void delete(Long id);
    HopDongLaoDong getById(Long id);
    List<HopDongLaoDong> getAll();
    List<HopDongLaoDong> getByNhanVienId(Long nhanVienId);
}
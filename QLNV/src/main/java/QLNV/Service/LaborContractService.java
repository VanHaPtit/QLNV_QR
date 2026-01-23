package QLNV.Service;

import QLNV.Entity.LaborContract;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface LaborContractService {
    // Thêm tham số MultipartFile và ném ngoại lệ IOException
    LaborContract create(LaborContract hopDong, MultipartFile file) throws IOException;

    LaborContract update(Long id, LaborContract hopDong, MultipartFile file) throws IOException;

    void delete(Long id);
    LaborContract getById(Long id);
    List<LaborContract> getAll();
    List<LaborContract> getByNhanVienId(Long nhanVienId);
}
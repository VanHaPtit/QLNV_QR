package QLNV.Service;

import QLNV.Entity.ChucVu;

import java.util.List;
import java.util.Optional;

public interface ChucVuService {
    List<ChucVu> getAll();

    Optional<ChucVu> getChucVu(Long id);

    ChucVu saveChucVu(ChucVu chucVu);

    void deleteChucVu(Long id);

    ChucVu updateChucVu(Long id, ChucVu data);

    List<ChucVu> searchChucVu(String keyword);
}

package QLNV.Repository;

import QLNV.Entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChucVuRepository extends JpaRepository<ChucVu, Long> {
    List<ChucVu> findByTenChucVuContainingIgnoreCase(String keyword);
}

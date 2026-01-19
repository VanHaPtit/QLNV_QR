package QLNV.Repository;

import QLNV.Entity.HopDongLaoDong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopDongLaoDongRepository extends JpaRepository<HopDongLaoDong, Long> {
    List<HopDongLaoDong> findByNhanVien_Id(Long nhanVienId);
}

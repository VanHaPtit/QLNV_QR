package QLNV.Repository;

import QLNV.Entity.BangLuongThang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BangLuongThangRepository extends JpaRepository<BangLuongThang, Long> {

    List<BangLuongThang> findByNam(Integer nam);

    List<BangLuongThang> findByThang(Integer thang);

    List<BangLuongThang> findByNhanVienId(Long nhanVienId);

    List<BangLuongThang> findByNhanVienIdAndThangAndNam(Long nvId, Integer thang, Integer nam);
}

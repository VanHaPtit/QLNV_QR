package QLNV.Repository;

import QLNV.Entity.ChamCongChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ChamCongChiTietRepository extends JpaRepository<ChamCongChiTiet, Long> {

    List<ChamCongChiTiet> findByNhanVien_Id(Long nhanVienId);

    List<ChamCongChiTiet> findByNhanVien_IdAndNgay(Long nhanVienId, LocalDate ngay);
}

package QLNV.Repository;

import QLNV.Entity.NhanVien;
import QLNV.Entity.QuyPhepNam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuyPhepNamRepository extends JpaRepository<QuyPhepNam, Long> {
    // Tìm theo năm
    List<QuyPhepNam> findByNam(Integer nam);

    // Tìm theo nhân viên
    List<QuyPhepNam> findByNhanVienId(Long nhanVienId);

    // Tìm theo nhân viên và năm
    List<QuyPhepNam> findByNhanVienIdAndNam(Long nhanVienId, Integer nam);
}

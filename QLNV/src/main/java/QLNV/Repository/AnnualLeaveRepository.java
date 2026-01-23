package QLNV.Repository;

import QLNV.Entity.AnnualLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnualLeaveRepository extends JpaRepository<AnnualLeave, Long> {
    // Tìm theo năm
    List<AnnualLeave> findByNam(Integer nam);

    // Tìm theo nhân viên
    List<AnnualLeave> findByNhanVienId(Long nhanVienId);

    // Tìm theo nhân viên và năm
    List<AnnualLeave> findByNhanVienIdAndNam(Long nhanVienId, Integer nam);
}

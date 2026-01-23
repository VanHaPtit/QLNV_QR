package QLNV.Repository;

import QLNV.Entity.MonthlyPayroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyPayrollRepository extends JpaRepository<MonthlyPayroll, Long> {

    List<MonthlyPayroll> findByNam(Integer nam);

    List<MonthlyPayroll> findByThang(Integer thang);

    List<MonthlyPayroll> findByNhanVienId(Long nhanVienId);

    List<MonthlyPayroll> findByNhanVienIdAndThangAndNam(Long nvId, Integer thang, Integer nam);
}

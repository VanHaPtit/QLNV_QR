package QLNV.Service;

import QLNV.Entity.MonthlyPayroll;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MonthlyPayrollService {

    List<MonthlyPayroll> getAll();

    Optional<MonthlyPayroll> getById(Long id);

    MonthlyPayroll save(MonthlyPayroll bangLuongThang);

    MonthlyPayroll update(Long id, MonthlyPayroll data);

    void delete(Long id);

    List<MonthlyPayroll> findByNhanVien(Long nvId);

    List<MonthlyPayroll> findByThangNam(Integer thang, Integer nam);

    List<MonthlyPayroll> findByNhanVienAndThangNam(Long nvId, Integer thang, Integer nam);
    public void importFromExcel(MultipartFile file) throws Exception ;


}

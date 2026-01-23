package QLNV.Service;

import QLNV.Entity.EmployeeAllowance;

import java.util.List;

public interface EmployeeAllowanceService {
    List<EmployeeAllowance> getAll();
    EmployeeAllowance getById(Long id);
    EmployeeAllowance create(EmployeeAllowance pc);
    EmployeeAllowance update(Long id, EmployeeAllowance pc);
    void delete(Long id);

    List<EmployeeAllowance> findByHopDong(Long hopDongId);
    List<EmployeeAllowance> findByPhuCap(Long phuCapId);
}

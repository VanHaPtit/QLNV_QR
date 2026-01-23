package QLNV.Service;

import QLNV.Entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    public List<Department> getAll() ;
    public Optional<Department> getPhongBan(Long id) ;
    public Department savePhongBan(Department phongBan) ;
    public void deletePhongBan(Long id) ;
    Department updatePhongBan(Long id, Department data);
    public List<Department> searchPhongBan(String keyword) ;
}

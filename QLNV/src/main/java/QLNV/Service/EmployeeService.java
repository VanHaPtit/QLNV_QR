package QLNV.Service;

import QLNV.Entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAll();
    Optional<Employee> getById(Long id);
    Employee save(Employee nv, MultipartFile file) throws IOException;
    Employee update(Long id, Employee nv, MultipartFile file) throws IOException;
    void delete(Long id);

    // Tìm kiếm
    Employee findByMaNv(String maNv);
}


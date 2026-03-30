package QLNV.Service;

import QLNV.DTO.response.EmployeeResponse;
import QLNV.Entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeResponse> getAll();
    Optional<EmployeeResponse> getById(Long id);
    EmployeeResponse save(EmployeeResponse nvDto, MultipartFile file) throws IOException;
    EmployeeResponse update(Long id, EmployeeResponse nvDto, MultipartFile file) throws IOException;
    void delete(Long id);
    Employee findByMaNv(String maNv);
}


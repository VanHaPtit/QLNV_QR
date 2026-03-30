package QLNV.Controller;

import QLNV.DTO.response.EmployeeResponse;
import QLNV.Entity.Employee;
import QLNV.Service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nhanvien")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        Optional<EmployeeResponse> nvOpt = service.getById(id);

        if (nvOpt.isPresent()) {
            EmployeeResponse data = nvOpt.get();
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> add(
            @RequestPart("nhanVien") String nhanVienJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            EmployeeResponse dto = objectMapper.readValue(nhanVienJson, EmployeeResponse.class);
            EmployeeResponse savedNv = service.save(dto, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNv);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Lỗi xử lý dữ liệu: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestPart("nhanVien") String nhanVienJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            EmployeeResponse dto = objectMapper.readValue(nhanVienJson, EmployeeResponse.class);
            EmployeeResponse updatedNv = service.update(id, dto, file);
            return ResponseEntity.ok(updatedNv);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Lỗi cập nhật: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Đã xoá nhân viên có ID = " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search/{maNv}")
    public ResponseEntity<Employee> findByMaNv(@PathVariable String maNv) {
        Employee nv = service.findByMaNv(maNv);
        return nv != null ? ResponseEntity.ok(nv) : ResponseEntity.notFound().build();
    }
}
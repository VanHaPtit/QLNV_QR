package QLNV.Controller;

import QLNV.Entity.LaborContract;
import QLNV.Service.LaborContractService;
import com.fasterxml.jackson.databind.ObjectMapper; // Cần cái này để parse JSON
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/hop-dong")
@CrossOrigin(origins = "*")
public class LaborContractController {

    private final LaborContractService service;

    // Inject ObjectMapper của Spring để chuyển String JSON thành Object Java
    @Autowired
    private ObjectMapper objectMapper;

    public LaborContractController(LaborContractService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(
            @RequestPart("hopDong") String hopDongJson, // Nhận chuỗi JSON
            @RequestPart(value = "file", required = false) MultipartFile file // Nhận file (có thể null)
    ) {
        try {
            // Chuyển chuỗi JSON thành Object HopDongLaoDong
            LaborContract hopDong = objectMapper.readValue(hopDongJson, LaborContract.class);

            // Gọi service xử lý
            LaborContract newHd = service.create(hopDong, file);
            return ResponseEntity.ok(newHd);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Lỗi xử lý dữ liệu: " + e.getMessage());
        }
    }

    // 2. PUT UPDATE
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestPart("hopDong") String hopDongJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            LaborContract hopDong = objectMapper.readValue(hopDongJson, LaborContract.class);

            LaborContract updatedHd = service.update(id, hopDong, file);
            return ResponseEntity.ok(updatedHd);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Lỗi cập nhật: " + e.getMessage());
        }
    }

    // --- Các API GET/DELETE giữ nguyên ---

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public LaborContract getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<LaborContract> getAll() {
        return service.getAll();
    }

    @GetMapping("/nhan-vien/{nhanVienId}")
    public List<LaborContract> getByNhanVien(@PathVariable Long nhanVienId) {
        return service.getByNhanVienId(nhanVienId);
    }
}
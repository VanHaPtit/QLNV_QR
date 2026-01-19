package QLNV.Controller;

import QLNV.Entity.NhanVien;
import QLNV.Service.NhanVienService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nhanvien")
@CrossOrigin(origins = "*")
public class NhanVienController {

    @Autowired
    private NhanVienService service;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("/all")

    public List<NhanVien> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")

    public NhanVien getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> add(
            @RequestPart("nhanVien") String nhanVienJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            NhanVien nv = objectMapper.readValue(nhanVienJson, NhanVien.class);

            List<NhanVien> nhanVienList = service.getAll();

            for (NhanVien x : nhanVienList) {

                if (nv.getMaNv() != null && x.getMaNv() != null &&
                        x.getMaNv().equals(nv.getMaNv())) {
                    return ResponseEntity
                            .status(HttpStatus.CONFLICT)
                            .body("Mã nhân viên đã tồn tại");
                }

                if (nv.getCccd() != null && x.getCccd() != null &&
                        x.getCccd().equals(nv.getCccd())) {
                    return ResponseEntity
                            .status(HttpStatus.CONFLICT)
                            .body("CCCD đã tồn tại");
                }

                if (nv.getMaSoThue() != null && x.getMaSoThue() != null &&
                        x.getMaSoThue().equals(nv.getMaSoThue())) {
                    return ResponseEntity
                            .status(HttpStatus.CONFLICT)
                            .body("Mã số thuế đã tồn tại");
                }
            }

            NhanVien savedNv = service.save(nv, file);
            return ResponseEntity.ok(savedNv);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Lỗi xử lý dữ liệu: " + e.getMessage());
        }
    }

    @PostMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestPart("nhanVien") String nhanVienJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            // SỬA: Dùng biến objectMapper đã được Autowired
            NhanVien nv = objectMapper.readValue(nhanVienJson, NhanVien.class);

            NhanVien updatedNv = service.update(id, nv, file);
            return ResponseEntity.ok(updatedNv);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Lỗi cập nhật: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")

    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Đã xoá nhân viên có ID = " + id;
    }

    @GetMapping("/search/{maNv}")
    public NhanVien findByMaNv(@PathVariable String maNv) {
        return service.findByMaNv(maNv);
    }
}
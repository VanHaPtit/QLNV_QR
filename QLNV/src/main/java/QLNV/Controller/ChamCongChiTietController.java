package QLNV.Controller;


import QLNV.Entity.ChamCongChiTiet;
import QLNV.Service.ChamCongChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cham-cong-chi-tiet")
@CrossOrigin("*")
public class ChamCongChiTietController {

    @Autowired
    private ChamCongChiTietService service;

    // ✔ Get all
    @GetMapping
    public ResponseEntity<List<ChamCongChiTiet>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // ✔ Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ChamCongChiTiet c = service.findById(id);
        if (c == null) {
            return ResponseEntity.badRequest().body("Không tìm thấy bản ghi!");
        }
        return ResponseEntity.ok(c);
    }

    // ✔ Create
    @PostMapping
    public ResponseEntity<ChamCongChiTiet> create(@RequestBody ChamCongChiTiet c) {
        return ResponseEntity.ok(service.save(c));
    }

    // ✔ Update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ChamCongChiTiet c) {
        ChamCongChiTiet existing = service.findById(id);
        if (existing == null) {
            return ResponseEntity.badRequest().body("Không tồn tại bản ghi để cập nhật!");
        }

        c.setId(id);
        return ResponseEntity.ok(service.save(c));
    }

    // ✔ Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ChamCongChiTiet existing = service.findById(id);
        if (existing == null) {
            return ResponseEntity.badRequest().body("Không có bản ghi để xóa!");
        }

        service.delete(id);
        return ResponseEntity.ok("Đã xóa thành công!");
    }

    // ✔ Get theo nhân viên (optional)
    @GetMapping("/nhan-vien/{nhanVienId}")
    public ResponseEntity<List<ChamCongChiTiet>> findByNhanVien(@PathVariable Long nhanVienId) {
        return ResponseEntity.ok(service.findByNhanVienId(nhanVienId));
    }
}


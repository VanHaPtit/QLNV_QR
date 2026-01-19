package QLNV.Controller;

import QLNV.Entity.BangLuongThang;
import QLNV.Service.BangLuongThangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bangluong")
@CrossOrigin(origins = "*")
public class BangLuongThangController {

    @Autowired
    private BangLuongThangService service;

    @GetMapping("/all")
    public List<BangLuongThang> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BangLuongThang getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping("/create")
    public BangLuongThang create(@RequestBody BangLuongThang bangLuongThang) {
        return service.save(bangLuongThang);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> importExcel(@RequestParam("file") MultipartFile file) {
        // 1. Kiểm tra file trống
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Vui lòng chọn một file Excel để tải lên.");
        }

        try {
            // 2. Gọi service xử lý (Hàm này có throws Exception nên cần nằm trong try)
            service.importFromExcel(file);

            // 3. Trả về thành công
            return ResponseEntity.ok(Map.of(
                    "message", "Import dữ liệu bảng lương thành công!",
                    "fileName", file.getOriginalFilename()
            ));

        } catch (Exception e) {
            // 4. Xử lý khi có lỗi xảy ra trong quá trình đọc file hoặc lưu DB
            e.printStackTrace(); // In lỗi ra console để debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi hệ thống khi xử lý file: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public BangLuongThang update(@PathVariable Long id, @RequestBody BangLuongThang data) {
        return service.update(id, data);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted!";
    }

    @GetMapping("/nv/{nvId}")
    public List<BangLuongThang> getByNhanVien(@PathVariable Long nvId) {
        return service.findByNhanVien(nvId);
    }

    @GetMapping("/filter")
    public List<BangLuongThang> filter(
            @RequestParam(required = false) Long nvId,
            @RequestParam Integer thang,
            @RequestParam Integer nam
    ) {
        return service.findByNhanVienAndThangNam(nvId, thang, nam);
    }
}

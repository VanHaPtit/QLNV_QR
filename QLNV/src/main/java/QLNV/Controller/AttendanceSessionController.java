package QLNV.Controller;

import QLNV.Entity.AttendanceDetail;
import QLNV.Entity.AttendanceSession;
import QLNV.Repository.AttendanceSessionRepository;
import QLNV.Service.AttendanceDetailService;
import QLNV.Service.QRCodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/attendance") // Nên có để phân biệt route
@CrossOrigin("*")
public class AttendanceSessionController {
    @Autowired
    private AttendanceSessionRepository phienRepo;
    @Autowired private QRCodeGeneratorService qrService;

    @Autowired
    private AttendanceDetailService chamCongService;
    // Sếp bấm nút "Tạo phiên mới" gọi vào đây
    @PostMapping("/start")
    public ResponseEntity<?> startSession(@RequestBody AttendanceSession config) {
        // Tắt các phiên cũ
        phienRepo.findAll().forEach(p -> { p.setDangHoatDong(false); phienRepo.save(p); });

        config.setNgayTao(LocalDate.now());
        config.setDangHoatDong(true);
        return ResponseEntity.ok(phienRepo.save(config));
    }

    // React gọi API này mỗi 7 giây để lấy hình ảnh mã QR mới
    @GetMapping("/get-qr")
    public ResponseEntity<String> getDynamicQR() throws Exception {
        return ResponseEntity.ok(qrService.getQRCodeBase64()); // Trả về chuỗi Base64
    }

    @PostMapping("/scan")
    public ResponseEntity<?> employeeScan(@RequestParam Long nhanVienId, @RequestParam String token) {
        try {
            AttendanceDetail result = chamCongService.quetMaDiemDanh(nhanVienId, token);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
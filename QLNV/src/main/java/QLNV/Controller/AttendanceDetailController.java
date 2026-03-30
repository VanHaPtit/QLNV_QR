package QLNV.Controller;


import QLNV.DTO.response.ApiResponse;
import QLNV.DTO.response.AttendanceDetailResponse;
import QLNV.Entity.AttendanceDetail;
import QLNV.Service.AttendanceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cham-cong-chi-tiet")
@CrossOrigin("*")
public class AttendanceDetailController {

    @Autowired
    private AttendanceDetailService service;

    @GetMapping
    public ApiResponse<List<AttendanceDetailResponse>> getAll() {
        List<AttendanceDetailResponse> list = service.findAll();
        return ApiResponse.success(list);
    }

    @GetMapping("/{id}")
    public ApiResponse<AttendanceDetailResponse> getById(@PathVariable Long id) {
        AttendanceDetailResponse res = service.findById(id);
        if (res == null) {
            return ApiResponse.error(404, "Không tìm thấy bản ghi chấm công ID: " + id);
        }
        return ApiResponse.success(res);
    }

    @PostMapping
    public ResponseEntity<AttendanceDetail> create(@RequestBody AttendanceDetail c) {
        return ResponseEntity.ok(service.save(c));
    }

    @PutMapping("/{id}")
    public ApiResponse<AttendanceDetailResponse> update(
            @PathVariable Long id,
            @RequestBody AttendanceDetailResponse data) {
        try {
            AttendanceDetailResponse updated = service.update(id, data);
            return ApiResponse.success(updated);
        } catch (Exception e) {
            return ApiResponse.error(400, "Cập nhật thất bại: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        AttendanceDetailResponse existing = service.findById(id);
        if (existing == null) {
            return ApiResponse.error(404, "Không có bản ghi để xóa!");
        }
        service.delete(id);
        return ApiResponse.success("Đã xóa bản ghi chấm công thành công!");
    }

    @GetMapping("/nhan-vien/{nhanVienId}")
    public ApiResponse<List<AttendanceDetailResponse>> findByNhanVien(@PathVariable Long nhanVienId) {
        List<AttendanceDetailResponse> list = service.findByNhanVienId(nhanVienId);
        return ApiResponse.success(list);
    }


    @PostMapping("/scan")
    public ApiResponse<AttendanceDetailResponse> scan(
            @RequestParam Long nhanVienId,
            @RequestParam String token) {
        try {
            AttendanceDetailResponse res = service.quetMaDiemDanh(nhanVienId, token);
            return ApiResponse.success(res);
        } catch (Exception e) {
            return ApiResponse.error(400, e.getMessage());
        }
    }

}


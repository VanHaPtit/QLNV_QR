package QLNV.Controller;

import QLNV.Entity.LeaveRequest;
import QLNV.Entity.Enum.RequestStatus;
import QLNV.Service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/don-xin-phep")
@CrossOrigin("*")
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    @GetMapping
    public List<LeaveRequest> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public LeaveRequest getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public LeaveRequest create(@RequestBody LeaveRequest data) {
        return service.create(data);
    }

    @PutMapping("/{id}")
    public LeaveRequest update(@PathVariable Long id, @RequestBody LeaveRequest data) {
        return service.update(id, data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // --- Filter ---
    @GetMapping("/nhan-vien/{nvId}")
    public List<LeaveRequest> findByNhanVien(@PathVariable Long nvId) {
        return service.findByNhanVien(nvId);
    }

    @GetMapping("/trang-thai/{status}")
    public List<LeaveRequest> findByTrangThai(@PathVariable RequestStatus status) {
        return service.findByTrangThai(status);
    }

    // --- Duyệt đơn ---
    @PutMapping("/{id}/approve/{nguoiDuyetId}")
    public LeaveRequest approve(@PathVariable Long id, @PathVariable Long nguoiDuyetId) {
        return service.approveDon(id, nguoiDuyetId);
    }

    @PutMapping("/{id}/reject/{nguoiDuyetId}")
    public LeaveRequest reject(@PathVariable Long id, @PathVariable Long nguoiDuyetId) {
        return service.rejectDon(id, nguoiDuyetId);
    }
}

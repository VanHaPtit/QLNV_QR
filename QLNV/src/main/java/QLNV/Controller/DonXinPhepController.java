package QLNV.Controller;

import QLNV.Entity.DonXinPhep;
import QLNV.Entity.Enum.TrangThaiDon;
import QLNV.Service.DonXinPhepService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/don-xin-phep")
@CrossOrigin("*")
public class DonXinPhepController {

    private final DonXinPhepService service;

    public DonXinPhepController(DonXinPhepService service) {
        this.service = service;
    }

    @GetMapping
    public List<DonXinPhep> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DonXinPhep getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public DonXinPhep create(@RequestBody DonXinPhep data) {
        return service.create(data);
    }

    @PutMapping("/{id}")
    public DonXinPhep update(@PathVariable Long id, @RequestBody DonXinPhep data) {
        return service.update(id, data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // --- Filter ---
    @GetMapping("/nhan-vien/{nvId}")
    public List<DonXinPhep> findByNhanVien(@PathVariable Long nvId) {
        return service.findByNhanVien(nvId);
    }

    @GetMapping("/trang-thai/{status}")
    public List<DonXinPhep> findByTrangThai(@PathVariable TrangThaiDon status) {
        return service.findByTrangThai(status);
    }

    // --- Duyệt đơn ---
    @PutMapping("/{id}/approve/{nguoiDuyetId}")
    public DonXinPhep approve(@PathVariable Long id, @PathVariable Long nguoiDuyetId) {
        return service.approveDon(id, nguoiDuyetId);
    }

    @PutMapping("/{id}/reject/{nguoiDuyetId}")
    public DonXinPhep reject(@PathVariable Long id, @PathVariable Long nguoiDuyetId) {
        return service.rejectDon(id, nguoiDuyetId);
    }
}

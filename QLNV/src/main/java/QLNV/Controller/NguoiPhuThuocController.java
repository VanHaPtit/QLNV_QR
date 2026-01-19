package QLNV.Controller;

import QLNV.Entity.NguoiPhuThuoc;
import QLNV.Service.NguoiPhuThuocService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nguoi-phu-thuoc")
@CrossOrigin("*")
public class NguoiPhuThuocController {

    private final NguoiPhuThuocService service;

    public NguoiPhuThuocController(NguoiPhuThuocService service) {
        this.service = service;
    }

    @GetMapping
    public List<NguoiPhuThuoc> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public NguoiPhuThuoc getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public NguoiPhuThuoc create(@RequestBody NguoiPhuThuoc data) {
        return service.create(data);
    }

    @PutMapping("/{id}")
    public NguoiPhuThuoc update(@PathVariable Long id, @RequestBody NguoiPhuThuoc data) {
        return service.update(id, data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ------- API tìm kiếm -------
    @GetMapping("/nhan-vien/{nvId}")
    public List<NguoiPhuThuoc> findByNhanVien(@PathVariable Long nvId) {
        return service.findByNhanVien(nvId);
    }

    @GetMapping("/mst/{mst}")
    public List<NguoiPhuThuoc> findByMST(@PathVariable String mst) {
        return service.findByMaSoThue(mst);
    }
}

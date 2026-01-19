package QLNV.Controller;

import QLNV.Entity.NhanVienPhuCap;
import QLNV.Service.NhanVienPhuCapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phu-cap-nhan-vien")
@CrossOrigin(origins = "*")
public class NhanVienPhuCapController {

    private final NhanVienPhuCapService service;

    public NhanVienPhuCapController(NhanVienPhuCapService service) {
        this.service = service;
    }

    @GetMapping
    public List<NhanVienPhuCap> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public NhanVienPhuCap getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public NhanVienPhuCap create(@RequestBody NhanVienPhuCap pc) {
        return service.create(pc);
    }

    @PutMapping("/{id}")
    public NhanVienPhuCap update(@PathVariable Long id, @RequestBody NhanVienPhuCap pc) {
        return service.update(id, pc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ======= TÌM KIẾM ==========
    @GetMapping("/hop-dong/{hopDongId}")
    public List<NhanVienPhuCap> findByHopDong(@PathVariable Long hopDongId) {
        return service.findByHopDong(hopDongId);
    }

    @GetMapping("/phu-cap/{phuCapId}")
    public List<NhanVienPhuCap> findByPhuCap(@PathVariable Long phuCapId) {
        return service.findByPhuCap(phuCapId);
    }
}

package QLNV.Controller;

import QLNV.Entity.QuaTrinhCongTac;
import QLNV.Service.QuaTrinhCongTacService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qua-trinh-cong-tac")
@CrossOrigin(origins = "*")
public class QuaTrinhCongTacController {

    private final QuaTrinhCongTacService service;

    public QuaTrinhCongTacController(QuaTrinhCongTacService service) {
        this.service = service;
    }

    @GetMapping
    public List<QuaTrinhCongTac> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public QuaTrinhCongTac getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public QuaTrinhCongTac create(@RequestBody QuaTrinhCongTac qtct) {
        return service.create(qtct);
    }

    @PutMapping("/{id}")
    public QuaTrinhCongTac update(@PathVariable Long id, @RequestBody QuaTrinhCongTac qtct) {
        return service.update(id, qtct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/nhan-vien/{nvId}")
    public List<QuaTrinhCongTac> findByNhanVien(@PathVariable Long nvId) {
        return service.findByNhanVien(nvId);
    }
}

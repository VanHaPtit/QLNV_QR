package QLNV.Controller;

import QLNV.Entity.CauHinhPhuCap;
import QLNV.Service.CauHinhPhuCapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phu-cap")
@CrossOrigin("*")
public class CauHinhPhuCapController {

    private final CauHinhPhuCapService service;

    public CauHinhPhuCapController(CauHinhPhuCapService service) {
        this.service = service;
    }

    @GetMapping
    public List<CauHinhPhuCap> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CauHinhPhuCap getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public CauHinhPhuCap create(@RequestBody CauHinhPhuCap phuCap) {
        return service.create(phuCap);
    }

    @PutMapping("/{id}")
    public CauHinhPhuCap update(@PathVariable Long id, @RequestBody CauHinhPhuCap phuCap) {
        return service.update(id, phuCap);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

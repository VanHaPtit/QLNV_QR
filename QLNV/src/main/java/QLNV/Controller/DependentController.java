package QLNV.Controller;

import QLNV.Entity.Dependent;
import QLNV.Service.DependentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nguoi-phu-thuoc")
@CrossOrigin("*")
public class DependentController {

    private final DependentService service;

    public DependentController(DependentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Dependent> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Dependent getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Dependent create(@RequestBody Dependent data) {
        return service.create(data);
    }

    @PutMapping("/{id}")
    public Dependent update(@PathVariable Long id, @RequestBody Dependent data) {
        return service.update(id, data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ------- API tìm kiếm -------
    @GetMapping("/nhan-vien/{nvId}")
    public List<Dependent> findByNhanVien(@PathVariable Long nvId) {
        return service.findByNhanVien(nvId);
    }

    @GetMapping("/mst/{mst}")
    public List<Dependent> findByMST(@PathVariable String mst) {
        return service.findByMaSoThue(mst);
    }
}

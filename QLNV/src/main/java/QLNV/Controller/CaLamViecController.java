package QLNV.Controller;

import QLNV.Entity.CaLamViec;
import QLNV.Service.CaLamViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calamviec")
@CrossOrigin(origins = "*")
public class CaLamViecController {

    @Autowired
    private CaLamViecService service;

    @GetMapping("/all")
    public List<CaLamViec> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CaLamViec getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping("/add")
    public CaLamViec add(@RequestBody CaLamViec caLamViec) {
        return service.save(caLamViec);
    }

    @PutMapping("/update/{id}")
    public CaLamViec update(@PathVariable Long id, @RequestBody CaLamViec caLamViec) {
        return service.update(id, caLamViec);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Đã xoá ca làm việc có id = " + id;
    }
}


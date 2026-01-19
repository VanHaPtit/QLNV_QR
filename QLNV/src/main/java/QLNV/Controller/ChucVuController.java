package QLNV.Controller;

import QLNV.Entity.ChucVu;
import QLNV.Service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chucvu")
@CrossOrigin(origins = "*")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/list")
    public List<ChucVu> getAll() {
        return chucVuService.getAll();
    }

    @GetMapping("/{id}")
    public ChucVu getById(@PathVariable Long id) {
        return chucVuService.getChucVu(id).orElse(null);
    }

    @PostMapping("/create")
    public ChucVu create(@RequestBody ChucVu cv) {
        return chucVuService.saveChucVu(cv);
    }

    @PutMapping("/update/{id}")
    public ChucVu update(@PathVariable Long id, @RequestBody ChucVu cv) {
        return chucVuService.updateChucVu(id, cv);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        chucVuService.deleteChucVu(id);
        return "Deleted ID: " + id;
    }

    @GetMapping("/search")
    public List<ChucVu> search(@RequestParam String keyword) {
        return chucVuService.searchChucVu(keyword);
    }
}

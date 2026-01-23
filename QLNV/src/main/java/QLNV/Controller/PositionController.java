package QLNV.Controller;

import QLNV.Entity.Position;
import QLNV.Service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chucvu")
@CrossOrigin(origins = "*")
public class PositionController {

    @Autowired
    private PositionService chucVuService;

    @GetMapping("/list")
    public List<Position> getAll() {
        return chucVuService.getAll();
    }

    @GetMapping("/{id}")
    public Position getById(@PathVariable Long id) {
        return chucVuService.getChucVu(id).orElse(null);
    }

    @PostMapping("/create")
    public Position create(@RequestBody Position cv) {
        return chucVuService.saveChucVu(cv);
    }

    @PutMapping("/update/{id}")
    public Position update(@PathVariable Long id, @RequestBody Position cv) {
        return chucVuService.updateChucVu(id, cv);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        chucVuService.deleteChucVu(id);
        return "Deleted ID: " + id;
    }

    @GetMapping("/search")
    public List<Position> search(@RequestParam String keyword) {
        return chucVuService.searchChucVu(keyword);
    }
}

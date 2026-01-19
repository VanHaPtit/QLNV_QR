package QLNV.Controller;

import QLNV.Entity.PhongBan;
import QLNV.Service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phongban")
@CrossOrigin(origins = "*")
public class PhongBanController {

    @Autowired
    public PhongBanService phongBanService;

    // 1. GET all
    @GetMapping("/list")
    public List<PhongBan> getPhongBanList() {
        return phongBanService.getAll();
    }

    // 2. GET by ID
    @GetMapping("/{id}")
    public PhongBan getById(@PathVariable Long id) {
        return phongBanService.getPhongBan(id).orElse(null);
    }

    // 3. POST create
    @PostMapping("/create")
    public PhongBan create(@RequestBody PhongBan phongBan) {
        return phongBanService.savePhongBan(phongBan);
    }

    // 4. PUT update
    @PutMapping("/update/{id}")
    public PhongBan update(@PathVariable Long id, @RequestBody PhongBan phongBan) {
        return phongBanService.updatePhongBan(id, phongBan);
    }

    // 5. DELETE
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        phongBanService.deletePhongBan(id);
        return "Deleted ID: " + id;
    }

    // 6. SEARCH
    @GetMapping("/search")
    public List<PhongBan> search(@RequestParam String keyword) {
        return phongBanService.searchPhongBan(keyword);
    }
}



package QLNV.Controller;

import QLNV.Entity.Department;
import QLNV.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phongban")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    public DepartmentService phongBanService;


    @GetMapping("/list")
    public List<Department> getPhongBanList() {
        return phongBanService.getAll();
    }


    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return phongBanService.getPhongBan(id).orElse(null);
    }


    @PostMapping("/create")
    public Department create(@RequestBody Department phongBan) {
        return phongBanService.savePhongBan(phongBan);
    }


    @PutMapping("/update/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department phongBan) {
        return phongBanService.updatePhongBan(id, phongBan);
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        phongBanService.deletePhongBan(id);
        return "Deleted ID: " + id;
    }


    @GetMapping("/search")
    public List<Department> search(@RequestParam String keyword) {
        return phongBanService.searchPhongBan(keyword);
    }
}



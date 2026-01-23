package QLNV.Controller;

import QLNV.Entity.EmployeeAllowance;
import QLNV.Service.EmployeeAllowanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phu-cap-nhan-vien")
@CrossOrigin(origins = "*")
public class EmployeeAllowanceController {

    private final EmployeeAllowanceService service;

    public EmployeeAllowanceController(EmployeeAllowanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeAllowance> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeAllowance getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public EmployeeAllowance create(@RequestBody EmployeeAllowance pc) {
        return service.create(pc);
    }

    @PutMapping("/{id}")
    public EmployeeAllowance update(@PathVariable Long id, @RequestBody EmployeeAllowance pc) {
        return service.update(id, pc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ======= TÌM KIẾM ==========
    @GetMapping("/hop-dong/{hopDongId}")
    public List<EmployeeAllowance> findByHopDong(@PathVariable Long hopDongId) {
        return service.findByHopDong(hopDongId);
    }

    @GetMapping("/phu-cap/{phuCapId}")
    public List<EmployeeAllowance> findByPhuCap(@PathVariable Long phuCapId) {
        return service.findByPhuCap(phuCapId);
    }
}

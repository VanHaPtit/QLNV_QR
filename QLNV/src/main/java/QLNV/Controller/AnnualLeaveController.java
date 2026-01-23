package QLNV.Controller;

import QLNV.Entity.AnnualLeave;
import QLNV.Service.QuyPhepNamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quy-phep-nam")
@CrossOrigin(origins = "*")
public class AnnualLeaveController {

    private final QuyPhepNamService service;

    public AnnualLeaveController(QuyPhepNamService service) {
        this.service = service;
    }

    @GetMapping
    public List<AnnualLeave> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AnnualLeave getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public AnnualLeave create(@RequestBody AnnualLeave qpn) {
        return service.create(qpn);
    }

    @PutMapping("/{id}")
    public AnnualLeave update(@PathVariable Long id, @RequestBody AnnualLeave qpn) {
        return service.update(id, qpn);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Đã xóa QuyPhepNam id = " + id;
    }

    // Lấy theo năm
    @GetMapping("/nam/{nam}")
    public List<AnnualLeave> getByYear(@PathVariable Integer nam) {
        return service.findByNam(nam);
    }

    // Lấy theo nhân viên
    @GetMapping("/nhan-vien/{id}")
    public List<AnnualLeave> getByEmployee(@PathVariable Long id) {
        return service.findByNhanVien(id);
    }

    // Lấy theo nhân viên + năm
    @GetMapping("/search")
    public List<AnnualLeave> search(
            @RequestParam Long nhanVienId,
            @RequestParam Integer nam) {
        return service.findByNhanVienAndNam(nhanVienId, nam);
    }

}

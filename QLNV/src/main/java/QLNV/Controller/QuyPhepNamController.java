package QLNV.Controller;

import QLNV.Entity.QuyPhepNam;
import QLNV.Service.QuyPhepNamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quy-phep-nam")
@CrossOrigin(origins = "*")
public class QuyPhepNamController {

    private final QuyPhepNamService service;

    public QuyPhepNamController(QuyPhepNamService service) {
        this.service = service;
    }

    @GetMapping
    public List<QuyPhepNam> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public QuyPhepNam getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public QuyPhepNam create(@RequestBody QuyPhepNam qpn) {
        return service.create(qpn);
    }

    @PutMapping("/{id}")
    public QuyPhepNam update(@PathVariable Long id, @RequestBody QuyPhepNam qpn) {
        return service.update(id, qpn);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Đã xóa QuyPhepNam id = " + id;
    }

    // Lấy theo năm
    @GetMapping("/nam/{nam}")
    public List<QuyPhepNam> getByYear(@PathVariable Integer nam) {
        return service.findByNam(nam);
    }

    // Lấy theo nhân viên
    @GetMapping("/nhan-vien/{id}")
    public List<QuyPhepNam> getByEmployee(@PathVariable Long id) {
        return service.findByNhanVien(id);
    }

    // Lấy theo nhân viên + năm
    @GetMapping("/search")
    public List<QuyPhepNam> search(
            @RequestParam Long nhanVienId,
            @RequestParam Integer nam) {
        return service.findByNhanVienAndNam(nhanVienId, nam);
    }

}

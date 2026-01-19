package QLNV.Service.impl;

import QLNV.Entity.NhanVienPhuCap;
import QLNV.Repository.NhanVienPhuCapRepository;
import QLNV.Service.NhanVienPhuCapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienPhuCapServiceImpl implements NhanVienPhuCapService {

    private final NhanVienPhuCapRepository repo;

    public NhanVienPhuCapServiceImpl(NhanVienPhuCapRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<NhanVienPhuCap> getAll() {
        return repo.findAll();
    }

    @Override
    public NhanVienPhuCap getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phụ cấp nhân viên id = " + id));
    }

    @Override
    public NhanVienPhuCap create(NhanVienPhuCap pc) {
        return repo.save(pc);
    }

    @Override
    public NhanVienPhuCap update(Long id, NhanVienPhuCap pc) {
        NhanVienPhuCap old = getById(id);
        old.setHopDong(pc.getHopDong());
        old.setPhuCap(pc.getPhuCap());
        old.setSoTien(pc.getSoTien());
        return repo.save(old);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<NhanVienPhuCap> findByHopDong(Long hopDongId) {
        return repo.findByHopDongId(hopDongId);
    }

    @Override
    public List<NhanVienPhuCap> findByPhuCap(Long phuCapId) {
        return repo.findByPhuCapId(phuCapId);
    }
}

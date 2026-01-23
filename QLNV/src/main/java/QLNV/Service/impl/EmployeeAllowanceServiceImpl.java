package QLNV.Service.impl;

import QLNV.Entity.EmployeeAllowance;
import QLNV.Repository.EmployeeAllowanceRepository;
import QLNV.Service.EmployeeAllowanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeAllowanceServiceImpl implements EmployeeAllowanceService {

    private final EmployeeAllowanceRepository repo;

    public EmployeeAllowanceServiceImpl(EmployeeAllowanceRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<EmployeeAllowance> getAll() {
        return repo.findAll();
    }

    @Override
    public EmployeeAllowance getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phụ cấp nhân viên id = " + id));
    }

    @Override
    public EmployeeAllowance create(EmployeeAllowance pc) {
        return repo.save(pc);
    }

    @Override
    public EmployeeAllowance update(Long id, EmployeeAllowance pc) {
        EmployeeAllowance old = getById(id);
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
    public List<EmployeeAllowance> findByHopDong(Long hopDongId) {
        return repo.findByHopDongId(hopDongId);
    }

    @Override
    public List<EmployeeAllowance> findByPhuCap(Long phuCapId) {
        return repo.findByPhuCapId(phuCapId);
    }
}

package QLNV.Service.impl;

import QLNV.Entity.Department;
import QLNV.Repository.DepartmentRepository;
import QLNV.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository phongBanRepository ;

    @Override
    public List<Department> getAll() {
        return phongBanRepository.findAll();
    }

    @Override
    public Optional<Department> getPhongBan(Long id) {
        return phongBanRepository.findById(id);
    }

    @Override
    public Department savePhongBan(Department phongBan) {
        return phongBanRepository.save(phongBan);
    }

    @Override
    public void deletePhongBan(Long id) {
        phongBanRepository.deleteById(id);
    }

    @Override
    public Department updatePhongBan(Long id, Department data) {
        return phongBanRepository.findById(id)
                .map(pb -> {
                    pb.setTenPhongBan(data.getTenPhongBan());
                    pb.setMoTa(data.getMoTa());
                    return phongBanRepository.save(pb);
                })
                .orElse(null);
    }

    @Override
    public List<Department> searchPhongBan(String keyword) {
        return phongBanRepository.findByTenPhongBanContainingIgnoreCase(keyword);
    }
}
package QLNV.Service.impl;

import QLNV.Entity.PhongBan;
import QLNV.Repository.PhongBanRepository;
import QLNV.Service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhongBanServiceImpl implements PhongBanService {

    @Autowired
    private PhongBanRepository phongBanRepository ;

    @Override
    public List<PhongBan> getAll() {
        return phongBanRepository.findAll();
    }

    @Override
    public Optional<PhongBan> getPhongBan(Long id) {
        return phongBanRepository.findById(id);
    }

    @Override
    public PhongBan savePhongBan(PhongBan phongBan) {
        return phongBanRepository.save(phongBan);
    }

    @Override
    public void deletePhongBan(Long id) {
        phongBanRepository.deleteById(id);
    }

    @Override
    public PhongBan updatePhongBan(Long id, PhongBan data) {
        return phongBanRepository.findById(id)
                .map(pb -> {
                    pb.setTenPhongBan(data.getTenPhongBan());
                    pb.setMoTa(data.getMoTa());
                    return phongBanRepository.save(pb);
                })
                .orElse(null);
    }

    @Override
    public List<PhongBan> searchPhongBan(String keyword) {
        return phongBanRepository.findByTenPhongBanContainingIgnoreCase(keyword);
    }
}
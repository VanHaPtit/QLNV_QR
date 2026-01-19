package QLNV.Service.impl;

import QLNV.Entity.ChucVu;
import QLNV.Repository.ChucVuRepository;
import QLNV.Service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChucVuServiceImpl implements ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Override
    public List<ChucVu> getAll() {
        return chucVuRepository.findAll();
    }

    @Override
    public Optional<ChucVu> getChucVu(Long id) {
        return chucVuRepository.findById(id);
    }


    @Override
    public ChucVu saveChucVu(ChucVu chucVu) {
        return chucVuRepository.save(chucVu);
    }

    @Override
    public void deleteChucVu(Long id) {
        chucVuRepository.deleteById(id);
    }

    @Override
    public ChucVu updateChucVu(Long id, ChucVu data) {
        return chucVuRepository.findById(id)
                .map(cv -> {
                    cv.setTenChucVu(data.getTenChucVu());
                    cv.setCapBac(data.getCapBac());
                    return chucVuRepository.save(cv);
                })
                .orElse(null);
    }

    @Override
    public List<ChucVu> searchChucVu(String keyword) {
        return chucVuRepository.findByTenChucVuContainingIgnoreCase(keyword);
    }
}

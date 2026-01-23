package QLNV.Service.impl;

import QLNV.Entity.Position;
import QLNV.Repository.PositionRepository;
import QLNV.Service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository chucVuRepository;

    @Override
    public List<Position> getAll() {
        return chucVuRepository.findAll();
    }

    @Override
    public Optional<Position> getChucVu(Long id) {
        return chucVuRepository.findById(id);
    }


    @Override
    public Position saveChucVu(Position chucVu) {
        return chucVuRepository.save(chucVu);
    }

    @Override
    public void deleteChucVu(Long id) {
        chucVuRepository.deleteById(id);
    }

    @Override
    public Position updateChucVu(Long id, Position data) {
        return chucVuRepository.findById(id)
                .map(cv -> {
                    cv.setTenChucVu(data.getTenChucVu());
                    cv.setCapBac(data.getCapBac());
                    return chucVuRepository.save(cv);
                })
                .orElse(null);
    }

    @Override
    public List<Position> searchChucVu(String keyword) {
        return chucVuRepository.findByTenChucVuContainingIgnoreCase(keyword);
    }
}

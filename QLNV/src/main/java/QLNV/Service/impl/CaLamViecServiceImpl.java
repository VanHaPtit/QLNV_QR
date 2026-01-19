package QLNV.Service.impl;

import QLNV.Entity.CaLamViec;
import QLNV.Repository.CaLamViecRepository;
import QLNV.Service.CaLamViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaLamViecServiceImpl implements CaLamViecService {

    @Autowired
    private CaLamViecRepository repository;

    @Override
    public List<CaLamViec> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<CaLamViec> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public CaLamViec save(CaLamViec caLamViec) {
        return repository.save(caLamViec);
    }

    @Override
    public CaLamViec update(Long id, CaLamViec caLamViec) {
        CaLamViec existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ca làm việc không tồn tại"));

        existing.setTenCa(caLamViec.getTenCa());
        existing.setGioVao(caLamViec.getGioVao());
        existing.setGioRa(caLamViec.getGioRa());
        existing.setNghiTruaBatDau(caLamViec.getNghiTruaBatDau());
        existing.setNghiTruaKetThuc(caLamViec.getNghiTruaKetThuc());
        existing.setSoCongChuan(caLamViec.getSoCongChuan());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ca làm việc không tồn tại");
        }
        repository.deleteById(id);
    }
}


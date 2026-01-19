package QLNV.Service.impl;

import QLNV.Entity.CauHinhPhuCap;
import QLNV.Repository.CauHinhPhuCapRepository;
import QLNV.Service.CauHinhPhuCapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CauHinhPhuCapServiceImpl implements CauHinhPhuCapService {

    private final CauHinhPhuCapRepository repository;

    public CauHinhPhuCapServiceImpl(CauHinhPhuCapRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CauHinhPhuCap> getAll() {
        return repository.findAll();
    }

    @Override
    public CauHinhPhuCap getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phụ cấp ID = " + id));
    }

    @Override
    public CauHinhPhuCap create(CauHinhPhuCap phuCap) {
        return repository.save(phuCap);
    }

    @Override
    public CauHinhPhuCap update(Long id, CauHinhPhuCap phuCapUpdate) {
        CauHinhPhuCap old = getById(id);

        old.setTenPhuCap(phuCapUpdate.getTenPhuCap());
        old.setTinhThueTncn(phuCapUpdate.isTinhThueTncn());
        old.setDongBhxh(phuCapUpdate.isDongBhxh());

        return repository.save(old);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

package QLNV.Service.impl;

import QLNV.Entity.Dependent;
import QLNV.Repository.DependentRepository;
import QLNV.Service.DependentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependentServiceImpl implements DependentService {

    private final DependentRepository repository;

    public DependentServiceImpl(DependentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Dependent> getAll() {
        return repository.findAll();
    }

    @Override
    public Dependent getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người phụ thuộc ID=" + id));
    }

    @Override
    public Dependent create(Dependent data) {
        return repository.save(data);
    }

    @Override
    public Dependent update(Long id, Dependent data) {
        Dependent old = getById(id);

        old.setNhanVien(data.getNhanVien());
        old.setHoTenNguoiThan(data.getHoTenNguoiThan());
        old.setQuanHe(data.getQuanHe());
        old.setNgaySinh(data.getNgaySinh());
        old.setMaSoThue(data.getMaSoThue());
        old.setNgayBatDauGiamTru(data.getNgayBatDauGiamTru());
        old.setNgayKetThucGiamTru(data.getNgayKetThucGiamTru());

        return repository.save(old);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Dependent> findByNhanVien(Long nvId) {
        return repository.findByNhanVien_Id(nvId);
    }

    @Override
    public List<Dependent> findByMaSoThue(String mst) {
        return repository.findByMaSoThue(mst);
    }
}
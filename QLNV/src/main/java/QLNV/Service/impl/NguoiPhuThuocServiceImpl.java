package QLNV.Service.impl;

import QLNV.Entity.NguoiPhuThuoc;
import QLNV.Repository.NguoiPhuThuocRepository;
import QLNV.Service.NguoiPhuThuocService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NguoiPhuThuocServiceImpl implements NguoiPhuThuocService {

    private final NguoiPhuThuocRepository repository;

    public NguoiPhuThuocServiceImpl(NguoiPhuThuocRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NguoiPhuThuoc> getAll() {
        return repository.findAll();
    }

    @Override
    public NguoiPhuThuoc getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người phụ thuộc ID=" + id));
    }

    @Override
    public NguoiPhuThuoc create(NguoiPhuThuoc data) {
        return repository.save(data);
    }

    @Override
    public NguoiPhuThuoc update(Long id, NguoiPhuThuoc data) {
        NguoiPhuThuoc old = getById(id);

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
    public List<NguoiPhuThuoc> findByNhanVien(Long nvId) {
        return repository.findByNhanVien_Id(nvId);
    }

    @Override
    public List<NguoiPhuThuoc> findByMaSoThue(String mst) {
        return repository.findByMaSoThue(mst);
    }
}
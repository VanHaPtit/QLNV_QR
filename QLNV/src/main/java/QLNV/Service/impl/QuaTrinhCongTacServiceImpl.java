package QLNV.Service.impl;

import QLNV.Entity.QuaTrinhCongTac;
import QLNV.Repository.QuaTrinhCongTacRepository;
import QLNV.Service.QuaTrinhCongTacService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuaTrinhCongTacServiceImpl implements QuaTrinhCongTacService {

    private final QuaTrinhCongTacRepository repository;

    public QuaTrinhCongTacServiceImpl(QuaTrinhCongTacRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<QuaTrinhCongTac> getAll() {
        return repository.findAll();
    }

    @Override
    public QuaTrinhCongTac getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public QuaTrinhCongTac create(QuaTrinhCongTac qtct) {
        return repository.save(qtct);
    }

    @Override
    public QuaTrinhCongTac update(Long id, QuaTrinhCongTac qtct) {
        QuaTrinhCongTac exist = repository.findById(id).orElse(null);
        if (exist == null) return null;

        exist.setNhanVien(qtct.getNhanVien());
        exist.setPhongBan(qtct.getPhongBan());
        exist.setChucVu(qtct.getChucVu());
        exist.setNgayHieuLuc(qtct.getNgayHieuLuc());
        exist.setNgayKetThuc(qtct.getNgayKetThuc());
        exist.setLoaiQuyetDinh(qtct.getLoaiQuyetDinh());

        return repository.save(exist);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<QuaTrinhCongTac> findByNhanVien(Long nhanVienId) {
        return repository.findByNhanVienId(nhanVienId);
    }
}
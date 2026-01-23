package QLNV.Service.impl;

import QLNV.Entity.WorkHistory;
import QLNV.Repository.WorkHistoryRepository;
import QLNV.Service.WorkHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkHistoryServiceImpl implements WorkHistoryService {

    private final WorkHistoryRepository repository;

    public WorkHistoryServiceImpl(WorkHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WorkHistory> getAll() {
        return repository.findAll();
    }

    @Override
    public WorkHistory getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public WorkHistory create(WorkHistory qtct) {
        return repository.save(qtct);
    }

    @Override
    public WorkHistory update(Long id, WorkHistory qtct) {
        WorkHistory exist = repository.findById(id).orElse(null);
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
    public List<WorkHistory> findByNhanVien(Long nhanVienId) {
        return repository.findByNhanVienId(nhanVienId);
    }
}
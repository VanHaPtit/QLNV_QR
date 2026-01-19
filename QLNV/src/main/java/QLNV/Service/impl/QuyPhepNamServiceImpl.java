package QLNV.Service.impl;

import QLNV.Entity.QuyPhepNam;
import QLNV.Repository.QuyPhepNamRepository;
import QLNV.Service.QuyPhepNamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuyPhepNamServiceImpl implements QuyPhepNamService {

    private final QuyPhepNamRepository repository;

    public QuyPhepNamServiceImpl(QuyPhepNamRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<QuyPhepNam> getAll() {
        return repository.findAll();
    }

    @Override
    public QuyPhepNam getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy QuyPhepNam id = " + id));
    }

    @Override
    public QuyPhepNam create(QuyPhepNam qpn) {
        return repository.save(qpn);
    }

    @Override
    public QuyPhepNam update(Long id, QuyPhepNam qpn) {
        QuyPhepNam old = getById(id);

        old.setNhanVien(qpn.getNhanVien());
        old.setNam(qpn.getNam());
        old.setTongPhepDuocCap(qpn.getTongPhepDuocCap());
        old.setPhepDaNghi(qpn.getPhepDaNghi());
        old.setPhepTonNamTruoc(qpn.getPhepTonNamTruoc());

        return repository.save(old);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<QuyPhepNam> findByNam(Integer nam) {
        return repository.findByNam(nam);
    }

    @Override
    public List<QuyPhepNam> findByNhanVien(Long nhanVienId) {
        return repository.findByNhanVienId(nhanVienId);
    }

    @Override
    public List<QuyPhepNam> findByNhanVienAndNam(Long nhanVienId, Integer nam) {
        return repository.findByNhanVienIdAndNam(nhanVienId, nam);
    }

}

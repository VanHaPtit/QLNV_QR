package QLNV.Service.impl;

import QLNV.Entity.AnnualLeave;
import QLNV.Repository.AnnualLeaveRepository;
import QLNV.Service.QuyPhepNamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnualLeaveServiceImpl implements QuyPhepNamService {

    private final AnnualLeaveRepository repository;

    public AnnualLeaveServiceImpl(AnnualLeaveRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AnnualLeave> getAll() {
        return repository.findAll();
    }

    @Override
    public AnnualLeave getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy QuyPhepNam id = " + id));
    }

    @Override
    public AnnualLeave create(AnnualLeave qpn) {
        return repository.save(qpn);
    }

    @Override
    public AnnualLeave update(Long id, AnnualLeave qpn) {
        AnnualLeave old = getById(id);

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
    public List<AnnualLeave> findByNam(Integer nam) {
        return repository.findByNam(nam);
    }

    @Override
    public List<AnnualLeave> findByNhanVien(Long nhanVienId) {
        return repository.findByNhanVienId(nhanVienId);
    }

    @Override
    public List<AnnualLeave> findByNhanVienAndNam(Long nhanVienId, Integer nam) {
        return repository.findByNhanVienIdAndNam(nhanVienId, nam);
    }

}

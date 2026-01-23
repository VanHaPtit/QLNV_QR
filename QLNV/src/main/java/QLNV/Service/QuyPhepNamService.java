package QLNV.Service;

import QLNV.Entity.AnnualLeave;

import java.util.List;

public interface QuyPhepNamService {
    List<AnnualLeave> getAll();
    AnnualLeave getById(Long id);
    AnnualLeave create(AnnualLeave qpn);
    AnnualLeave update(Long id, AnnualLeave qpn);
    void delete(Long id);
    List<AnnualLeave> findByNam(Integer nam);

    List<AnnualLeave> findByNhanVien(Long nhanVienId);

    List<AnnualLeave> findByNhanVienAndNam(Long nhanVienId, Integer nam);
}

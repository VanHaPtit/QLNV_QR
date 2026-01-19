package QLNV.Service;

import QLNV.Entity.QuyPhepNam;

import java.util.List;

public interface QuyPhepNamService {
    List<QuyPhepNam> getAll();
    QuyPhepNam getById(Long id);
    QuyPhepNam create(QuyPhepNam qpn);
    QuyPhepNam update(Long id, QuyPhepNam qpn);
    void delete(Long id);
    List<QuyPhepNam> findByNam(Integer nam);

    List<QuyPhepNam> findByNhanVien(Long nhanVienId);

    List<QuyPhepNam> findByNhanVienAndNam(Long nhanVienId, Integer nam);
}

package QLNV.Service;

import QLNV.Entity.DonXinPhep;
import QLNV.Entity.Enum.TrangThaiDon;

import java.util.List;

public interface DonXinPhepService {

    List<DonXinPhep> getAll();
    DonXinPhep getById(Long id);
    DonXinPhep create(DonXinPhep data);
    DonXinPhep update(Long id, DonXinPhep data);
    void delete(Long id);

    // Filtering
    List<DonXinPhep> findByNhanVien(Long nvId);
    List<DonXinPhep> findByTrangThai(TrangThaiDon status);

    // Chức năng duyệt đơn
    DonXinPhep approveDon(Long id, Long nguoiDuyetId);
    DonXinPhep rejectDon(Long id, Long nguoiDuyetId);
}

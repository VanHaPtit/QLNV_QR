package QLNV.Service;

import QLNV.Entity.NguoiPhuThuoc;

import java.util.List;

public interface NguoiPhuThuocService {

    List<NguoiPhuThuoc> getAll();
    NguoiPhuThuoc getById(Long id);
    NguoiPhuThuoc create(NguoiPhuThuoc data);
    NguoiPhuThuoc update(Long id, NguoiPhuThuoc data);
    void delete(Long id);

    // Thêm filter
    List<NguoiPhuThuoc> findByNhanVien(Long nvId);
    List<NguoiPhuThuoc> findByMaSoThue(String mst);
}

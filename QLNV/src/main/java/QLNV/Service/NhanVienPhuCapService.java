package QLNV.Service;

import QLNV.Entity.NhanVienPhuCap;

import java.util.List;

public interface NhanVienPhuCapService {
    List<NhanVienPhuCap> getAll();
    NhanVienPhuCap getById(Long id);
    NhanVienPhuCap create(NhanVienPhuCap pc);
    NhanVienPhuCap update(Long id, NhanVienPhuCap pc);
    void delete(Long id);

    List<NhanVienPhuCap> findByHopDong(Long hopDongId);
    List<NhanVienPhuCap> findByPhuCap(Long phuCapId);
}

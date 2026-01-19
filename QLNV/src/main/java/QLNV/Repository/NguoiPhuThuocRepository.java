package QLNV.Repository;

import QLNV.Entity.NguoiPhuThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NguoiPhuThuocRepository extends JpaRepository<NguoiPhuThuoc, Long> {

    // Tìm theo nhân viên
    List<NguoiPhuThuoc> findByNhanVien_Id(Long nhanVienId);

    // Tìm theo MST
    List<NguoiPhuThuoc> findByMaSoThue(String maSoThue);
}

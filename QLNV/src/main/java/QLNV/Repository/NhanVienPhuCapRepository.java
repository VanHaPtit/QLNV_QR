package QLNV.Repository;

import QLNV.Entity.NhanVienPhuCap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhanVienPhuCapRepository extends JpaRepository<NhanVienPhuCap, Long> {

    // Tìm theo mã hợp đồng
    List<NhanVienPhuCap> findByHopDongId(Long hopDongId);

    // Tìm theo mã phụ cấp
    List<NhanVienPhuCap> findByPhuCapId(Long phuCapId);
}

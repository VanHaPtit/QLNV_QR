package QLNV.Repository;

import QLNV.Entity.DonXinPhep;
import QLNV.Entity.Enum.TrangThaiDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DonXinPhepRepository extends JpaRepository<DonXinPhep, Long> {

    // Lấy đơn theo nhân viên
    List<DonXinPhep> findByNhanVien_Id(Long nhanVienId);

    // Lọc theo trạng thái
    List<DonXinPhep> findByTrangThai(TrangThaiDon trangThai);

    // Lọc theo khoảng ngày
    List<DonXinPhep> findByTuNgayBetween(LocalDate start, LocalDate end);
}

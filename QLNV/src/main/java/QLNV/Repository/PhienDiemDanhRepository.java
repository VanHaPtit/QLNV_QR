package QLNV.Repository;

import QLNV.Entity.PhienDiemDanh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhienDiemDanhRepository extends JpaRepository<PhienDiemDanh, Long> {
    // Lấy phiên mới nhất đang hoạt động
    Optional<PhienDiemDanh> findTopByDangHoatDongTrueOrderByNgayTaoDesc();
}

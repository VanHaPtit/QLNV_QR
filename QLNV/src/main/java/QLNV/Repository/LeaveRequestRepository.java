package QLNV.Repository;

import QLNV.Entity.LeaveRequest;
import QLNV.Entity.Enum.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // Lấy đơn theo nhân viên
    List<LeaveRequest> findByNhanVien_Id(Long nhanVienId);

    // Lọc theo trạng thái
    List<LeaveRequest> findByTrangThai(RequestStatus trangThai);

    // Lọc theo khoảng ngày
    List<LeaveRequest> findByTuNgayBetween(LocalDate start, LocalDate end);
}

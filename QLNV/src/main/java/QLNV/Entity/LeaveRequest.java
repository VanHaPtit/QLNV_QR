package QLNV.Entity;

import QLNV.Entity.Enum.LeaveType;
import QLNV.Entity.Enum.RequestStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "don_xin_phep")
public class LeaveRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee nhanVien;

    private LocalDate tuNgay;
    private LocalDate denNgay;

    @Enumerated(EnumType.STRING)
    private LeaveType loaiNghi;

    @Enumerated(EnumType.STRING)
    private RequestStatus trangThai;

    @ManyToOne
    private Employee nguoiDuyet;

    public LeaveRequest(Long id, Employee nhanVien, LocalDate tuNgay, LocalDate denNgay, LeaveType loaiNghi, RequestStatus trangThai, Employee nguoiDuyet) {
        this.id = id;
        this.nhanVien = nhanVien;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.loaiNghi = loaiNghi;
        this.trangThai = trangThai;
        this.nguoiDuyet = nguoiDuyet;
    }

    public LeaveRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(Employee nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDate getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(LocalDate tuNgay) {
        this.tuNgay = tuNgay;
    }

    public LocalDate getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(LocalDate denNgay) {
        this.denNgay = denNgay;
    }

    public LeaveType getLoaiNghi() {
        return loaiNghi;
    }

    public void setLoaiNghi(LeaveType loaiNghi) {
        this.loaiNghi = loaiNghi;
    }

    public RequestStatus getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(RequestStatus trangThai) {
        this.trangThai = trangThai;
    }

    public Employee getNguoiDuyet() {
        return nguoiDuyet;
    }

    public void setNguoiDuyet(Employee nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }
}


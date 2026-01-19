package QLNV.Entity;

import QLNV.Entity.Enum.LoaiNghi;
import QLNV.Entity.Enum.TrangThaiDon;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "don_xin_phep")
public class DonXinPhep {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NhanVien nhanVien;

    private LocalDate tuNgay;
    private LocalDate denNgay;

    @Enumerated(EnumType.STRING)
    private LoaiNghi loaiNghi;

    @Enumerated(EnumType.STRING)
    private TrangThaiDon trangThai;

    @ManyToOne
    private NhanVien nguoiDuyet;

    public DonXinPhep(Long id, NhanVien nhanVien, LocalDate tuNgay, LocalDate denNgay, LoaiNghi loaiNghi, TrangThaiDon trangThai, NhanVien nguoiDuyet) {
        this.id = id;
        this.nhanVien = nhanVien;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.loaiNghi = loaiNghi;
        this.trangThai = trangThai;
        this.nguoiDuyet = nguoiDuyet;
    }

    public DonXinPhep() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
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

    public LoaiNghi getLoaiNghi() {
        return loaiNghi;
    }

    public void setLoaiNghi(LoaiNghi loaiNghi) {
        this.loaiNghi = loaiNghi;
    }

    public TrangThaiDon getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiDon trangThai) {
        this.trangThai = trangThai;
    }

    public NhanVien getNguoiDuyet() {
        return nguoiDuyet;
    }

    public void setNguoiDuyet(NhanVien nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }
}


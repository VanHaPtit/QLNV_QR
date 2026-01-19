package QLNV.Entity;

import QLNV.Entity.Enum.LoaiQuyetDinh;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "qua_trinh_cong_tac")
public class QuaTrinhCongTac {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NhanVien nhanVien;

    @ManyToOne
    private PhongBan phongBan;

    @ManyToOne
    private ChucVu chucVu;

    private LocalDate ngayHieuLuc;
    private LocalDate ngayKetThuc;

    @Enumerated(EnumType.STRING)
    private LoaiQuyetDinh loaiQuyetDinh;

    public QuaTrinhCongTac(Long id, NhanVien nhanVien, PhongBan phongBan, ChucVu chucVu, LocalDate ngayHieuLuc, LocalDate ngayKetThuc, LoaiQuyetDinh loaiQuyetDinh) {
        this.id = id;
        this.nhanVien = nhanVien;
        this.phongBan = phongBan;
        this.chucVu = chucVu;
        this.ngayHieuLuc = ngayHieuLuc;
        this.ngayKetThuc = ngayKetThuc;
        this.loaiQuyetDinh = loaiQuyetDinh;
    }

    public QuaTrinhCongTac() {
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

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public LocalDate getNgayHieuLuc() {
        return ngayHieuLuc;
    }

    public void setNgayHieuLuc(LocalDate ngayHieuLuc) {
        this.ngayHieuLuc = ngayHieuLuc;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public LoaiQuyetDinh getLoaiQuyetDinh() {
        return loaiQuyetDinh;
    }

    public void setLoaiQuyetDinh(LoaiQuyetDinh loaiQuyetDinh) {
        this.loaiQuyetDinh = loaiQuyetDinh;
    }
}


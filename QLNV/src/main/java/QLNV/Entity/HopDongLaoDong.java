package QLNV.Entity;

import QLNV.Entity.Enum.LoaiHopDong;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "hop_dong_lao_dong")
public class HopDongLaoDong {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String soHopDong;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;

    @Enumerated(EnumType.STRING)
    private LoaiHopDong loaiHopDong;

    private LocalDate ngayHieuLuc;
    private LocalDate ngayHetHan;

    private BigDecimal luongCoBan;
    private BigDecimal luongDongBhxh;

    private String fileDinhKem;

    public HopDongLaoDong(Long id, String soHopDong, NhanVien nhanVien, LoaiHopDong loaiHopDong, LocalDate ngayHieuLuc, LocalDate ngayHetHan, BigDecimal luongCoBan, BigDecimal luongDongBhxh, String fileDinhKem) {
        this.id = id;
        this.soHopDong = soHopDong;
        this.nhanVien = nhanVien;
        this.loaiHopDong = loaiHopDong;
        this.ngayHieuLuc = ngayHieuLuc;
        this.ngayHetHan = ngayHetHan;
        this.luongCoBan = luongCoBan;
        this.luongDongBhxh = luongDongBhxh;
        this.fileDinhKem = fileDinhKem;
    }

    public HopDongLaoDong() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoHopDong() {
        return soHopDong;
    }

    public void setSoHopDong(String soHopDong) {
        this.soHopDong = soHopDong;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LoaiHopDong getLoaiHopDong() {
        return loaiHopDong;
    }

    public void setLoaiHopDong(LoaiHopDong loaiHopDong) {
        this.loaiHopDong = loaiHopDong;
    }

    public LocalDate getNgayHieuLuc() {
        return ngayHieuLuc;
    }

    public void setNgayHieuLuc(LocalDate ngayHieuLuc) {
        this.ngayHieuLuc = ngayHieuLuc;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public BigDecimal getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(BigDecimal luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public BigDecimal getLuongDongBhxh() {
        return luongDongBhxh;
    }

    public void setLuongDongBhxh(BigDecimal luongDongBhxh) {
        this.luongDongBhxh = luongDongBhxh;
    }

    public String getFileDinhKem() {
        return fileDinhKem;
    }

    public void setFileDinhKem(String fileDinhKem) {
        this.fileDinhKem = fileDinhKem;
    }
}

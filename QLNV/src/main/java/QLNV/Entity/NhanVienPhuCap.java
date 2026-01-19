package QLNV.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "nhan_vien_phu_cap")
public class NhanVienPhuCap {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private HopDongLaoDong hopDong;

    @ManyToOne
    private CauHinhPhuCap phuCap;

    private BigDecimal soTien;

    public NhanVienPhuCap(Long id, HopDongLaoDong hopDong, CauHinhPhuCap phuCap, BigDecimal soTien) {
        this.id = id;
        this.hopDong = hopDong;
        this.phuCap = phuCap;
        this.soTien = soTien;
    }

    public NhanVienPhuCap() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HopDongLaoDong getHopDong() {
        return hopDong;
    }

    public void setHopDong(HopDongLaoDong hopDong) {
        this.hopDong = hopDong;
    }

    public CauHinhPhuCap getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(CauHinhPhuCap phuCap) {
        this.phuCap = phuCap;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }
}


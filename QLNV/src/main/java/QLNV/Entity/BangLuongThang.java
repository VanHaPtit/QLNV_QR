//package QLNV.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "bang_luong_thang")
//public class BangLuongThang {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Integer thang;
//    private Integer nam;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "nhan_vien_id")
//    @JsonIgnoreProperties({"bangLuongThangs", "hibernateLazyInitializer", "handler"})
//    private NhanVien nhanVien;
//
//    // Thu nhập
//    private BigDecimal luongNgayCong;
//    private BigDecimal luongOt;
//    private BigDecimal tongPhuCap;
//    private BigDecimal thuongDoanhSo;
//
//    // Khấu trừ
//    private BigDecimal khauTruBhxh;
//    private BigDecimal khauTruBhyt;
//    private BigDecimal khauTruBhtn;
//    private BigDecimal doanPhi;
//    private BigDecimal thueTncn;
//
//    // Thực lĩnh
//    private BigDecimal tamUng;
//    private BigDecimal thucLinh;
//
//    public BangLuongThang() {
//    }
//
//    public BangLuongThang(Long id, Integer thang, Integer nam, NhanVien nhanVien, BigDecimal luongNgayCong, BigDecimal luongOt, BigDecimal tongPhuCap, BigDecimal thuongDoanhSo, BigDecimal khauTruBhxh, BigDecimal khauTruBhyt, BigDecimal khauTruBhtn, BigDecimal doanPhi, BigDecimal thueTncn, BigDecimal tamUng, BigDecimal thucLinh) {
//        this.id = id;
//        this.thang = thang;
//        this.nam = nam;
//        this.nhanVien = nhanVien;
//        this.luongNgayCong = luongNgayCong;
//        this.luongOt = luongOt;
//        this.tongPhuCap = tongPhuCap;
//        this.thuongDoanhSo = thuongDoanhSo;
//        this.khauTruBhxh = khauTruBhxh;
//        this.khauTruBhyt = khauTruBhyt;
//        this.khauTruBhtn = khauTruBhtn;
//        this.doanPhi = doanPhi;
//        this.thueTncn = thueTncn;
//        this.tamUng = tamUng;
//        this.thucLinh = thucLinh;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Integer getThang() {
//        return thang;
//    }
//
//    public void setThang(Integer thang) {
//        this.thang = thang;
//    }
//
//    public Integer getNam() {
//        return nam;
//    }
//
//    public void setNam(Integer nam) {
//        this.nam = nam;
//    }
//
//    public NhanVien getNhanVien() {
//        return nhanVien;
//    }
//
//    public void setNhanVien(NhanVien nhanVien) {
//        this.nhanVien = nhanVien;
//    }
//
//    public BigDecimal getLuongNgayCong() {
//        return luongNgayCong;
//    }
//
//    public void setLuongNgayCong(BigDecimal luongNgayCong) {
//        this.luongNgayCong = luongNgayCong;
//    }
//
//    public BigDecimal getLuongOt() {
//        return luongOt;
//    }
//
//    public void setLuongOt(BigDecimal luongOt) {
//        this.luongOt = luongOt;
//    }
//
//    public BigDecimal getTongPhuCap() {
//        return tongPhuCap;
//    }
//
//    public void setTongPhuCap(BigDecimal tongPhuCap) {
//        this.tongPhuCap = tongPhuCap;
//    }
//
//    public BigDecimal getThuongDoanhSo() {
//        return thuongDoanhSo;
//    }
//
//    public void setThuongDoanhSo(BigDecimal thuongDoanhSo) {
//        this.thuongDoanhSo = thuongDoanhSo;
//    }
//
//    public BigDecimal getKhauTruBhxh() {
//        return khauTruBhxh;
//    }
//
//    public void setKhauTruBhxh(BigDecimal khauTruBhxh) {
//        this.khauTruBhxh = khauTruBhxh;
//    }
//
//    public BigDecimal getKhauTruBhyt() {
//        return khauTruBhyt;
//    }
//
//    public void setKhauTruBhyt(BigDecimal khauTruBhyt) {
//        this.khauTruBhyt = khauTruBhyt;
//    }
//
//    public BigDecimal getKhauTruBhtn() {
//        return khauTruBhtn;
//    }
//
//    public void setKhauTruBhtn(BigDecimal khauTruBhtn) {
//        this.khauTruBhtn = khauTruBhtn;
//    }
//
//    public BigDecimal getDoanPhi() {
//        return doanPhi;
//    }
//
//    public void setDoanPhi(BigDecimal doanPhi) {
//        this.doanPhi = doanPhi;
//    }
//
//    public BigDecimal getThueTncn() {
//        return thueTncn;
//    }
//
//    public void setThueTncn(BigDecimal thueTncn) {
//        this.thueTncn = thueTncn;
//    }
//
//    public BigDecimal getTamUng() {
//        return tamUng;
//    }
//
//    public void setTamUng(BigDecimal tamUng) {
//        this.tamUng = tamUng;
//    }
//
//    public BigDecimal getThucLinh() {
//        return thucLinh;
//    }
//
//    public void setThucLinh(BigDecimal thucLinh) {
//        this.thucLinh = thucLinh;
//    }
//}



package QLNV.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bang_luong_thang")
public class BangLuongThang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "thang")
    private Integer thang;

    @Column(name = "nam")
    private Integer nam;

    // Mapping khóa ngoại nhan_vien_id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nhan_vien_id")
    @JsonIgnoreProperties({"bangLuongThangs", "hibernateLazyInitializer", "handler"})
    private NhanVien nhanVien;


    @Column(name = "luong_ngay_cong")
    private BigDecimal luongNgayCong;

    @Column(name = "luong_ot")
    private BigDecimal luongOt;

    @Column(name = "tong_phu_cap")
    private BigDecimal tongPhuCap;

    @Column(name = "thuong_doanh_so")
    private BigDecimal thuongDoanhSo;

    @Column(name = "khau_tru_bhxh")
    private BigDecimal khauTruBhxh;

    @Column(name = "khau_tru_bhyt")
    private BigDecimal khauTruBhyt;

    @Column(name = "khau_tru_bhtn")
    private BigDecimal khauTruBhtn;

    @Column(name = "doan_phi")
    private BigDecimal doanPhi;

    @Column(name = "thue_tncn")
    private BigDecimal thueTncn;

    @Column(name = "tam_ung")
    private BigDecimal tamUng;

    @Column(name = "thuc_linh")
    private BigDecimal thucLinh;

    // --- CONSTRUCTOR ---
    public BangLuongThang() {}

    // --- GETTER & SETTER (Giữ nguyên logic cũ) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getThang() { return thang; }
    public void setThang(Integer thang) { this.thang = thang; }

    public Integer getNam() { return nam; }
    public void setNam(Integer nam) { this.nam = nam; }

    public NhanVien getNhanVien() { return nhanVien; }
    public void setNhanVien(NhanVien nhanVien) { this.nhanVien = nhanVien; }

    public BigDecimal getLuongNgayCong() { return luongNgayCong; }
    public void setLuongNgayCong(BigDecimal luongNgayCong) { this.luongNgayCong = luongNgayCong; }

    public BigDecimal getLuongOt() { return luongOt; }
    public void setLuongOt(BigDecimal luongOt) { this.luongOt = luongOt; }

    public BigDecimal getTongPhuCap() { return tongPhuCap; }
    public void setTongPhuCap(BigDecimal tongPhuCap) { this.tongPhuCap = tongPhuCap; }

    public BigDecimal getThuongDoanhSo() { return thuongDoanhSo; }
    public void setThuongDoanhSo(BigDecimal thuongDoanhSo) { this.thuongDoanhSo = thuongDoanhSo; }

    public BigDecimal getKhauTruBhxh() { return khauTruBhxh; }
    public void setKhauTruBhxh(BigDecimal khauTruBhxh) { this.khauTruBhxh = khauTruBhxh; }

    public BigDecimal getKhauTruBhyt() { return khauTruBhyt; }
    public void setKhauTruBhyt(BigDecimal khauTruBhyt) { this.khauTruBhyt = khauTruBhyt; }

    public BigDecimal getKhauTruBhtn() { return khauTruBhtn; }
    public void setKhauTruBhtn(BigDecimal khauTruBhtn) { this.khauTruBhtn = khauTruBhtn; }

    public BigDecimal getDoanPhi() { return doanPhi; }
    public void setDoanPhi(BigDecimal doanPhi) { this.doanPhi = doanPhi; }

    public BigDecimal getThueTncn() { return thueTncn; }
    public void setThueTncn(BigDecimal thueTncn) { this.thueTncn = thueTncn; }

    public BigDecimal getTamUng() { return tamUng; }
    public void setTamUng(BigDecimal tamUng) { this.tamUng = tamUng; }

    public BigDecimal getThucLinh() { return thucLinh; }
    public void setThucLinh(BigDecimal thucLinh) { this.thucLinh = thucLinh; }
}
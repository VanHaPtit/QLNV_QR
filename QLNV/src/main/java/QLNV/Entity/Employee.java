package QLNV.Entity;

import QLNV.Entity.Enum.EmployeeStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "nhan_vien")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String maNv;

    private String hoTen;
    private LocalDate ngaySinh;
    private String gioiTinh;

    @Column(name = "avatar_url", columnDefinition = "TEXT")
    private String avatarUrl;

    @Column(name = "face_data", columnDefinition = "TEXT")
    private String faceData;

    private String cccd;
    private LocalDate ngayCap;
    private String noiCap;

    @Column(columnDefinition = "TEXT")
    private String diaChiThuongTru;

    @Column(columnDefinition = "TEXT")
    private String diaChiHienTai;

    private String soDienThoai;
    private String emailCongTy;
    private String emailCaNhan;

    private String maSoThue;
    private String soTaiKhoan;
    private String nganHang;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus trangThai;

    @ManyToOne
    @JoinColumn(name = "phong_ban_id")
    private Department phongBan;

    @ManyToOne
    @JoinColumn(name = "chuc_vu_id")
    private Position chucVu;

    @OneToMany(mappedBy = "nhanVien")
    @JsonIgnoreProperties("nhanVien")
    private List<MonthlyPayroll> bangLuongThangs;


    public Employee(Long id, String maNv, String hoTen, LocalDate ngaySinh, String gioiTinh, String avatarUrl, String faceData, String cccd, LocalDate ngayCap, String noiCap, String diaChiThuongTru, String diaChiHienTai, String soDienThoai, String emailCongTy, String emailCaNhan, String maSoThue, String soTaiKhoan, String nganHang, EmployeeStatus trangThai, Department phongBan, Position chucVu) {
        this.id = id;
        this.maNv = maNv;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.avatarUrl = avatarUrl;
        this.faceData = faceData;
        this.cccd = cccd;
        this.ngayCap = ngayCap;
        this.noiCap = noiCap;
        this.diaChiThuongTru = diaChiThuongTru;
        this.diaChiHienTai = diaChiHienTai;
        this.soDienThoai = soDienThoai;
        this.emailCongTy = emailCongTy;
        this.emailCaNhan = emailCaNhan;
        this.maSoThue = maSoThue;
        this.soTaiKhoan = soTaiKhoan;
        this.nganHang = nganHang;
        this.trangThai = trangThai;
        this.phongBan = phongBan;
        this.chucVu = chucVu;
    }

    public List<MonthlyPayroll> getBangLuongThangs() {
        return bangLuongThangs;
    }

    public void setBangLuongThangs(List<MonthlyPayroll> bangLuongThangs) {
        this.bangLuongThangs = bangLuongThangs;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }

    public Employee(Long id, String maNv, String hoTen, LocalDate ngaySinh, String gioiTinh, String cccd, LocalDate ngayCap, String noiCap, String diaChiThuongTru, String diaChiHienTai, String soDienThoai, String emailCongTy, String emailCaNhan, String maSoThue, String soTaiKhoan, String nganHang, EmployeeStatus trangThai, Department phongBan, Position chucVu) {
        this.id = id;
        this.maNv = maNv;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.cccd = cccd;
        this.ngayCap = ngayCap;
        this.noiCap = noiCap;
        this.diaChiThuongTru = diaChiThuongTru;
        this.diaChiHienTai = diaChiHienTai;
        this.soDienThoai = soDienThoai;
        this.emailCongTy = emailCongTy;
        this.emailCaNhan = emailCaNhan;
        this.maSoThue = maSoThue;
        this.soTaiKhoan = soTaiKhoan;
        this.nganHang = nganHang;
        this.trangThai = trangThai;
        this.phongBan = phongBan;
        this.chucVu = chucVu;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public LocalDate getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(LocalDate ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public String getDiaChiThuongTru() {
        return diaChiThuongTru;
    }

    public void setDiaChiThuongTru(String diaChiThuongTru) {
        this.diaChiThuongTru = diaChiThuongTru;
    }

    public String getDiaChiHienTai() {
        return diaChiHienTai;
    }

    public void setDiaChiHienTai(String diaChiHienTai) {
        this.diaChiHienTai = diaChiHienTai;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmailCongTy() {
        return emailCongTy;
    }

    public void setEmailCongTy(String emailCongTy) {
        this.emailCongTy = emailCongTy;
    }

    public String getEmailCaNhan() {
        return emailCaNhan;
    }

    public void setEmailCaNhan(String emailCaNhan) {
        this.emailCaNhan = emailCaNhan;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public EmployeeStatus getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(EmployeeStatus trangThai) {
        this.trangThai = trangThai;
    }

    public Department getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(Department phongBan) {
        this.phongBan = phongBan;
    }

    public Position getChucVu() {
        return chucVu;
    }

    public void setChucVu(Position chucVu) {
        this.chucVu = chucVu;
    }
}


package QLNV.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quy_phep_nam")
public class QuyPhepNam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NhanVien nhanVien;

    private Integer nam;
    private Float tongPhepDuocCap;
    private Float phepDaNghi;
    private Float phepTonNamTruoc;

    public QuyPhepNam(Long id, NhanVien nhanVien, Integer nam, Float tongPhepDuocCap, Float phepDaNghi, Float phepTonNamTruoc) {
        this.id = id;
        this.nhanVien = nhanVien;
        this.nam = nam;
        this.tongPhepDuocCap = tongPhepDuocCap;
        this.phepDaNghi = phepDaNghi;
        this.phepTonNamTruoc = phepTonNamTruoc;
    }

    public QuyPhepNam() {
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

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Float getTongPhepDuocCap() {
        return tongPhepDuocCap;
    }

    public void setTongPhepDuocCap(Float tongPhepDuocCap) {
        this.tongPhepDuocCap = tongPhepDuocCap;
    }

    public Float getPhepDaNghi() {
        return phepDaNghi;
    }

    public void setPhepDaNghi(Float phepDaNghi) {
        this.phepDaNghi = phepDaNghi;
    }

    public Float getPhepTonNamTruoc() {
        return phepTonNamTruoc;
    }

    public void setPhepTonNamTruoc(Float phepTonNamTruoc) {
        this.phepTonNamTruoc = phepTonNamTruoc;
    }
}


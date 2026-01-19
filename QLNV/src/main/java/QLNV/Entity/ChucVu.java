package QLNV.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chuc_vu")
public class ChucVu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenChucVu;
    private Integer capBac;

    public ChucVu(Long id, String tenChucVu, Integer capBac) {
        this.id = id;
        this.tenChucVu = tenChucVu;
        this.capBac = capBac;
    }

    public ChucVu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public Integer getCapBac() {
        return capBac;
    }

    public void setCapBac(Integer capBac) {
        this.capBac = capBac;
    }
}


package QLNV.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maQuyen;

    public Permission(Long id, String maQuyen) {
        this.id = id;
        this.maQuyen = maQuyen;
    }

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }
}


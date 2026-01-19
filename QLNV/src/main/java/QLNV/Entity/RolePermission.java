package QLNV.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role_permissions")
public class RolePermission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;

    public RolePermission(Long id, Role role, Permission permission) {
        this.id = id;
        this.role = role;
        this.permission = permission;
    }

    public RolePermission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}


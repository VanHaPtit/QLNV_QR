package QLNV.Entity.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    private Set<String> role;

    private String  nhanVienId;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public String getNhanVienId() {
        return nhanVienId;
    }

    public void setNhanVienId(String nhanVienId) {
        this.nhanVienId = nhanVienId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}

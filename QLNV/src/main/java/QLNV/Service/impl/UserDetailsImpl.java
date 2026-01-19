package QLNV.Service.impl;

import QLNV.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String username;
    private String fullName; // Thêm trường này để hiển thị trên React Header

    private Long nhanVienId;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String fullName, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.authorities = authorities;
    }


    public Long getNhanVienId() {
        return nhanVienId;
    }



    public void setNhanVienId(Long nhanVienId) {
        this.nhanVienId = nhanVienId;
    }

    public static UserDetailsImpl build(User user) {
        // Vì getRole() trả về 1 đối tượng, ta không dùng stream() mà tạo trực tiếp authority
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getTenRole());

        // Bọc authority vào một danh sách duy nhất (Singleton List)
        List<GrantedAuthority> authorities = Collections.singletonList(authority);

        // Lấy tên thật từ NhanVien (nếu có), nếu không có thì dùng username
        String fullName = (user.getNhanVien() != null) ? user.getNhanVien().getHoTen() : user.getUsername();

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                fullName,
                user.getPasswordHash(), // Khớp với trường passwordHash trong Entity của bạn
                authorities);
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String getPassword() { return password; }
    @Override
    public String getUsername() { return username; }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
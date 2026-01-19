package QLNV.Controller;

import QLNV.Entity.NhanVien;
import QLNV.Entity.Role;
import QLNV.Entity.User;
import QLNV.Entity.request.LoginRequest;
import QLNV.Entity.request.SignupRequest;
import QLNV.Entity.response.MessageResponse;
import QLNV.Entity.response.UserInfoResponse;
import QLNV.Repository.NhanVienRepository;
import QLNV.Repository.RoleRepository;
import QLNV.Repository.UserRepository;
import QLNV.Service.impl.UserDetailsImpl;
import QLNV.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // 1. Tạo Cookie chứa JWT (để dùng header Set-Cookie)
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        // 2. Lấy chuỗi JWT string từ Cookie hoặc tạo mới để gửi về Body
        // Lưu ý: Nếu jwtUtils của bạn có hàm generateTokenFromUsername thì dùng hàm đó cho gọn
        String jwtToken = jwtCookie.getValue();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // 3. Trả về Body bao gồm chuỗi token để React lưu vào localStorage
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(
                        jwtToken,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getFullName(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        if (signUpRequest.getNhanVienId() != null) {
            NhanVien nv = nhanVienRepository.findByMaNv(signUpRequest.getNhanVienId())
                    .orElseThrow(() -> new RuntimeException("Lỗi: Không tìm thấy nhân viên có mã: " + signUpRequest.getNhanVienId()));
            user.setNhanVien(nv);
        }

        Set<String> strRoles = signUpRequest.getRole();
        Role role;

        if (strRoles == null || strRoles.isEmpty()) {
            role = roleRepository.findByTenRole("USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role USER is not found."));
        } else {
            String roleStr = strRoles.iterator().next();
            role = roleRepository.findByTenRole(roleStr.toUpperCase())
                    .orElseThrow(() -> new RuntimeException("Error: Role " + roleStr + " is not found."));
        }

        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
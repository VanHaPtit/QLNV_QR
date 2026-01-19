package QLNV.Config;

import QLNV.Service.impl.UserDetailsServiceImpl;
import QLNV.security.AuthEntryPointJwt;
import QLNV.security.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity // Cho phép phân quyền bằng @PreAuthorize trong Controller
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    // --- 1. Filter xử lý Token JWT ---
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    // --- 2. Cấu hình kiểm tra Tài khoản & Mật khẩu ---
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // --- 5. CHUỖI BỘ LỌC BẢO MẬT (Security Filter Chain) ---
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Cấu hình CORS: Cho phép React kết nối và gửi Cookie
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:5173"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true); // Quan trọng: Cho phép gửi JWT Cookie
                    return config;
                }))

                // Vô hiệu hóa CSRF vì chúng ta dùng Stateless JWT
                .csrf(csrf -> csrf.disable())

                // Xử lý lỗi 401 Unauthorized khi truy cập trái phép
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))

                // Không tạo Session trên Server (Stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Phân quyền URL
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Cho phép Login/Register tự do
                        .anyRequest().authenticated()               // Các API khác (Hợp đồng, Nhân viên...) phải đăng nhập
                );

        // Đăng ký Provider xác thực
        http.authenticationProvider(authenticationProvider());

        // Kiểm tra JWT Filter trước khi thực hiện các bước khác
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
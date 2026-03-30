package QLNV.DTO.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class LaborContractResponse {
    private Long id;
    private String soHopDong;
    private String maNv;
    private String hoTen;
    private String loaiHopDong;
    private LocalDate ngayHieuLuc;
    private LocalDate ngayHetHan;
    private BigDecimal luongCoBan;
    private BigDecimal luongDongBhxh;
    private String fileDinhKemUrl;
    private String tinhTrangHieuLuc; // "Còn hạn", "Hết hạn", "Sắp hết hạn"
}

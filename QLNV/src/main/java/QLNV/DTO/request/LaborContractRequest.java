package QLNV.DTO.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LaborContractRequest {
    private String soHopDong;
    private Long nhanVienId;
    private String loaiHopDong;
    private LocalDate ngayHieuLuc;
    private LocalDate ngayHetHan;
    private BigDecimal luongCoBan;
    private BigDecimal luongDongBhxh;
}

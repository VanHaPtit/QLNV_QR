package QLNV.DTO.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestResponse {
    private Long id;
    private String loaiNghi;
    private LocalDate tuNgay;
    private LocalDate denNgay;
    private String trangThai;
    private String tenNguoiDuyet;
}

package QLNV.Service;

import QLNV.DTO.response.AttendanceDetailResponse;
import QLNV.Entity.AttendanceDetail;

import java.util.List;

public interface AttendanceDetailService {

    public List<AttendanceDetailResponse> findAll() ;

    public AttendanceDetailResponse findById(Long id) ;

    public AttendanceDetail save(AttendanceDetail c) ;


    AttendanceDetailResponse update(Long id, AttendanceDetailResponse data);

    public AttendanceDetailResponse quetMaDiemDanh(Long nhanVienId, String token);
    public void delete(Long id) ;

    public List<AttendanceDetailResponse> findByNhanVienId(Long nhanVienId) ;

}

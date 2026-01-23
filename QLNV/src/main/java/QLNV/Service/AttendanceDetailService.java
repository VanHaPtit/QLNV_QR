package QLNV.Service;

import QLNV.Entity.AttendanceDetail;

import java.util.List;

public interface AttendanceDetailService {

    public List<AttendanceDetail> findAll() ;

    public AttendanceDetail findById(Long id) ;

    public AttendanceDetail save(AttendanceDetail c) ;


    public AttendanceDetail quetMaDiemDanh(Long nhanVienId, String token);
    public void delete(Long id) ;

    public List<AttendanceDetail> findByNhanVienId(Long nhanVienId) ;

}

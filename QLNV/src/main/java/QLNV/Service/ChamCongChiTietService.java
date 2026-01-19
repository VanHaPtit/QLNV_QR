package QLNV.Service;

import QLNV.Entity.ChamCongChiTiet;

import java.util.List;

public interface ChamCongChiTietService {

    public List<ChamCongChiTiet> findAll() ;

    public ChamCongChiTiet findById(Long id) ;

    public ChamCongChiTiet save(ChamCongChiTiet c) ;


    public ChamCongChiTiet quetMaDiemDanh(Long nhanVienId, String token);
    public void delete(Long id) ;

    public List<ChamCongChiTiet> findByNhanVienId(Long nhanVienId) ;

}

package QLNV.Service;

import QLNV.Entity.PhongBan;

import java.util.List;
import java.util.Optional;

public interface PhongBanService {
    public List<PhongBan> getAll() ;
    public Optional<PhongBan> getPhongBan(Long id) ;
    public PhongBan savePhongBan(PhongBan phongBan) ;
    public void deletePhongBan(Long id) ;
    PhongBan updatePhongBan(Long id, PhongBan data);
    public List<PhongBan> searchPhongBan(String keyword) ;
}

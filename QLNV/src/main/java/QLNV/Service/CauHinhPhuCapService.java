package QLNV.Service;

import QLNV.Entity.CauHinhPhuCap;

import java.util.List;

public interface CauHinhPhuCapService {
    List<CauHinhPhuCap> getAll();
    CauHinhPhuCap getById(Long id);
    CauHinhPhuCap create(CauHinhPhuCap phuCap);
    CauHinhPhuCap update(Long id, CauHinhPhuCap phuCapUpdate);
    void delete(Long id);
}

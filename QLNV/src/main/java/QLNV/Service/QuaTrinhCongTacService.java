package QLNV.Service;

import QLNV.Entity.QuaTrinhCongTac;

import java.util.List;

public interface QuaTrinhCongTacService {

    List<QuaTrinhCongTac> getAll();

    QuaTrinhCongTac getById(Long id);

    QuaTrinhCongTac create(QuaTrinhCongTac qtct);

    QuaTrinhCongTac update(Long id, QuaTrinhCongTac qtct);

    void delete(Long id);

    List<QuaTrinhCongTac> findByNhanVien(Long nhanVienId);
}

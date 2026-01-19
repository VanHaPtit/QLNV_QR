package QLNV.Repository;


import QLNV.Entity.QuaTrinhCongTac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuaTrinhCongTacRepository extends JpaRepository<QuaTrinhCongTac, Long> {

    List<QuaTrinhCongTac> findByNhanVienId(Long nhanVienId);
}

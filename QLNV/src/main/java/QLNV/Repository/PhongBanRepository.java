package QLNV.Repository;

import QLNV.Entity.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan , Long> {
    List<PhongBan> findByTenPhongBanContainingIgnoreCase(String keyword);
}

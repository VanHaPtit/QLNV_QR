package QLNV.Repository;

import QLNV.Entity.CaLamViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaLamViecRepository extends JpaRepository<CaLamViec, Long> {

}

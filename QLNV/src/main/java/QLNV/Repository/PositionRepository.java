package QLNV.Repository;

import QLNV.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByTenChucVuContainingIgnoreCase(String keyword);
}

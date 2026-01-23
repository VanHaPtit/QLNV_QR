package QLNV.Service;

import QLNV.Entity.Position;

import java.util.List;
import java.util.Optional;

public interface PositionService {
    List<Position> getAll();

    Optional<Position> getChucVu(Long id);

    Position saveChucVu(Position chucVu);

    void deleteChucVu(Long id);

    Position updateChucVu(Long id, Position data);

    List<Position> searchChucVu(String keyword);
}

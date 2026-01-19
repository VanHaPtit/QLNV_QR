package QLNV.Service;

import QLNV.Entity.CaLamViec;

import java.util.List;
import java.util.Optional;

public interface CaLamViecService {

    List<CaLamViec> getAll();
    Optional<CaLamViec> getById(Long id);
    CaLamViec save(CaLamViec caLamViec);
    CaLamViec update(Long id, CaLamViec caLamViec);
    void delete(Long id);
}

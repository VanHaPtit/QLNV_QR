package QLNV.Service;

import QLNV.Entity.Dependent;

import java.util.List;

public interface DependentService {

    List<Dependent> getAll();
    Dependent getById(Long id);
    Dependent create(Dependent data);
    Dependent update(Long id, Dependent data);
    void delete(Long id);

    // Thêm filter
    List<Dependent> findByNhanVien(Long nvId);
    List<Dependent> findByMaSoThue(String mst);
}

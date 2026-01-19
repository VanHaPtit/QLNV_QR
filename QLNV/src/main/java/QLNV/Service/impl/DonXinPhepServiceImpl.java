package QLNV.Service.impl;

import QLNV.Entity.DonXinPhep;
import QLNV.Entity.Enum.TrangThaiDon;
import QLNV.Entity.NhanVien;
import QLNV.Repository.DonXinPhepRepository;
import QLNV.Repository.NhanVienRepository;
import QLNV.Service.DonXinPhepService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonXinPhepServiceImpl implements DonXinPhepService {

    private final DonXinPhepRepository repo;
    private final NhanVienRepository nhanVienRepo;

    public DonXinPhepServiceImpl(DonXinPhepRepository repo, NhanVienRepository nhanVienRepo) {
        this.repo = repo;
        this.nhanVienRepo = nhanVienRepo;
    }

    @Override
    public List<DonXinPhep> getAll() {
        return repo.findAll();
    }

    @Override
    public DonXinPhep getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn xin phép ID=" + id));
    }

    @Override
    public DonXinPhep create(DonXinPhep data) {
        data.setTrangThai(TrangThaiDon.CHO_DUYET);
        return repo.save(data);
    }

    @Override
    public DonXinPhep update(Long id, DonXinPhep data) {
        DonXinPhep old = getById(id);

        old.setNhanVien(data.getNhanVien());
        old.setTuNgay(data.getTuNgay());
        old.setDenNgay(data.getDenNgay());
        old.setLoaiNghi(data.getLoaiNghi());
        old.setTrangThai(data.getTrangThai());
        old.setNguoiDuyet(data.getNguoiDuyet());

        return repo.save(old);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<DonXinPhep> findByNhanVien(Long nvId) {
        return repo.findByNhanVien_Id(nvId);
    }

    @Override
    public List<DonXinPhep> findByTrangThai(TrangThaiDon status) {
        return repo.findByTrangThai(status);
    }

    // ==========================
    // CHỨC NĂNG DUYỆT ĐƠN
    // ==========================

    @Override
    public DonXinPhep approveDon(Long id, Long nguoiDuyetId) {
        DonXinPhep don = getById(id);

        NhanVien nguoiDuyet = nhanVienRepo.findById(nguoiDuyetId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người duyệt"));

        don.setTrangThai(TrangThaiDon.DA_DUYET);
        don.setNguoiDuyet(nguoiDuyet);

        return repo.save(don);
    }

    @Override
    public DonXinPhep rejectDon(Long id, Long nguoiDuyetId) {
        DonXinPhep don = getById(id);

        NhanVien nguoiDuyet = nhanVienRepo.findById(nguoiDuyetId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người duyệt"));

        don.setTrangThai(TrangThaiDon.TU_CHOI);
        don.setNguoiDuyet(nguoiDuyet);

        return repo.save(don);
    }
}

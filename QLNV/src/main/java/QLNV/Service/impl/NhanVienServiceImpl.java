package QLNV.Service.impl;

import QLNV.Entity.ChucVu;
import QLNV.Entity.NhanVien;
import QLNV.Entity.PhongBan;
import QLNV.Repository.ChucVuRepository;
import QLNV.Repository.NhanVienRepository;
import QLNV.Repository.PhongBanRepository;
import QLNV.Service.CloudinaryService;
import QLNV.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private PhongBanRepository phongBanRepository;

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public Optional<NhanVien> getById(Long id) {
        return nhanVienRepository.findById(id);
    }

    @Override
    public NhanVien save(NhanVien nv, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            // Upload lên Cloudinary
            String avatarUrl = cloudinaryService.uploadImage(file);
            // Gán URL trả về vào đối tượng nhân viên
            nv.setAvatarUrl(avatarUrl);
        }

        // Kiểm tra phòng ban
        if (nv.getPhongBan() != null && nv.getPhongBan().getId() != null) {
            PhongBan pb = phongBanRepository.findById(nv.getPhongBan().getId())
                    .orElseThrow(() -> new RuntimeException("Phòng ban không tồn tại"));
            nv.setPhongBan(pb);
        }

        // Kiểm tra chức vụ
        if (nv.getChucVu() != null && nv.getChucVu().getId() != null) {
            ChucVu cv = chucVuRepository.findById(nv.getChucVu().getId())
                    .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại"));
            nv.setChucVu(cv);
        }

        return nhanVienRepository.save(nv);
    }

    @Override
    public NhanVien update(Long id, NhanVien nv, MultipartFile file) throws IOException {
        NhanVien existing = nhanVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));

        if (file != null && !file.isEmpty()) {
            String newAvatarUrl = cloudinaryService.uploadImage(file);
            existing.setAvatarUrl(newAvatarUrl);
        } else {
             existing.setAvatarUrl(existing.getAvatarUrl());
        }
        existing.setMaNv(nv.getMaNv());
        existing.setFaceData(nv.getFaceData());
        existing.setHoTen(nv.getHoTen());
        existing.setNgaySinh(nv.getNgaySinh());
        existing.setGioiTinh(nv.getGioiTinh());
        existing.setCccd(nv.getCccd());
        existing.setNgayCap(nv.getNgayCap());
        existing.setNoiCap(nv.getNoiCap());
        existing.setDiaChiThuongTru(nv.getDiaChiThuongTru());
        existing.setDiaChiHienTai(nv.getDiaChiHienTai());
        existing.setSoDienThoai(nv.getSoDienThoai());
        existing.setEmailCongTy(nv.getEmailCongTy());
        existing.setEmailCaNhan(nv.getEmailCaNhan());
        existing.setMaSoThue(nv.getMaSoThue());
        existing.setSoTaiKhoan(nv.getSoTaiKhoan());
        existing.setNganHang(nv.getNganHang());
        existing.setTrangThai(nv.getTrangThai());
        if (nv.getPhongBan() != null) {
            PhongBan pb = phongBanRepository.findById(nv.getPhongBan().getId())
                    .orElseThrow(() -> new RuntimeException("Phòng ban không tồn tại"));
            existing.setPhongBan(pb);
        }

        if (nv.getChucVu() != null) {
            ChucVu cv = chucVuRepository.findById(nv.getChucVu().getId())
                    .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại"));
            existing.setChucVu(cv);
        }

        return nhanVienRepository.save(existing);
    }


    @Override
    public void delete(Long id) {
        if (!nhanVienRepository.existsById(id)) {
            throw new RuntimeException("Nhân viên không tồn tại");
        }
        nhanVienRepository.deleteById(id);
    }

    @Override
    public NhanVien findByMaNv(String maNv) {
        return nhanVienRepository.findByMaNv(maNv)
                .orElse(null);
    }
}


package QLNV.Service.impl;

import QLNV.Entity.MonthlyPayroll;
import QLNV.Entity.Employee;
import QLNV.Repository.MonthlyPayrollRepository;
import QLNV.Repository.EmployeeRepository;
import QLNV.Service.MonthlyPayrollService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonthlyPayrollServiceImpl implements MonthlyPayrollService {

    @Autowired
    private MonthlyPayrollRepository repo;

    @Autowired
    private EmployeeRepository nhanVienRepository ;

    @Override
    public List<MonthlyPayroll> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<MonthlyPayroll> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public MonthlyPayroll save(MonthlyPayroll bangLuongThang) {
        return repo.save(bangLuongThang);
    }

    @Override
    public MonthlyPayroll update(Long id, MonthlyPayroll data) {
        return repo.findById(id).map(bl -> {

            bl.setThang(data.getThang());
            bl.setNam(data.getNam());
            bl.setNhanVien(data.getNhanVien());

            bl.setLuongNgayCong(data.getLuongNgayCong());
            bl.setLuongOt(data.getLuongOt());
            bl.setTongPhuCap(data.getTongPhuCap());
            bl.setThuongDoanhSo(data.getThuongDoanhSo());

            bl.setKhauTruBhxh(data.getKhauTruBhxh());
            bl.setKhauTruBhyt(data.getKhauTruBhyt());
            bl.setKhauTruBhtn(data.getKhauTruBhtn());
            bl.setDoanPhi(data.getDoanPhi());
            bl.setThueTncn(data.getThueTncn());

            bl.setTamUng(data.getTamUng());
            bl.setThucLinh(data.getThucLinh());

            return repo.save(bl);

        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<MonthlyPayroll> findByNhanVien(Long nvId) {
        return repo.findByNhanVienId(nvId);
    }

    @Override
    public List<MonthlyPayroll> findByThangNam(Integer thang, Integer nam) {
        return repo.findByThang(thang)
                .stream()
                .filter(x -> x.getNam().equals(nam))
                .toList();
    }

    @Override
    public List<MonthlyPayroll> findByNhanVienAndThangNam(Long nvId, Integer thang, Integer nam) {
        return repo.findByNhanVienIdAndThangAndNam(nvId, thang, nam);
    }

    @Override
    public void importFromExcel(MultipartFile file) throws Exception {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        List<String> errorMessages = new ArrayList<>();
        // Chạy từ dòng 1 để bỏ qua tiêu đề
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null) continue;

            try {
                // 1. Đọc dữ liệu định danh
                String maNV = row.getCell(0).getStringCellValue();
                int thang = (int) row.getCell(2).getNumericCellValue(); // Cột Tháng index 2
                int nam = (int) row.getCell(3).getNumericCellValue();   // Cột Năm index 3

                // 2. Tìm kiếm nhân viên
                Optional<Employee> nvOptional = nhanVienRepository.findByMaNv(maNV);

                if (nvOptional.isPresent()) {
                    Employee nv = nvOptional.get();
                    MonthlyPayroll bl = new MonthlyPayroll();

                    bl.setNhanVien(nv);
                    bl.setThang(thang);
                    bl.setNam(nam);

                    // 3. Đọc và ánh xạ các cột tiền (Sử dụng hàm phụ để tránh lỗi ô trống)
                    bl.setLuongOt(getCellValueAsBigDecimal(row.getCell(4)));
                    bl.setTongPhuCap(getCellValueAsBigDecimal(row.getCell(5)));
                    bl.setThuongDoanhSo(getCellValueAsBigDecimal(row.getCell(6)));

                    bl.setKhauTruBhxh(getCellValueAsBigDecimal(row.getCell(7)));
                    bl.setKhauTruBhyt(getCellValueAsBigDecimal(row.getCell(8)));
                    bl.setKhauTruBhtn(getCellValueAsBigDecimal(row.getCell(9)));
                    bl.setDoanPhi(getCellValueAsBigDecimal(row.getCell(10)));
                    bl.setThueTncn(getCellValueAsBigDecimal(row.getCell(11)));

                    bl.setTamUng(getCellValueAsBigDecimal(row.getCell(13)));
                    bl.setThucLinh(getCellValueAsBigDecimal(row.getCell(14)));

                    // Lưu ý: luongNgayCong không có trong file Excel image_245cc4.png
                    // Bạn có thể mặc định là 0 hoặc tự tính toán nếu cần
                    bl.setLuongNgayCong(getCellValueAsBigDecimal(row.getCell(12)));

                    repo.save(bl);
                }else{
                    errorMessages.add("Dòng " + (i + 1) + ": Không tìm thấy nhân viên có mã [" + maNV + "]");
                }
            } catch (Exception e) {
                // Log lỗi cho từng dòng nếu dữ liệu sai định dạng
                System.err.println("Lỗi tại dòng " + (i + 1) + ": " + e.getMessage());
            }
        }
        workbook.close();
    }

    /**
     * Hàm hỗ trợ chuyển đổi giá trị Cell sang BigDecimal an toàn
     */
    private BigDecimal getCellValueAsBigDecimal(Cell cell) {
        if (cell == null) return BigDecimal.ZERO;
        try {
            if (cell.getCellType() == CellType.NUMERIC) {
                return BigDecimal.valueOf(cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.STRING) {
                String val = cell.getStringCellValue().replaceAll("[^\\d.]", "");
                return val.isEmpty() ? BigDecimal.ZERO : new BigDecimal(val);
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }
}
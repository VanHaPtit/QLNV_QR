package QLNV.Controller;

import QLNV.DTO.request.LaborContractRequest;
import QLNV.DTO.response.ApiResponse;
import QLNV.DTO.response.LaborContractResponse;
import QLNV.Service.LaborContractService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/hop-dong")
@CrossOrigin(origins = "*")
public class LaborContractController {

    private final LaborContractService service;

    @Autowired
    private ObjectMapper objectMapper;

    public LaborContractController(LaborContractService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<LaborContractResponse> create(
            @RequestPart("hopDong") String requestJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            LaborContractRequest request = objectMapper.readValue(requestJson, LaborContractRequest.class);
            return ApiResponse.success(service.create(request, file));
        } catch (IOException e) {
            return ApiResponse.error(400, "Dữ liệu không hợp lệ: " + e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<LaborContractResponse> update(
            @PathVariable Long id,
            @RequestPart("hopDong") String requestJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            LaborContractRequest request = objectMapper.readValue(requestJson, LaborContractRequest.class);
            return ApiResponse.success(service.update(id, request, file));
        } catch (IOException e) {
            return ApiResponse.error(400, "Cập nhật thất bại: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<LaborContractResponse> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id));
    }

    @GetMapping
    public ApiResponse<List<LaborContractResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @GetMapping("/nhan-vien/{nhanVienId}")
    public ApiResponse<List<LaborContractResponse>> getByNhanVien(@PathVariable Long nhanVienId) {
        return ApiResponse.success(service.getByNhanVienId(nhanVienId));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success("Đã xóa hợp đồng thành công!");
    }
}
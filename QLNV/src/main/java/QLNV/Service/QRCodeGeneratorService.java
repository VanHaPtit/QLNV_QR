package QLNV.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class QRCodeGeneratorService {

    private final String SECRET_KEY = "HRM_PROJECT_SECRET_KEY_2026";

    /**
     * Sinh Token động dựa trên thời gian thực (7 giây đổi 1 lần)
     */
    public String generateDynamicToken() {
        // Chia thời gian hiện tại cho 7000ms để tạo ra một chỉ số thay đổi sau mỗi 7s
        long timeIndex = System.currentTimeMillis() / 7000;
        return DigestUtils.sha256Hex(SECRET_KEY + timeIndex);
    }

    /**
     * Tạo mã QR dưới dạng chuỗi Base64 để hiển thị trực tiếp trên thẻ <img> của React
     */
    public String getQRCodeBase64() throws Exception {
        String token = generateDynamicToken();
        // Đường dẫn này sẽ là trang mà điện thoại nhân viên mở ra khi quét mã
        String qrContent = "http://your-app-url.com/scan-attendance?token=" + token;

        int width = 400;
        int height = 400;

        BitMatrix matrix = new MultiFormatWriter().encode(
                qrContent, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);

        byte[] pngData = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(pngData);
    }

    /**
     * Kiểm tra token nhân viên gửi lên có khớp với mã hiện tại (hoặc mã vừa hết hạn 7s trước) không
     */
    public boolean isValidToken(String clientToken) {
        String currentToken = generateDynamicToken();

        // Tính thêm token của 7 giây trước để tránh sai lệch do độ trễ mạng
        long previousTimeIndex = (System.currentTimeMillis() / 7000) - 1;
        String previousToken = DigestUtils.sha256Hex(SECRET_KEY + previousTimeIndex);

        return clientToken.equals(currentToken) || clientToken.equals(previousToken);
    }
}

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

    public String generateDynamicToken() {
        long timeIndex = System.currentTimeMillis() / 7000;
        return DigestUtils.sha256Hex(SECRET_KEY + timeIndex);
    }

    public String getQRCodeBase64() throws Exception {
        String token = generateDynamicToken();
        String qrContent = "http://localhost:3000//scan-attendance?token=" + token;

        int width = 400;
        int height = 400;

        BitMatrix matrix = new MultiFormatWriter().encode(
                qrContent, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);

        byte[] pngData = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(pngData);
    }

    public boolean isValidToken(String clientToken) {
        String currentToken = generateDynamicToken();

        long previousTimeIndex = (System.currentTimeMillis() / 7000) - 1;
        String previousToken = DigestUtils.sha256Hex(SECRET_KEY + previousTimeIndex);

        return clientToken.equals(currentToken) || clientToken.equals(previousToken);
    }
}

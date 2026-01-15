package com.javaweb.service;

import com.javaweb.entity.ContractEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfService {

    public String generateContractPdf(ContractEntity contract) {
        try {
            // Ví dụ đường dẫn lưu file PDF
            String fileName = "contract_" + contract.getId() + "_" + System.currentTimeMillis() + ".pdf";
            Path filePath = Paths.get("uploads/contracts/" + fileName);

            // Giả sử có hàm sinh nội dung PDF (tuỳ ông đang dùng iText, PDFBox hay Jasper)
            byte[] pdfBytes = createPdfBytes(contract);

            // Ghi file
            Files.write(filePath, pdfBytes);

            // Trả về đường dẫn mới
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo file PDF cho hợp đồng " + contract.getId(), e);
        }
    }

    private byte[] createPdfBytes(ContractEntity contract) {
        // Giả lập sinh nội dung PDF, ông thay phần này bằng code thật đang dùng
        String content = "Hợp đồng thuê số " + contract.getId() + "\nKhách hàng: " + contract.getCustomer().getFullName();
        return content.getBytes(StandardCharsets.UTF_8);
    }
}


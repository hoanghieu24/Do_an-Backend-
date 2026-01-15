package com.javaweb.service.impl;

import com.javaweb.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path root = Paths.get("uploads");

    @Override
    public String save(MultipartFile file) {
        try {
            // Tạo thư mục nếu chưa có
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            // Tạo tên file random để tránh trùng
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = root.resolve(filename);

            // Ghi file xuống
            Files.copy(file.getInputStream(), filePath);

            // Trả về đường dẫn (ví dụ bạn muốn lưu vào DB)
            return "/uploads/" + filename;

        } catch (IOException e) {
            throw new RuntimeException("❌ Lưu file thất bại: " + e.getMessage());
        }
    }

    @Override
    public void delete(String filePath) {
        try {
            if (filePath == null || filePath.isEmpty()) return;

            // Cắt bỏ "/uploads/" để lấy đúng tên file
            String filename = filePath.replace("/uploads/", "");

            Path path = root.resolve(filename);

            // Nếu file tồn tại thì xóa
            if (Files.exists(path)) {
                Files.delete(path);
            }

        } catch (IOException e) {
            throw new RuntimeException("❌ Xoá file thất bại: " + e.getMessage());
        }
    }

}

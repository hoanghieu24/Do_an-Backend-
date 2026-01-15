package com.javaweb.service;

import org.springframework.web.multipart.MultipartFile;

public interface  StorageService {
    String save(MultipartFile file);
    void delete(String filePath);
}

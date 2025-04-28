package com.thecodingcult.chat_service.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaStorageService {
    public String upload(MultipartFile file) {
        // Simulate uploading to cloud storage and returning the URL
        String uploadedUrl = "https://cloud-storage.example.com/" + file.getOriginalFilename();
        return uploadedUrl;
    }
}
package com.thecodingcult.chat_service.service;

import com.thecodingcult.chat_service.model.MediaFile;
import com.thecodingcult.chat_service.repository.MediaFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaStorageService {

    private final MediaFileRepository mediaFileRepository;

    public MediaStorageService(MediaFileRepository mediaFileRepository) {
        this.mediaFileRepository = mediaFileRepository;
    }

    public MediaFile upload(MultipartFile file) {
        // Simulate saving file metadata to the database
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileName(file.getOriginalFilename());
        mediaFile.setFileUrl("/uploads/" + file.getOriginalFilename()); // Example URL path
        return mediaFileRepository.save(mediaFile);
    }
}
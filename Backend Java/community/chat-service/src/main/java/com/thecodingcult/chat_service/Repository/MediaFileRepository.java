package com.thecodingcult.chat_service.repository;

import com.thecodingcult.chat_service.model.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFileRepository extends JpaRepository<MediaFile, Long> {
}
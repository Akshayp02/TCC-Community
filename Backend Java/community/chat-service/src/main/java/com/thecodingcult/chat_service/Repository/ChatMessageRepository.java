package com.thecodingcult.chat_service.Repository;

import com.thecodingcult.chat_service.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByGroupIdOrderByTimestampAsc(String groupId);
}

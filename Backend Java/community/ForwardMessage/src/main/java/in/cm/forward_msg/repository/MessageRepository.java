package in.cm.forward_msg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.cm.forward_msg.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
}
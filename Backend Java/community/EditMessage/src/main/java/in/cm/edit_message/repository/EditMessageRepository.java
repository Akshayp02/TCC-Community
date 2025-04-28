package in.cm.edit_message.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cm.edit_message.model.EditMessage;

public interface EditMessageRepository extends JpaRepository<EditMessage, Long> {
}
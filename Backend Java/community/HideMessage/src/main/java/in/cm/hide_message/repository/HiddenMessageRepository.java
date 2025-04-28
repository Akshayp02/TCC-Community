package in.cm.hide_message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cm.hide_message.entity.HiddenMessageEntity;
//Hiding from multiple user repository

@Repository
public interface HiddenMessageRepository extends JpaRepository<HiddenMessageEntity, Long>{
	
	List<HiddenMessageEntity> findByUsername(String username);
	boolean existsByMessageIdAndUsername(Long messageId, String username);

}

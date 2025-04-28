package in.cm.hide_message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cm.hide_message.entity.HideMessageEntity;

@Repository
public interface HideMessageRepo extends JpaRepository<HideMessageEntity, Long>{
	
	List<HideMessageEntity> findBySenderAndHiddenBySenderFalse(String sender);
    List<HideMessageEntity> findByReceiverAndHiddenByReceiverFalse(String receiver);

}
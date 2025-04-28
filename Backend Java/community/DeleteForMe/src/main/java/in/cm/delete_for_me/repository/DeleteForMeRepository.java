package in.cm.delete_for_me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import in.cm.delete_for_me.model.DeleteForMeModel;

public interface DeleteForMeRepository extends JpaRepository<DeleteForMeModel, Long>{
	List<DeleteForMeModel> findBySenderIdOrReceiverId(String senderId, String receiverId);

}


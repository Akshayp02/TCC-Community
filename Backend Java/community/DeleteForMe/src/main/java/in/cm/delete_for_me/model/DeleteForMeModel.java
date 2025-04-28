package in.cm.delete_for_me.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteForMeModel {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String senderId;
	    private String receiverId;
	    private String content;

	    private boolean deletedForSender = false;
	    private boolean deletedForReceiver = false;
	    
	    //Getter and Setter
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSenderId() {
			return senderId;
		}
		public void setSenderId(String senderId) {
			this.senderId = senderId;
		}
		public String getReceiverId() {
			return receiverId;
		}
		public void setReceiverId(String receiverId) {
			this.receiverId = receiverId;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public boolean isDeletedForSender() {
			return deletedForSender;
		}
		public void setDeletedForSender(boolean deletedForSender) {
			this.deletedForSender = deletedForSender;
		}
		public boolean isDeletedForReceiver() {
			return deletedForReceiver;
		}
		public void setDeletedForReceiver(boolean deletedForReceiver) {
			this.deletedForReceiver = deletedForReceiver;
		}
}


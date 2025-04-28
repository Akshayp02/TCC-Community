package in.cm.hide_message.entity;

import jakarta.persistence.*;

@Entity
public class HideMessageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String sender;
    private String receiver;
    private String content;
    
    private boolean hiddenBySender = false;
    private boolean hiddenByReceiver = false;
	
    //Getter and Setter
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
    }
	public boolean isHiddenBySender() {
		return hiddenBySender;
	}
	public void setHiddenBySender(boolean hiddenBySender) {
		this.hiddenBySender = hiddenBySender;
	}
	public boolean isHiddenByReceiver() {
		return hiddenByReceiver;
	}
	public void setHiddenByReceiver(boolean hiddenByReceiver) {
		this.hiddenByReceiver = hiddenByReceiver;
	}
    
    
	

}

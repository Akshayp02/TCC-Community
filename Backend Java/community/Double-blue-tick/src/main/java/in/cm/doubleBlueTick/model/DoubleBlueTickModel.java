package in.cm.doubleBlueTick.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoubleBlueTickModel {
    private String id;
    private String content;
    private MessageStatusBlue status;
    private boolean isInternetAvailable;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MessageStatusBlue getStatus() {
		return status;
	}
	public void setStatus(MessageStatusBlue status) {
		this.status = status;
	}
	public boolean isInternetAvailable() {
		return isInternetAvailable;
	}
	public void setInternetAvailable(boolean isInternetAvailable) {
		this.isInternetAvailable = isInternetAvailable;
	}
	
	
    
    
}

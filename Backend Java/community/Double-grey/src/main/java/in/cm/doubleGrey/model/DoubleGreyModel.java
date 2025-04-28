package in.cm.doubleGrey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoubleGreyModel {
	private String id;
    private String content;
    private MessageStatusDouble status;
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
	public MessageStatusDouble getStatus() {
		return status;
	}
	public void setStatus(MessageStatusDouble status) {
		this.status = status;
	}
	public boolean isInternetAvailable() {
		return isInternetAvailable;
	}
	public void setInternetAvailable(boolean isInternetAvailable) {
		this.isInternetAvailable = isInternetAvailable;
	}
	
	
    
    

}

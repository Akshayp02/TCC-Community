package in.cm.singleGrey.model;

public class SingleGreyModel {
	    private String id;
	    private String content;
	    private MessageStatus status;
	    private boolean isInternetAvailable;

	    // Default constructor
	    public SingleGreyModel() {}

	    // Parameterized constructor
	    public SingleGreyModel(String id, String content, MessageStatus status) {
	        this.id = id;
	        this.content = content;
	        this.status = status;
	    }

	    // Getters and setters
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

	    public MessageStatus getStatus() {
	        return status;
	    }

	    public void setStatus(MessageStatus status) {
	        this.status = status;
	    }

		public boolean isInternetAvailable() {
			return isInternetAvailable;
		}

		public void setInternetAvailable(boolean isInternetAvailable) {
			this.isInternetAvailable = isInternetAvailable;
		}
	    
}

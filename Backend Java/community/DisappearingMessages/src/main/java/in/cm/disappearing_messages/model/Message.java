package in.cm.disappearing_messages.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // For text or original file name

    private String type; // TEXT, PHOTO, VIDEO

    @Column(name = "file_path")
    private String filePath; // Used for storing file path if media

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private ExpirationTimer timer;

    private LocalDateTime expiresAt;
    
    private LocalDateTime expiryDate;
    

    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public ExpirationTimer getTimer() {
		return timer;
	}



	public void setTimer(ExpirationTimer timer) {
		this.timer = timer;
	}



	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}



	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
	
	public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }



	// Set creation and expiration timestamps
    public void setExpiration() {
        this.createdAt = LocalDateTime.now();
        if (this.timer == null || this.timer == ExpirationTimer.OFF) {
            this.expiresAt = null;
        } else {
            switch (this.timer) {
                case DAY -> this.expiresAt = this.createdAt.plusDays(1);
                case WEEK -> this.expiresAt = this.createdAt.plusWeeks(1);
                case MONTH -> this.expiresAt = this.createdAt.plusMonths(3);
            }
        }
    }
    
    public enum ExpirationTimer {
        OFF(0),        // No expiration
        DAY(86400),    // 1 day
        WEEK(604800),  // 7 days
        MONTH(7776000); // 90 days

        private final long seconds;

        ExpirationTimer(long seconds) {
            this.seconds = seconds;
        }

        public long getSeconds() {
            return this.seconds;
        }
    }
    
    
}

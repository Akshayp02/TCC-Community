package in.cm.live_location_sharing.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class LiveLocationModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private double latitude;
    private double longitude;
    private LocalDateTime sharedAt;
    private String locationName;
	
    //Getter and Setter
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public LocalDateTime getSharedAt() {
		return sharedAt;
	}
	public void setSharedAt(LocalDateTime sharedAt) {
		this.sharedAt = sharedAt;
	}  
    
	public String getLocationName() {
	    return locationName;
	}

	public void setLocationName(String locationName) {
	    this.locationName = locationName;
	}
}

package in.cm.current_location.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cm.current_location.model.CurrentLocationModel;
import in.cm.current_location.repository.CurrentLocationRepo;

@Service
public class CurrentLocationService {
	
	@Autowired
    private CurrentLocationRepo currentLocationRepository;

    public CurrentLocationModel shareCurrentLocation(Long userId, double latitude, double longitude) {
    	CurrentLocationModel location = new CurrentLocationModel();
        location.setUserId(userId);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setSharedAt(LocalDateTime.now());
        return currentLocationRepository.save(location);
    }

}

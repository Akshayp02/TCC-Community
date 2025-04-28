package in.cm.live_location_sharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.JSONObject;

import in.cm.live_location_sharing.model.LiveLocationModel;
import in.cm.live_location_sharing.repository.LiveLocationRepository;

import java.time.LocalDateTime;

@Service
public class LiveLocationService {

    @Autowired
    private LiveLocationRepository repository;
    
    @Autowired
    private RestTemplate restTemplate;

    public LiveLocationModel shareLocation(Long userId, double latitude, double longitude) {
        LiveLocationModel location = new LiveLocationModel();
        location.setUserId(userId);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setSharedAt(LocalDateTime.now());

     // Get location name from coordinates
        String locationName = getLocationName(latitude, longitude);
        location.setLocationName(locationName);

        return repository.save(location);
    }
    
    private String getLocationName(double latitude, double longitude) {
        String url = String.format(
            "https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=%f&lon=%f",
            latitude, longitude
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Spring Boot App");  // Required by OpenStreetMap

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                JSONObject obj = new JSONObject(response.getBody());
                return obj.optString("display_name", "Unknown location");
            } else {
                System.out.println("Reverse geocoding failed: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Unknown location";
    }

}
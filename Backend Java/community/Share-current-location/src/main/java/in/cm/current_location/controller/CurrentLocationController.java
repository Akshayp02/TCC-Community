package in.cm.current_location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.cm.current_location.model.CurrentLocationModel;
import in.cm.current_location.service.CurrentLocationService;

@RestController
@RequestMapping("/api/location")
public class CurrentLocationController {

    @Autowired
    private CurrentLocationService locationService;

    @PostMapping("/shareCurrentLocation")
    public ResponseEntity<String> shareCurrentLocation(@RequestBody CurrentLocationModel request) {
        System.out.println("Received from User ID: " + request.getUserId());
        System.out.println("Latitude: " + request.getLatitude());
        System.out.println("Longitude: " + request.getLongitude());
        return ResponseEntity.ok("Location shared successfully!");
    }
}

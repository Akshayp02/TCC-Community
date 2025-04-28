package in.cm.live_location_sharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.cm.live_location_sharing.model.LiveLocationModel;
import in.cm.live_location_sharing.service.LiveLocationService;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LiveLocationService locationService;

    @PostMapping("/share")
    public LiveLocationModel shareLiveLocation(@RequestParam Long userId, @RequestParam double latitude,
                                               @RequestParam double longitude) {
        return locationService.shareLocation(userId, latitude, longitude);
    }
}

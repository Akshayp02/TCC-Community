package in.cm.callspeaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.cm.callspeaker.model.SpeakerStatus;
import in.cm.callspeaker.service.SpeakerService;

@RestController
@RequestMapping("/calls/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @PostMapping("/on")
    public SpeakerStatus turnOnSpeaker(@RequestParam String callId) {
        return speakerService.turnOnSpeaker(callId);
    }

    @PostMapping("/off")
    public SpeakerStatus turnOffSpeaker(@RequestParam String callId) {
        return speakerService.turnOffSpeaker(callId);
    }

    @GetMapping("/status/{callId}")
    public SpeakerStatus getSpeakerStatus(@PathVariable String callId) {
        return speakerService.getStatus(callId);
    }
}
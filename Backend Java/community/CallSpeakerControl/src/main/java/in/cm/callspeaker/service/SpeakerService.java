package in.cm.callspeaker.service;

import org.springframework.stereotype.Service;

import in.cm.callspeaker.model.SpeakerStatus;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpeakerService {

    // In-memory map to store speaker status by callId
    private Map<String, Boolean> speakerStatusMap = new HashMap<>();

    public SpeakerStatus turnOnSpeaker(String callId) {
        speakerStatusMap.put(callId, true);
        return new SpeakerStatus(callId, true);
    }

    public SpeakerStatus turnOffSpeaker(String callId) {
        speakerStatusMap.put(callId, false);
        return new SpeakerStatus(callId, false);
    }

    public SpeakerStatus getStatus(String callId) {
        boolean status = speakerStatusMap.getOrDefault(callId, false);
        return new SpeakerStatus(callId, status);
    }
}

package in.cm.callspeaker.model;

public class SpeakerStatus {
    private String callId;
    private boolean speakerOn;

    public SpeakerStatus() {}

    public SpeakerStatus(String callId, boolean speakerOn) {
        this.callId = callId;
        this.speakerOn = speakerOn;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public boolean isSpeakerOn() {
        return speakerOn;
    }

    public void setSpeakerOn(boolean speakerOn) {
        this.speakerOn = speakerOn;
    }
}
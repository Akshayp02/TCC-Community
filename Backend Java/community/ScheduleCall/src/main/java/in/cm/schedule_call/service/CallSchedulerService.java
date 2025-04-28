package in.cm.schedule_call.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import in.cm.schedule_call.model.ScheduledCall;
import in.cm.schedule_call.repository.ScheduledCallRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CallSchedulerService {

    @Autowired
    private ScheduledCallRepository repository;

    public ScheduledCall scheduleCall(ScheduledCall call) {
        call.setTriggered(false);
        return repository.save(call);
    }
    
    public List<ScheduledCall> getAllCalls() {
        return repository.findAll();
    }

    @Scheduled(fixedRate = 60000) // Every 1 minute
    public void triggerScheduledCalls() {
        List<ScheduledCall> dueCalls = repository.findByTriggeredFalseAndScheduledTimeBefore(LocalDateTime.now());
        for (ScheduledCall call : dueCalls) {
            System.out.println("📞 Triggering call to: " + call.getRecipient() + " at " + LocalDateTime.now());
            call.setTriggered(true);
            repository.save(call);
        }
    }
}

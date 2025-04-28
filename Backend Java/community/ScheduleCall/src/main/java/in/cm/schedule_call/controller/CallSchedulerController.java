package in.cm.schedule_call.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.cm.schedule_call.model.ScheduledCall;
import in.cm.schedule_call.service.CallSchedulerService;

@RestController
@RequestMapping("/api/calls")
public class CallSchedulerController {

    @Autowired
    private CallSchedulerService service;

    @PostMapping("/schedule")
    public ScheduledCall scheduleCall(@RequestBody ScheduledCall call) {
        return service.scheduleCall(call);
    }
    
    @GetMapping("/getCall")
    public List<ScheduledCall> getAllScheduledCalls() {
        return service.getAllCalls();
    }
}
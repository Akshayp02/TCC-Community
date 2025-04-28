package in.cm.hold_resume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import in.cm.hold_resume.model.Call;
import in.cm.hold_resume.service.CallService;

import java.util.List;

@RestController
@RequestMapping("/calls")
public class CallController {

    @Autowired
    private CallService callService;

    @GetMapping
    public List<Call> getAllCalls() {
        return callService.getAllCalls();
    }

    @PostMapping
    public Call createCall(@RequestBody Call call) {
        return callService.createCall(call);
    }

    @PutMapping("/{id}/hold")
    public Call putOnHold(@PathVariable Long id) {
        return callService.putOnHold(id);
    }

    @PutMapping("/{id}/resume")
    public Call resumeCall(@PathVariable Long id) {
        return callService.resumeCall(id);
    }

    @PutMapping("/{id}")
    public Call updateCall(@PathVariable Long id, @RequestBody Call call) {
        return callService.updateCall(id, call);
    }

    @DeleteMapping("/{id}")
    public String deleteCall(@PathVariable Long id) {
        callService.deleteCall(id);
        return "Call deleted successfully with ID: " + id;
    }
}

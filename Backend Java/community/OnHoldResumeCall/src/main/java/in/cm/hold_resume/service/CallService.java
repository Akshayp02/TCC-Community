package in.cm.hold_resume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cm.hold_resume.model.Call;
import in.cm.hold_resume.repository.CallRepository;

import java.util.List;

@Service
public class CallService {

    @Autowired
    private CallRepository callRepository;

    public List<Call> getAllCalls() {
        return callRepository.findAll();
    }

    public Call createCall(Call call) {
        call.setStatus(Call.CallStatus.ACTIVE);
        return callRepository.save(call);
    }

    public Call putOnHold(Long id) {
        Call call = callRepository.findById(id).orElseThrow();
        call.setStatus(Call.CallStatus.ON_HOLD);
        return callRepository.save(call);
    }

    public Call resumeCall(Long id) {
        Call call = callRepository.findById(id).orElseThrow();
        call.setStatus(Call.CallStatus.RESUMED);
        return callRepository.save(call);
    }

    public void deleteCall(Long id) {
        callRepository.deleteById(id);
    }

    public Call updateCall(Long id, Call updatedCall) {
        Call call = callRepository.findById(id).orElseThrow();
        call.setCaller(updatedCall.getCaller());
        call.setReceiver(updatedCall.getReceiver());
        return callRepository.save(call);
    }
}

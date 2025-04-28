package in.cm.schedule_call.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cm.schedule_call.model.ScheduledCall;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledCallRepository extends JpaRepository<ScheduledCall, Long> {
    List<ScheduledCall> findByTriggeredFalseAndScheduledTimeBefore(LocalDateTime time);
}
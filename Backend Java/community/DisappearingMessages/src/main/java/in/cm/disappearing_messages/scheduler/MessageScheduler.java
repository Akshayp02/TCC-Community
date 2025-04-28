package in.cm.disappearing_messages.scheduler;

import in.cm.disappearing_messages.service.MessageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduler {

    private final MessageService messageService;

    public MessageScheduler(MessageService messageService) {
        this.messageService = messageService;
    }

    // Schedule task to delete expired messages every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredMessages() {
        messageService.deleteExpiredMessages();
    }
}

package in.cm.disappearing_messages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //enables schedule tasks (like msg deletion)
public class DisappearingMessagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisappearingMessagesApplication.class, args);
	}

}

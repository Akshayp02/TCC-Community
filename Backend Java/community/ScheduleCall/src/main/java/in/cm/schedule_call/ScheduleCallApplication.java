package in.cm.schedule_call;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduleCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleCallApplication.class, args);
	}

}

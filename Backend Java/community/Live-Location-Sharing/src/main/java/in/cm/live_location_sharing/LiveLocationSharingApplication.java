package in.cm.live_location_sharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LiveLocationSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveLocationSharingApplication.class, args);
	}

	//RestTemplate bean to allow HTTP API calls
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}

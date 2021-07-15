package upeu.sisecad.grupo5.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SisecadEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisecadEurekaServerApplication.class, args);
	}

	
}

package upeu.sisecad.grupo5.app.docente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SisecadMicroservicioDocenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisecadMicroservicioDocenteApplication.class, args);
	}

}

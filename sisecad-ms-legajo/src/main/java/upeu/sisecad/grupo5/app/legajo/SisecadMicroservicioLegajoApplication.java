package upeu.sisecad.grupo5.app.legajo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ms-legajo", version = "2.0"))
public class SisecadMicroservicioLegajoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisecadMicroservicioLegajoApplication.class, args);
	}

}

package upeu.sisecad.grupo5.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SisecadZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisecadZuulServerApplication.class, args);
	}

}

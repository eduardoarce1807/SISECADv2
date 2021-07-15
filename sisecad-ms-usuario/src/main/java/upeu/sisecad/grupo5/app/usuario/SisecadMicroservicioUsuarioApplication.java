package upeu.sisecad.grupo5.app.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ms-usuario", version = "2.0"))
public class SisecadMicroservicioUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisecadMicroservicioUsuarioApplication.class, args);
	}

}

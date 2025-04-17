package br.com.HUOC_BACK.config.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("REST API - HUOC")
                        .description("API de Sistema de Coleta de Dados Clínicos")
                        .contact(new Contact().name("JairVictor").email("jvla2@discente.ifpe.edu.br"))
                        .version("1.0"));
    }
}

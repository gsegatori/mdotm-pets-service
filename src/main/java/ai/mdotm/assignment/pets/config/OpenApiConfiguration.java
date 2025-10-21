package ai.mdotm.assignment.pets.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI petsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mdotm.ai – Pets Service API")
                        .version("1.0.0")
                        .description("""
                            RESTful API for managing pets and their owners.
                            Implemented as part of the Mdotm.ai Java Developer assignment.
                            Built with Spring Boot 3, Java 17, Lombok, and springdoc-openapi.
                            """
                        )
                        .contact(new Contact()
                                .name("Giorgio Segatori")
                                .email("ilmagodioz@live.it")
                                .url("https://github.com/gsegatori"))
                                .description("My github has only 1 public repo, the others are private, request the access")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .servers(java.util.List.of(new Server().url("/")))
                .externalDocs(new ExternalDocumentation().description("Swagger UI documentation").url("/swagger-ui.html"));
    }
}

package ai.mdotm.assignment.pets.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class StartupLogger implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            var env = event.getApplicationContext().getEnvironment();
            String port = env.getProperty("server.port", "8080");
            String host = InetAddress.getLocalHost().getHostAddress();

            String swaggerUrl = "http://" + host + ":" + port + "/swagger-ui.html";
            String openapiUrl = "http://" + host + ":" + port + "/v3/api-docs";

            System.out.println("""
                ------------------------------------------------------------
                 Application is ready!
                 Swagger UI:  %s
                 OpenAPI JSON: %s
                ------------------------------------------------------------
                """.formatted(swaggerUrl, openapiUrl));
        } catch (Exception e) {
            System.err.println("Could not determine startup URLs: " + e.getMessage());
        }
    }
}

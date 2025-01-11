package backend.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Konfiguriert die CORS (Cross-Origin Resource Sharing)-Einstellungen für die Webanwendung.
 * Diese Klasse stellt sicher, dass Anfragen von bestimmten Ursprüngen und mit bestimmten Methoden erlaubt sind.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Konfiguriert die CORS-Mappings für die Anwendung.
     * Definiert, welche Ursprünge, HTTP-Methoden, Header und Anmeldeinformationen erlaubt sind.
     *
     * @param registry Das {@link CorsRegistry}-Objekt, das verwendet wird, um die CORS-Konfigurationen zu registrieren
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000") // Erlaube nur Anfragen von diesem Origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Erlaube diese HTTP-Methoden
                .allowedHeaders("*") // Erlaube alle Header
                .allowCredentials(true); // Erlaube Cookies und andere Anmeldeinformationen
    }
}

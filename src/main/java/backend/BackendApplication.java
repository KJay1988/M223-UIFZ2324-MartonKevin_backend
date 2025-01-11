package backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Die Hauptanwendungsklasse für das Backend der Anwendung.
 * Diese Klasse enthält die Hauptmethode, die als Einstiegspunkt für das Spring Boot-Anwendungsprogramm dient.
 * Sie initialisiert und startet die Spring Boot-Anwendung.
 */

@SpringBootApplication
public class BackendApplication {

    /**
     * Der Einstiegspunkt für die Anwendung.
     * Diese Methode startet die Spring Boot-Anwendung und initialisiert den gesamten Anwendungskontext.
     *
     * @param args die Argumente, die beim Starten der Anwendung über die Kommandozeile übergeben werden
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
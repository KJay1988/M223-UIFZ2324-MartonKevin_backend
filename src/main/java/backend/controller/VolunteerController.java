package backend.controller;

import backend.exception.ResourceNotFoundException;
import backend.model.Volunteer;
import backend.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Der Controller für die Verwaltung von Freiwilligen (Volunteers).
 * Diese Klasse verwaltet die HTTP-Anfragen und bietet Endpunkte für CRUD-Operationen
 * (Erstellen, Lesen, Aktualisieren und Löschen) von Freiwilligen.
 */
@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private static final Logger LOGGER = Logger.getLogger(VolunteerController.class.getName());

    @Autowired
    private VolunteerRepository volunteerRepository;

    /**
     * Gibt eine Liste aller registrierten Freiwilligen zurück (nur für Admins sichtbar).
     *
     * @return Eine Liste von {@link Volunteer}-Objekten
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Volunteer> getAllVolunteers() {
        LOGGER.info("Alle Volunteers werden abgerufen.");
        return volunteerRepository.findAll();
    }

    /**
     * Gibt die Details des eingeloggten Freiwilligen zurück.
     * Jeder Benutzer kann nur seine eigenen Daten abrufen.
     *
     * @param authentication Enthält die Benutzerinformationen des eingeloggten Nutzers.
     * @return Das entsprechende {@link Volunteer}-Objekt oder eine Fehlermeldung.
     */
    @GetMapping("/me")
    public ResponseEntity<?> getLoggedInVolunteer(Authentication authentication) {
        String username = authentication.getName();
        Volunteer volunteer = volunteerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with username: " + username));

        return ResponseEntity.ok(volunteer);
    }

    /**
     * Erstellt einen neuen Freiwilligen (nur für Admins erlaubt).
     *
     * @param volunteer Das {@link Volunteer}-Objekt, das erstellt werden soll
     * @return Das neu erstellte {@link Volunteer}-Objekt
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createVolunteer(@Valid @RequestBody Volunteer volunteer) {
        LOGGER.info("Neuer Volunteer wird erstellt: " + volunteer.getUsername());
        return ResponseEntity.ok(volunteerRepository.save(volunteer));
    }

    /**
     * Gibt die Details eines bestimmten Freiwilligen anhand der ID zurück (Admin und OK-Mitglieder).
     *
     * @param id Die ID des Freiwilligen
     * @return Eine {@link ResponseEntity} mit dem {@link Volunteer}-Objekt oder einer Fehlermeldung
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('OK')")
    public ResponseEntity<?> getVolunteerById(@PathVariable String id) {
        try {
            Long volunteerId = Long.parseLong(id);
            Volunteer volunteer = volunteerRepository.findById(volunteerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with id: " + volunteerId));
            return ResponseEntity.ok(volunteer);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid ID format: " + id, e);
            return ResponseEntity.badRequest().body("Invalid ID format: " + id);
        } catch (ResourceNotFoundException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred", e);
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    /**
     * Aktualisiert die Daten eines bestehenden Freiwilligen anhand der ID (nur Admins erlaubt).
     *
     * @param id Die ID des zu aktualisierenden Freiwilligen
     * @param volunteerDetails Ein {@link Volunteer}-Objekt mit den aktualisierten Daten
     * @return Eine {@link ResponseEntity} mit dem aktualisierten {@link Volunteer}-Objekt oder einer Fehlermeldung
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateVolunteer(@PathVariable String id, @Valid @RequestBody Volunteer volunteerDetails) {
        try {
            Long volunteerId = Long.parseLong(id);
            Volunteer volunteer = volunteerRepository.findById(volunteerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with id: " + volunteerId));

            volunteer.setVorname(volunteerDetails.getVorname());
            volunteer.setName(volunteerDetails.getName());
            volunteer.setGeburtsdatum(volunteerDetails.getGeburtsdatum());
            volunteer.setEinsatzort(volunteerDetails.getEinsatzort());

            final Volunteer updatedVolunteer = volunteerRepository.save(volunteer);
            return ResponseEntity.ok(updatedVolunteer);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid ID format: " + id, e);
            return ResponseEntity.badRequest().body("Invalid ID format: " + id);
        } catch (ResourceNotFoundException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred", e);
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    /**
     * Löscht einen bestehenden Freiwilligen anhand der ID (nur für Admins erlaubt).
     *
     * @param id Die ID des zu löschenden Freiwilligen
     * @return Eine {@link ResponseEntity} mit dem Statuscode 204 (No Content) oder einer Fehlermeldung
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteVolunteer(@PathVariable String id) {
        try {
            Long volunteerId = Long.parseLong(id);
            Volunteer volunteer = volunteerRepository.findById(volunteerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with id: " + volunteerId));

            volunteerRepository.delete(volunteer);
            LOGGER.info("Volunteer gelöscht mit ID: " + volunteerId);
            return ResponseEntity.noContent().build();
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid ID format: " + id, e);
            return ResponseEntity.badRequest().body("Invalid ID format: " + id);
        } catch (ResourceNotFoundException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred", e);
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }
}

package backend.controller;

import backend.model.Role;
import backend.model.RoleName;
import backend.model.Volunteer;
import backend.repository.RoleRepository;
import backend.repository.VolunteerRepository;
import backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Registrierung eines neuen Benutzers (Volunteer)
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Volunteer volunteer) {
        // ✅ Username automatisch generieren
        String generatedUsername = volunteer.getVorname().toLowerCase() + "." + volunteer.getName().toLowerCase();
        volunteer.setUsername(generatedUsername);

        // ❌ Falls der Benutzername bereits existiert, Registrierung abbrechen
        if (volunteerRepository.existsByUsername(volunteer.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Benutzername existiert bereits!"));
        }

        // 🔹 Standard-Rolle `HELFER` setzen, falls keine Rolle angegeben wurde
        if (volunteer.getRole() == null) {
            Role defaultRole = roleRepository.findByName(RoleName.HELFER)
                    .orElseThrow(() -> new RuntimeException("Standardrolle nicht gefunden!"));
            volunteer.setRole(defaultRole);
        }

        // ✅ Passwort hashen und User speichern
        volunteer.setPassword(passwordEncoder.encode(volunteer.getPassword()));
        volunteerRepository.save(volunteer);

        LOGGER.info("Neuer Benutzer registriert: " + volunteer.getUsername() + " mit Rolle: " + volunteer.getRole().getName());
        return ResponseEntity.ok(Map.of("message", "Benutzer erfolgreich registriert!"));
    }

    /**
     * Benutzer-Login mit JWT-Token-Generierung
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Volunteer loginRequest) {
        Optional<Volunteer> dbVolunteer = volunteerRepository.findByUsername(loginRequest.getUsername());

        if (dbVolunteer.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), dbVolunteer.get().getPassword())) {
            Volunteer loggedInVolunteer = dbVolunteer.get();
            String token = jwtUtil.generateToken(loggedInVolunteer.getUsername());

            // ✅ Antwort als JSON-Objekt mit Token & Rolle
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", loggedInVolunteer.getRole().getName().toString());

            LOGGER.info("Benutzer erfolgreich eingeloggt: " + loggedInVolunteer.getUsername() + " mit Rolle: " + loggedInVolunteer.getRole().getName());
            return ResponseEntity.ok(response);
        }

        LOGGER.log(Level.WARNING, "Fehlgeschlagener Login-Versuch für Benutzer: " + loginRequest.getUsername());
        return ResponseEntity.status(401).body(Map.of("error", "Ungültige Anmeldeinformationen!"));
    }
}

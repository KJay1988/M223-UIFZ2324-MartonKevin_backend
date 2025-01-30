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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private VolunteerRepository volunteerRepository;  // 🔄 Geändert von UserRepository zu VolunteerRepository

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Volunteer volunteer) {
        // ✅ Username automatisch generieren
        String generatedUsername = volunteer.getVorname().toLowerCase() + "." + volunteer.getName().toLowerCase();
        volunteer.setUsername(generatedUsername);

        // ❌ Falls der Benutzername bereits existiert, Registrierung abbrechen
        if (volunteerRepository.existsByUsername(volunteer.getUsername())) {
            return ResponseEntity.badRequest().body("Benutzername existiert bereits!");
        }

        // 🔹 Standard-Rolle `HELPER` setzen, falls keine Rolle angegeben wurde
        if (volunteer.getRole() == null) {

            Role defaultRole = roleRepository.findByName(RoleName.valueOf("HELFER"))
                    .orElseThrow(() -> new RuntimeException("Standardrolle nicht gefunden!"));
            volunteer.setRole(defaultRole);
        }

        // ✅ Passwort hashen und User speichern
        volunteer.setPassword(passwordEncoder.encode(volunteer.getPassword()));
        volunteerRepository.save(volunteer);

        return ResponseEntity.ok("Benutzer erfolgreich registriert!");
    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Volunteer volunteer) {
        Optional<Volunteer> dbVolunteer = volunteerRepository.findByUsername(volunteer.getUsername());
        if (dbVolunteer.isPresent() && passwordEncoder.matches(volunteer.getPassword(), dbVolunteer.get().getPassword())) {
            String token = jwtUtil.generateToken(volunteer.getUsername());
            return ResponseEntity.ok("{\"token\": \"" + token + "\"}");
        }
        return ResponseEntity.status(401).body("Ungültige Anmeldeinformationen!");
    }
}

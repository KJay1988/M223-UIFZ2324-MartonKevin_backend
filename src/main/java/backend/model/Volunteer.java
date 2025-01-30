package backend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vorname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate geburtsdatum;

    @Column
    private String einsatzort;

    @Column(unique = true, nullable = false)
    private String username;  // Eindeutiger Benutzername für Login

    @Column(nullable = false)
    private String password;  // Passwort wird in der Datenbank gespeichert (verschlüsselt)

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role; // Jeder Volunteer hat eine Rolle

    // 🔹 Standard-Konstruktor (wichtig für JPA)
    public Volunteer() {}

    // 🔹 Konstruktor mit allen Attributen (ohne ID, da automatisch generiert)
    public Volunteer(String vorname, String name, LocalDate geburtsdatum, String einsatzort, String username, String password, Role role) {
        this.vorname = vorname;
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.einsatzort = einsatzort;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // 🔹 Getter & Setter

    public Long getId() { return id; }

    public String getVorname() {
        return vorname;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }
    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getEinsatzort() {
        return einsatzort;
    }
    public void setEinsatzort(String einsatzort) {
        this.einsatzort = einsatzort;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}

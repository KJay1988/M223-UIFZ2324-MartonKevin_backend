package backend.repository;

import backend.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository-Interface für die Entität {@link Volunteer}.
 * Diese Schnittstelle ermöglicht den Zugriff auf die Datenbankoperationen
 * für die {@link Volunteer}-Entität, einschließlich CRUD-Operationen.
 * Sie erbt von {@link JpaRepository}, das grundlegende Methoden für
 * die Arbeit mit der Datenbank bereitstellt.
 */
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    // Es sind keine zusätzlichen Methoden erforderlich, da JpaRepository bereits
    // die grundlegenden CRUD-Operationen bereitstellt.
}

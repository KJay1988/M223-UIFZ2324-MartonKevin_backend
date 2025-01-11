package backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Repräsentiert einen freiwilligen Helfer in der Anwendung.
 * Diese Klasse wird als Entität in der Datenbank gespeichert und enthält
 * Informationen über den Helfer, einschließlich persönlicher Daten und Einsatzort.
 */
@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Der Vorname des freiwilligen Helfers.
     */
    private String vorname;

    /**
     * Der Nachname des freiwilligen Helfers.
     */
    private String name;

    /**
     * Das Geburtsdatum des freiwilligen Helfers.
     */
    private String geburtsdatum;

    /**
     * Das Geschlecht des freiwilligen Helfers.
     */
    private String geschlecht;

    /**
     * Der Einsatzort des freiwilligen Helfers.
     */
    private String einsatzort;

    /**
     * Gibt die ID des freiwilligen Helfers zurück.
     * 
     * @return Die ID des freiwilligen Helfers
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt die ID des freiwilligen Helfers.
     * 
     * @param id Die ID des freiwilligen Helfers
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt den Vornamen des freiwilligen Helfers zurück.
     * 
     * @return Der Vorname des freiwilligen Helfers
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vornamen des freiwilligen Helfers.
     * 
     * @param vorname Der Vorname des freiwilligen Helfers
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gibt den Nachnamen des freiwilligen Helfers zurück.
     * 
     * @return Der Nachname des freiwilligen Helfers
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Nachnamen des freiwilligen Helfers.
     * 
     * @param name Der Nachname des freiwilligen Helfers
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt das Geburtsdatum des freiwilligen Helfers zurück.
     * 
     * @return Das Geburtsdatum des freiwilligen Helfers
     */
    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * Setzt das Geburtsdatum des freiwilligen Helfers.
     * 
     * @param geburtsdatum Das Geburtsdatum des freiwilligen Helfers
     */
    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    /**
     * Gibt das Geschlecht des freiwilligen Helfers zurück.
     * 
     * @return Das Geschlecht des freiwilligen Helfers
     */
    public String getGeschlecht() {
        return geschlecht;
    }

    /**
     * Setzt das Geschlecht des freiwilligen Helfers.
     * 
     * @param geschlecht Das Geschlecht des freiwilligen Helfers
     */
    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    /**
     * Gibt den Einsatzort des freiwilligen Helfers zurück.
     * 
     * @return Der Einsatzort des freiwilligen Helfers
     */
    public String getEinsatzort() {
        return einsatzort;
    }

    /**
     * Setzt den Einsatzort des freiwilligen Helfers.
     * 
     * @param einsatzort Der Einsatzort des freiwilligen Helfers
     */
    public void setEinsatzort(String einsatzort) {
        this.einsatzort = einsatzort;
    }
}

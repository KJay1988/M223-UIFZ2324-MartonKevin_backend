# Musikfestival Helferliste

## Beschreibung

Dieses Projekt verwaltet die Helferliste für ein Musikfestival. Es gibt vier verschiedene Rollen: Admin, OK, Superhelfer und Helfer. Jede Rolle hat unterschiedliche Berechtigungen und Aufgaben.

## Verwendete Technologien

- Java 21
- React
- Node.js
- Spring Security mit JWT Authentication
- Spring Data JPA
- MySQL
- Maven

## Installation

1. Klone die Backend Repository:
   ```sh
   git clone https://github.com/KJay1988/M223-UIFZ2324-MartonKevin_backend
   ```

2. Klone die Frontend Repository:
   ```sh
   git clone https://github.com/KJay1988/M223-UIFZ2324-MartonKevin_frontend
    ```
   
3. Wechsle in das Projektverzeichnis des Backends:
   ```sh
   cd M223-UIFZ2324-MartonKevin_backend
   ```

4. Wechsle in das Projektverzeichnis des Frontends:
   ```sh
   cd ../M223-UIFZ2324-MartonKevin_frontend
   ```
   
5. Installiere Abhängigkeiten (falls erforderlich):
   ```sh
   npm install
   ```

## Verwendung

 Starte den Backend:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

   Starte den Frontend:
   ```sh
   npm install
   npm start
   ```

## Datenbank Konfiguration
Erstelle einen MySQL Datenbank und aktualisiere den application.properties:
```sh
# ===============================
# DATENBANK-KONFIGURATION
# ===============================
spring.datasource.url=jdbc:mysql://localhost:3306/volunteers?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ===============================
# JPA / HIBERNATE EINSTELLUNGEN
# ===============================
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# ===============================
# SECURITY & JWT KONFIGURATION
# ===============================
app.jwt.secret=meinSuperGeheimesJWT
app.jwt.expirationMs=3600000  # 1 Stunde in Millisekunden

# ===============================
# CORS KONFIGURATION
# ===============================
spring.web.cors.allowed-origins=http://localhost:3000
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE
spring.web.cors.allowed-headers=*
spring.web.cors.allow-credentials=true

# ===============================
# DEBUGGING & LOGGING
# ===============================
logging.level.org.springframework=INFO
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
logging.level.org.springframework.jdbc.datasource.init.ScriptUtils=DEBUG
```

## Rollen und Berechtigungen

- **Admin**: Hat die volle Kontrolle über das System, kann Benutzer verwalten, Einsätze koordinieren und Berichte generieren.
- **OK**: Verantwortlich für die Organisation des Festivals, kann Helfer einteilen, löschen, bearbeiten und Schichten planen.  
- **Superhelfer**: Hat erweiterte Rechte im Vergleich zu normalen Helfern und kann andere Helfer unterstützen oder koordinieren.
- **Helfer**: Kann sich für Schichten eintragen und Aufgaben übernehmen.

## Tests

   Teste den Frontend:
   ```sh
   npm test
   ```

## Mitwirken

1. Forke das Repository
2. Erstelle einen neuen Branch (`git checkout -b feature-xy`)
3. Führe Änderungen durch und committe sie (`git commit -m 'Änderung'`)
4. Push den Branch (`git push origin feature-xy`)
5. Erstelle einen Pull Request

## Lizenz

Dieses Projekt steht unter der MIT-Lizenz. Weitere Informationen in der `LICENSE`-Datei.

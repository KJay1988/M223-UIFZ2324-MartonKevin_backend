CREATE DATABASE volunteers;

USE volunteers;

-- ============================
-- 🌍 Erstellen der Role-Tabelle
-- ============================
CREATE TABLE role (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name ENUM('ADMIN', 'OK', 'HELFER') UNIQUE NOT NULL
);

-- ============================
-- 🤝 Erstellen der Volunteer-Tabelle
-- Jeder Volunteer hat eine Rolle (role_id)
-- ============================user
CREATE TABLE volunteer (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           vorname VARCHAR(255) NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           geburtsdatum DATE,
                           einsatzort VARCHAR(255),
                           username VARCHAR(225) UNIQUE NOT NULL,
                           password VARCHAR(255) NOT NULL,  -- Falls Volunteers sich einloggen können
                           role_id BIGINT NOT NULL,  -- Jeder Volunteer hat genau eine Rolle
                           FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- ============================
-- 🔑 Rollen in die Role-Tabelle einfügen
-- ============================
INSERT INTO role (name) VALUES
                            ('ADMIN'),
                            ('OK'),
                            ('HELFER');




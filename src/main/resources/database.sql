-- 1. Creare baza de date
CREATE DATABASE IF NOT EXISTS catalog_cosmetice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE catalog_cosmetice;

-- 2. Tabel: Magazin
CREATE TABLE Magazin (
                         id_magazin INT AUTO_INCREMENT PRIMARY KEY,
                         nume VARCHAR(100) NOT NULL,
                         adresa VARCHAR(200),
                         telefon VARCHAR(20)
);

-- 3. Tabel: Produs
CREATE TABLE Produs (
                        id_produs INT AUTO_INCREMENT PRIMARY KEY,
                        denumire VARCHAR(100) NOT NULL,
                        producator VARCHAR(100),
                        valabilitate DATE,
                        categorie VARCHAR(50)
);

-- 4. Tabel: Stoc (legatura M:N intre Magazin si Produs)
CREATE TABLE Stoc (
                      id_magazin INT,
                      id_produs INT,
                      disponibilitate BOOLEAN DEFAULT TRUE,
                      cantitate INT DEFAULT 0,
                      PRIMARY KEY (id_magazin, id_produs),
                      FOREIGN KEY (id_magazin) REFERENCES Magazin(id_magazin) ON DELETE CASCADE,
                      FOREIGN KEY (id_produs) REFERENCES Produs(id_produs) ON DELETE CASCADE
);

-- 5. Tabel: VariantaProdus (variante per produs: culoare, marime etc.)
CREATE TABLE VariantaProdus (
                                id_varianta INT AUTO_INCREMENT PRIMARY KEY,
                                id_produs INT,
                                culoare VARCHAR(50),
                                marime VARCHAR(50),
                                imagine TEXT,
                                descriere TEXT,
                                FOREIGN KEY (id_produs) REFERENCES Produs(id_produs) ON DELETE CASCADE
);

-- Inserare date test Produs
INSERT INTO Produs (denumire, producator, valabilitate, categorie) VALUES
                                                                       ('Crema hidratantă', 'Nivea', '2025-12-31', 'Îngrijire ten'),
                                                                       ('Ruj mat', 'Maybelline', '2026-06-30', 'Machiaj');

-- Inserare date test Stoc
INSERT INTO Stoc (id_magazin, id_produs, disponibilitate, cantitate) VALUES
                                                                         (1, 1, TRUE, 50),
                                                                         (2, 1, TRUE, 30),
                                                                         (1, 2, FALSE, 0);

-- Inserare date test VarianteProdus
INSERT INTO VariantaProdus (id_produs, culoare, marime, imagine, descriere) VALUES
                                                                                (2, 'Roșu', 'Normal', 'imagine1.jpg', 'Ruj roșu intens, mat'),
                                                                                (2, 'Roz', 'Mini', 'imagine2.jpg', 'Ruj roz pal, mat');



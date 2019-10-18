-- Adrián Navarro Gabino

/*
 * 2.2.2.2: Create a database called "pruebas1". Create in it a table
 * "programas" to save a collection of software. In a first approximation we
 * will save only three fields: a code, a name and the name of the backup
 * device on which this program is backed up. Enter two sample data and verify
 * that they have been stored correctly.
 */

CREATE DATABASE pruebas1;

CREATE TABLE programas
(
    CODIGO SERIAL PRIMARY KEY,
    NOMBRE VARCHAR(100),
    DISPOSITIVO_COPIA VARCHAR(100)
);

INSERT INTO programas
(NOMBRE, DISPOSITIVO_COPIA)
VALUES
('Program1', 'Device1');

INSERT INTO programas
(NOMBRE, DISPOSITIVO_COPIA)
VALUES
('Program2', 'Device2');

SELECT *
FROM programas;
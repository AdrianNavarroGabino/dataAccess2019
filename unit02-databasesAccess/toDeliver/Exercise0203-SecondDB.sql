-- Adrián Navarro Gabino

/*
 * Using "pgAdmin", create a database "dia02". Add in it a table "clientes"
 * (text id, name, date of registration). Enter 2 sample data. Show the ones
 * that begin with "A".
 */

CREATE DATABASE 'dia02';

CREATE TABLE CLIENTES
(
    ID TEXT PRIMARY KEY,
    NOMBRE TEXT,
    FECHA_ALTA DATE
);

INSERT INTO CLIENTES
(ID, NOMBRE, FECHA_ALTA)
VALUES
('1', 'Antonio', NOW());

INSERT INTO CLIENTES
(ID, NOMBRE, FECHA_ALTA)
VALUES
('2', 'Sara', NOW());

SELECT *
FROM CLIENTES
WHERE UPPER(NOMBRE) LIKE 'A%';
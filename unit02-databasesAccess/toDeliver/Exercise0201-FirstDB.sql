-- Adrián Navarro Gabino

/*
 * Install PostgreSQL.
 * Using the "psql" tool, create a "dia01" database, with an "articulos" table,
 * which will have two fields: "id" (numeric, primary key) and "name"
 * (text, up to 100 characters).
 * Add two sample data. Show existing data.
 */

CREATE DATABASE 'DIA01';

CREATE TABLE ARTICULOS
(
    ID SERIAL PRIMARY KEY,
    NOMBRE VARCHAR(100)
);

INSERT INTO ARTICULOS (NOMBRE) VALUES 'Table';
INSERT INTO ARTICULOS (NOMBRE) VALUES 'Chair';

SELECT * FROM ARTICULOS;
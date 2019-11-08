-- Adrián Navarro Gabino

/*
 * 3.1.2.1: Create a database called “world”. Create in it a table "countries". 
 * For each country, for now we will keep only three fields: a code (“cod”),
 * the name (“name”) and the number of inhabitants (“inhabitants”).
 * Add two sample data.
 */

CREATE DATABASE MUNDO;

CREATE TABLE PAISES
(
	COD SERIAL PRIMARY KEY,
	NOMBRE VARCHAR(100),
	HABITANTES NUMERIC(10)
);

INSERT INTO PAISES
(NOMBRE, HABITANTES)
VALUES
('República Checa', 10501197);

INSERT INTO PAISES
(NOMBRE, HABITANTES)
VALUES
('Malta', 475700);

-- Adrián Navarro Gabino

/*
 * We want to store the information of the cities of Spain. The data we want 
 * to store about them are the following:
 *
 *   Name.
 *   Altitude.
 *   Population.
 *
 * We also want to store those cities that are capital and from which
 * province they are.
 *
 * Propose a solution with inheritance.
 */

CREATE TABLE CIUDADES
(
	NOMBRE VARCHAR(20) PRIMARY KEY,
	ALTITUD NUMERIC,
	HABITANTES NUMERIC
);

CREATE TABLE CAPITALES
(
	PROVINCIA VARCHAR(20)
)
INHERITS (CIUDADES);

ALTER TABLE CAPITALES ADD CONSTRAINT PK_CAPITALES PRIMARY KEY (NOMBRE);
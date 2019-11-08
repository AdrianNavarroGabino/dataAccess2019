-- Adrián Navarro Gabino

/*
 * 3.1.3.2: In the "world" database, also create a "cities" table. For each
 * country, for now we will save only three fields: a code ("cod"), the name
 * ("name") and the location ("ubic").
 */

CREATE TABLE CIUDADES
(
	COD SERIAL PRIMARY KEY,
	NOMBRE VARCHAR(100),
	UBIC UBICACION
);

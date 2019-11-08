-- Adrián Navarro Gabino

/*
 * Create a "PruebaOR" database.
 *
 * Create a "usuarios" table, with fields:
 *
 *    id (5 letters).
 *    nombre
 *    codPais (2 letters).
 *    diaNac (1 to 31).
 *    mesNac (1 to 12).
 *    anyoNac (-100,000 to +100,000).
 *    valuacion (0.0 to 100.0).
 *
 * Choose the most appropriate type of data for each.
 */

CREATE DATABASE PruebaOR;

CREATE TABLE USUARIOS
(
	ID VARCHAR(5) PRIMARY KEY,
	NOMBRE VARCHAR(50),
	COD_PAIS VARCHAR(2),
	DIA_NAC NUMERIC(2),
	MES_NAC NUMERIC(2),
	ANYO_NAC NUMERIC(6),
	VALORACION NUMERIC(4,2)
);

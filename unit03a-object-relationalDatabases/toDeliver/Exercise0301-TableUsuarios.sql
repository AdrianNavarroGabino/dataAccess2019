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
	DIA_NAC NUMERIC,
	MES_NAC NUMERIC,
	ANYO_NAC NUMERIC,
	VALORACION NUMERIC
);

CREATE DOMAIN DIA_NAC AS NUMERIC(2)
CHECK (VALUE >= 1 AND VALUE <= 31);

CREATE DOMAIN MES_NAC AS NUMERIC(2)
CHECK (VALUE >= 1 AND VALUE <= 12);

CREATE DOMAIN ANYO_NAC AS NUMERIC(6)
CHECK (VALUE >= -100000 AND VALUE <= 100000);

CREATE DOMAIN VALORACION AS NUMERIC(4,1)
CHECK (VALUE >= 0.0 AND VALUE <= 100.0);

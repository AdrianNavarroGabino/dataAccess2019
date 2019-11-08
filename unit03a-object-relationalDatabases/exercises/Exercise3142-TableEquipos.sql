-- Adrián Navarro Gabino

/*
 * 3.1.4.2: Creates a "computers" database, with a first "computers" table.
 * For each device we will store for the moment: a code ("cod"), the brand
 * ("brand"), the model ("model"), the amount of memory ("cantMem", numerical)
 * and the unit in which it is measured that memory ("unitMem").
 */

CREATE TABLE EQUIPOS
(
	COD VARCHAR(10) PRIMARY KEY,
	MARCA VARCHAR(30),
	MODELO VARCHAR(50),
	CANT_MEMORIA NUMERIC(10, 2),
	UNIDAD_MEM UNIDAD_ALMAC
);

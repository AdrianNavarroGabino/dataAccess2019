-- Adrián Navarro Gabino

/*
 * Create a "portatiles" database and in it.
 * 
 * Create a "marcas" table, with id (alphabetic) and nombre fields.
 */

CREATE DATABASE portatiles;

CREATE TABLE marcas
(
	id TEXT PRIMARY KEY,
	nombre TEXT
);

/*
 * Create a "portatiles" table, with fields id (auto-numeric), marca (which will
 * refer to the "id" of "marcas", modelo, procesador, memoria).
 */

CREATE TABLE portatiles
(
	id SERIAL PRIMARY KEY,
	marca TEXT REFERENCES marcas(id),
	modelo TEXT,
	procesador TEXT,
	memoria TEXT
);

/*
 * Add three laptops and three brands, which are not totally related
 * (for example, some brand of which we don't have any laptops).
 */

INSERT INTO marcas
(id, nombre)
VALUES
('Toshiba', 'Toshiba');

INSERT INTO marcas
(id, nombre)
VALUES
('HP', 'HP');

INSERT INTO marcas
(id, nombre)
VALUES
('Dell', 'Dell');

INSERT INTO portatiles
(marca, modelo, procesador, memoria)
VALUES
('Dell', 'Inspiron 17 3780', 'Intel Core i7-8565U de 8.ª generación',
'Unidad de estado sólido PCIe NVMe M.2 de 128 GB (arranque) y disco duro SATA de 1 TB a 5400 rpm de 2,5"');

INSERT INTO portatiles
(marca, modelo, procesador, memoria)
VALUES
('Dell', 'XPS 13', 'Intel Core i7-10510U de 10.ª generación',
'SSD NVME PCIe M.2 de 1 TB');

INSERT INTO portatiles
(marca, modelo, procesador, memoria)
VALUES
('HP', 'HP ENVY', 'Intel Core i7-8550U',
'SSD de 256 GB PCIe NVMe M.2');

/*
 * Show the data of all laptops (brand name, model, processor, memory).
 */

SELECT nombre AS marca, modelo, procesador, memoria
FROM marcas, portatiles
WHERE marcas.id = marca;

/*
 * Show all the brands, together with the models of that brand (if they exist;
 * the brands of which we do not have any laptop model should also appear).
 */

SELECT nombre, modelo
FROM marcas LEFT OUTER JOIN portatiles
ON marcas.id = marca;

/*
 * Show all the models, together with the name of your brand (if there is one;
 * if any brand did not exist in the "marcas" table - not expected, as a
 * foreign key -), the model should still appear.
 */

SELECT nombre, modelo
FROM portatiles LEFT OUTER JOIN marcas
ON marca = marcas.id;

/*
 * Show the brands of which we don't have any models.
 */

SELECT nombre
FROM marcas
WHERE marcas.id NOT IN (SELECT marca
					    FROM portatiles);

/*
 * Show the computers of the most popular brand (the one of which we have
 * information on more laptops).
 */

SELECT nombre
FROM (SELECT nombre, count(*) AS COUNT1
	 FROM marcas, portatiles
	 WHERE marcas.id = marca
	 GROUP BY nombre
	 ORDER BY COUNT1 DESC
	 LIMIT 1) AS T1;
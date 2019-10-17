-- Adrián Navarro Gabino

/*
 * Insert the following data in the "muebles" table:
 *
 * 200111413, 'Mesa LACK' , 10 , 'Conglomerado'
 * 702611150, 'Silla MARKUS' , 159 , 'Varios'
 * 791229203, 'Sofá EKTORP' , 249 , 'Tela beige'
 * 
 * and make the queries that have been requested. Deliver them here as a
 * text file.
 * 
 * All data
 * Name of the articles of more than 100 euros ordered (by name, ascending)
 * Chairs less than 200 euros sorted by descending price
 * Change the price of Ektorp furniture to 269
 * Add the "unidades" field to the table (may have values ​​from 0 to 99,999)
 */

INSERT INTO muebles
(id, nombre, precio, materiales)
VALUES
(200111413, 'Mesa LACK' , 10 , 'Conglomerado');

INSERT INTO muebles
(id, nombre, precio, materiales)
VALUES
(702611150, 'Silla MARKUS' , 159 , 'Varios');

INSERT INTO muebles
(id, nombre, precio, materiales)
VALUES
(791229203, 'Sofá EKTORP' , 249 , 'Tela beige');

SELECT *
FROM muebles;

SELECT nombre
FROM muebles
WHERE precio > 100
ORDER BY nombre ASC;

SELECT *
FROM muebles
WHERE UPPER(nombre) LIKE '%SILLA%'
  AND precio < 200
ORDER BY precio DESC;

UPDATE muebles
SET precio = 269
WHERE UPPER(nombre) LIKE '%EKTORP%';

ALTER TABLE muebles ADD unidades integer;
-- Adrián Navarro Gabino

/*
 * Using "pgAdmin", connect to your database "dia01". Shows the existing data
 * in the articles table. Add a new article. It shows those that have a
 * price lower than 10 euros.
 */

ALTER TABLE ARTICULOS ADD PRECIO NUMERIC NOT NULL DEFAULT 0;

UPDATE ARTICULOS
SET PRECIO = ID * 3;

SELECT *
FROM ARTICULOS
WHERE PRECIO < 10;
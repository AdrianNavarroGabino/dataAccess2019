-- Adrián Navarro Gabino

/*
 * Create a "personal" database for our software company. We begin to create a
 * "programadores" table and a "lenguajes" table, with an M: M (many to many)
 * "conocer" relationship. In this relationship there will be a "nivel"
 * attribute, which is the level that each programmer has in a certain language,
 * from 0 to 10.
 *
 * Add a considerable amount of data to be able to make inquiries later.
 */

CREATE DATABASE personal;

CREATE TABLE programadores
(
	nombre TEXT PRIMARY KEY
);

CREATE TABLE lenguajes
(
	lenguaje TEXT PRIMARY KEY
);

CREATE TABLE CONOCER
(
	nombre TEXT REFERENCES programadores(nombre),
	lenguaje TEXT REFERENCES lenguajes(lenguaje),
	nivel SMALLINT CONSTRAINT ONE_TO_TEN CHECK (nivel >= 0 AND nivel <= 10),
	CONSTRAINT PK_CONOCER PRIMARY KEY (nombre, lenguaje)
);

INSERT INTO programadores
VALUES
('Bautista Encina');

INSERT INTO programadores
VALUES
('Juan Miguel de Jesús');

INSERT INTO programadores
VALUES
('Marcela Aguilar');

INSERT INTO programadores
VALUES
('Alonso Navarro');

INSERT INTO programadores
VALUES
('César Díaz');

INSERT INTO programadores
VALUES
('Vicente Domínguez');

INSERT INTO programadores
VALUES
('Israel Alcoverde');

INSERT INTO programadores
VALUES
('Iván Burguete');

INSERT INTO programadores
VALUES
('Miguel Ángel Betanzos');

INSERT INTO lenguajes
VALUES
('Java');

INSERT INTO lenguajes
VALUES
('JavaScript');

INSERT INTO lenguajes
VALUES
('C#');

INSERT INTO lenguajes
VALUES
('PHP');

INSERT INTO lenguajes
VALUES
('Python');

INSERT INTO lenguajes
VALUES
('Go');

INSERT INTO lenguajes
VALUES
('Ruby');

INSERT INTO lenguajes
VALUES
('Kotlin');

INSERT INTO conocer
VALUES
('Bautista Encina', 'Java', 8);

INSERT INTO conocer
VALUES
('Bautista Encina', 'JavaScript', 7);

INSERT INTO conocer
VALUES
('Bautista Encina', 'C#', 9);

INSERT INTO conocer
VALUES
('Juan Miguel de Jesús', 'PHP', 10);

INSERT INTO conocer
VALUES
('Marcela Aguilar', 'Java', 5);

INSERT INTO conocer
VALUES
('Marcela Aguilar', 'PHP', 9);

INSERT INTO conocer
VALUES
('Alonso Navarro', 'JavaScript', 10);

INSERT INTO conocer
VALUES
('Alonso Navarro', 'Java', 7);

INSERT INTO conocer
VALUES
('Alonso Navarro', 'PHP', 8);

/*
 * From the "personal" database, from PostgreSQL, create the following queries:
 *
 * Show the list of programmers we have, along with the languages ​​they know.
 */

SELECT nombre, lenguaje
FROM conocer;

/*
 * Show the list of programmers we have, along with the languages ​​they know,
 * even if they don't know any.
 */

SELECT programadores.nombre, lenguaje
FROM programadores LEFT OUTER JOIN conocer
ON programadores.nombre = conocer.nombre;

/*
 * Show the list of programmers that we have and for which no language appears.
 */

SELECT nombre
FROM programadores
WHERE nombre NOT IN (SELECT nombre FROM conocer);

/*
 * Show the programmers who program in Java.
 */

SELECT nombre
FROM conocer
WHERE lenguaje = 'Java';

/*
 * Show the programmers that program in Java but not in Javascript.
 */

SELECT nombre
FROM conocer
WHERE lenguaje = 'Java'
  AND nombre NOT IN (SELECT nombre
					FROM conocer
					WHERE lenguaje = 'JavaScript');

/*
 * Show the language (or languages) for which we have more programmers.
 */

SELECT lenguaje
FROM conocer
GROUP BY lenguaje
HAVING COUNT(*) IN
		(SELECT COUNT(*) AS COUNT1
		FROM conocer
		GROUP BY lenguaje
		ORDER BY COUNT1 DESC
		LIMIT 1);

/*
 * Create an "expertos" view that shows you all programmers who master 3
 * or more languages.
 */
    
CREATE OR REPLACE VIEW expertos
AS
SELECT *
FROM programadores
WHERE nombre IN (SELECT nombre
				 FROM conocer
				 GROUP BY nombre
				 HAVING COUNT(*) >= 3);
				 
SELECT *
FROM expertos;

/*
 * Create a "uso" table with 3 fields: tipo, descripcion, fecha. Create a
 * "trigger" that, every time a data is deleted from a programmer, write down
 * a record with type "borrado", name of the programmer as description and the
 * current date.
 */

CREATE TABLE uso
(
	tipo TEXT,
	descripcion TEXT,
	fecha DATE,
	CONSTRAINT PK_USO PRIMARY KEY (tipo, descripcion, fecha)
);

CREATE OR REPLACE FUNCTION fn_borrar_prog()
RETURNS trigger
LANGUAGE plpgsql    
AS $$
BEGIN
	INSERT INTO uso
	(tipo, descripcion, fecha)
	VALUES
	('borrado', OLD.nombre, NOW());
END;
$$;


CREATE TRIGGER tr_borrar_prog
AFTER DELETE ON programadores
FOR EACH ROW
EXECUTE PROCEDURE fn_borrar_prog();
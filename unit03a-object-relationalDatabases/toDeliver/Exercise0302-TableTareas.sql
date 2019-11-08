-- Adrián Navarro Gabino

/*
 * Create a "tareas" table, with fields: id (auto-numeric), description (text),
 * expected date (date), annotated date (date with hour, minutes and seconds).
 * Choose the most appropriate type of data for each. Add some example data.
 */

CREATE TABLE TAREAS
(
	ID SERIAL PRIMARY KEY,
	DESCRIPCION VARCHAR(100),
	FECHA_PREVISTA DATE,
	FECHA_ANOTADO TIMESTAMP 
);

INSERT INTO TAREAS
(DESCRIPCION, FECHA_PREVISTA, FECHA_ANOTADO)
VALUES
('Task 1', DATE('2020-02-21'), '2019-11-05 16:50');

INSERT INTO TAREAS
(DESCRIPCION, FECHA_PREVISTA, FECHA_ANOTADO)
VALUES
('Task 2', DATE('2021-01-01'), '2019-10-30 12:45');

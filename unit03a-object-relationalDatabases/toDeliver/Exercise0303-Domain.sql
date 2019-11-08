-- Adrián Navarro Gabino

/*
 * Modify the "tareas" table, adding a priority field, whose values ​​will range
 * from 0 to 5. Create a domain for this type of data. Add some example data,
 * both valid and out of range and see what happens.
 */

CREATE DOMAIN DOM_PRIORIDAD AS NUMERIC(1)
CHECK (VALUE >= 0 AND VALUE <= 5);

ALTER TABLE TAREAS ADD COLUMN PRIORIDAD DOM_PRIORIDAD;

-- Valid insertion

INSERT INTO TAREAS
(DESCRIPCION, FECHA_PREVISTA, FECHA_ANOTADO, PRIORIDAD)
VALUES
('Task 3', DATE('2019-12-01'), '2019-08-09 11:10', 4);

-- Invalid insertion
/*
INSERT INTO TAREAS
(DESCRIPCION, FECHA_PREVISTA, FECHA_ANOTADO, PRIORIDAD)
VALUES
('Task 4', DATE('2019-12-01'), '2019-08-09 11:10', 6);
*/

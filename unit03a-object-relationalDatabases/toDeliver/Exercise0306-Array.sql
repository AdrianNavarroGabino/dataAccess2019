-- Adrián Navarro Gabino

/*
 * Modify the "tareas" table, adding a "comentarios" field, consisting of one
 * or more lines. Enter several sample data and then show them.
 */

ALTER TABLE TAREAS ADD COLUMN COMENTARIOS TEXT[];

INSERT INTO TAREAS
(DESCRIPCION, COMENTARIOS)
VALUES
('Task 7', '{"Comment 1", "Comment 2", "Comment 3"}');

-- Display as array

SELECT DESCRIPCION, COMENTARIOS
FROM TAREAS
WHERE DESCRIPCION = 'Task 7';

-- Displayed with unnest

SELECT DESCRIPCION, UNNEST(COMENTARIOS)
FROM TAREAS
WHERE DESCRIPCION = 'Task 7';

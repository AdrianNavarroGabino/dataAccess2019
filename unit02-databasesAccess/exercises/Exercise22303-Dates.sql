-- Adrián Navarro Gabino

/*
 * 2.2.3.3: Modify the first task so that it has a date of tomorrow and is not
 * finished, and the second so that it has a date of the day after tomorrow and
 * is not finished. Add two more tasks.
 */

UPDATE tareas
SET FECHA_PREVISTA = CURRENT_DATE + 1,
    TERMINADA = FALSE
WHERE CODIGO = 1;

UPDATE tareas
SET FECHA_PREVISTA = CURRENT_DATE + 2,
    TERMINADA = FALSE
WHERE CODIGO = 2;

INSERT INTO tareas
(CODIGO, DESCRIPCION, FECHA_PREVISTA, TERMINADA)
VALUES
(3, 'Task1', CURRENT_DATE + 7, FALSE);

INSERT INTO tareas
(CODIGO, DESCRIPCION, FECHA_PREVISTA, TERMINADA)
VALUES
(4, 'Task2', CURRENT_DATE + 14, FALSE);
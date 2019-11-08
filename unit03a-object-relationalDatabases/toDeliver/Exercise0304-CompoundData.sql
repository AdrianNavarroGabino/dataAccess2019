-- Adrián Navarro Gabino

/*
 * Modify the "tasks" table, adding a field "Employee time", which will be a
 * composite data consisting of two hours: the "Start time" and the "End time".
 * Add some example data. Show only the data first and then the difference
 * between the two hours.
 */

CREATE TYPE tiempo_emp AS (HORA_INICIAL TIME, HORA_FINAL TIME);

ALTER TABLE TAREAS ADD COLUMN TIEMPO_EMPLEADO TIEMPO_EMP;

INSERT INTO TAREAS
(DESCRIPCION, FECHA_PREVISTA, FECHA_ANOTADO, PRIORIDAD, TIEMPO_EMPLEADO)
VALUES
('Task 4', DATE('2020-01-01'), '2019-01-09 09:00', 3, ('16:00', '20:00'));

SELECT TIEMPO_EMPLEADO
FROM TAREAS
WHERE DESCRIPCION = 'Task 4';

SELECT (TIEMPO_EMPLEADO).HORA_FINAL - (TIEMPO_EMPLEADO).HORA_INICIAL AS TIEMPO
FROM TAREAS
WHERE DESCRIPCION = 'Task 4';

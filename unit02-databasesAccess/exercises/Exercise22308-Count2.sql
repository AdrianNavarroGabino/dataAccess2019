-- Adrián Navarro Gabino

/*
 * 2.2.3.8: Shows the amount of tasks for each day.
 */

SELECT FECHA_PREVISTA, COUNT(*) AS CANTIDAD_TAREAS
FROM tareas
GROUP BY FECHA_PREVISTA;
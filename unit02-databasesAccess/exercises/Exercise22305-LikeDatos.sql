-- Adrián Navarro Gabino

/*
 * 2.2.3.5: Shows the tasks that contain the word "datos".
 */

SELECT *
FROM tareas
WHERE UPPER(DESCRIPCION) LIKE '%DATOS%';
-- Adrián Navarro Gabino

/*
 * 2.2.3.6: Shows the code of the last task (the largest code).
 */

SELECT *
FROM tareas
WHERE CODIGO = (SELECT MAX(CODIGO) FROM tareas);
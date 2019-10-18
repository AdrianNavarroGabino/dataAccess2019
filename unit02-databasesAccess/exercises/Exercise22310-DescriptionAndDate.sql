-- Adrián Navarro Gabino

/*
 * 2.2.3.10: Display the description and date of the tasks whose code is lower
 * than the code of the oldest task (less code) that contains the word
 * "NetBeans".
 */

SELECT DESCRIPCION, FECHA_PREVISTA
FROM tareas
WHERE ID < (SELECT MIN(CODIGO)
                FROM tareas
                WHERE UPPER(DESCRIPCION) LIKE '%NETBEANS%');
-- Adrián Navarro Gabino

/*
 * 2.2.3.9: Show the code of the oldest task (least code) that contains the
 * word "NetBeans".
 */

SELECT *
FROM tareas
WHERE CODIGO = (SELECT MIN(CODIGO)
                FROM tareas
                WHERE UPPER(DESCRIPCION) LIKE '%NETBEANS%');
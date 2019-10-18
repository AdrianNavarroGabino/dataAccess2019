-- Adrián Navarro Gabino

/*
 * 2.2.3.16: Show the tasks belonging to the category of which more tasks we
 * have stored.
 */

SELECT *
FROM tareas
WHERE CODCATEG IN
    (SELECT CODCATEG
    FROM tareas
    GROUP BY CODCATEG
    HAVING COUNT(*) IN
        (SELECT COUNT(*) AS COUNT1
        FROM tareas
        GROUP BY CODCATEG
        ORDER BY COUNT1 DESC
        LIMIT 1));
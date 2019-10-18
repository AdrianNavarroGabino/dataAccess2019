-- Adrián Navarro Gabino

/*
 * 2.2.3.15: Displays the description of all tasks, together with the
 * description of the category to which they belong, for all tasks (some
 * category may appear as NULL) and for all categories (and some task may
 * appear as NULL, if that category has no assigned tasks).
 */

SELECT TAR.DESCRIPCION, CATE.DESCRIPCION
FROM tareas TAR LEFT JOIN categorias CATE
ON CODCATEG = CATE.CODIGO
UNION
SELECT NULL, DESCRIPCION
FROM categorias
WHERE CODIGO NOT IN (SELECT CODCATEG FROM tareas);
-- Adrián Navarro Gabino

/*
 * 2.2.3.13: Show the description of all the tasks, together with the
 * description of the category to which they belong or NULL in case that
 * category has not been indicated.
 */

SELECT TAR.DESCRIPCION, CATE.DESCRIPCION
FROM tareas TAR LEFT JOIN categorias CATE
ON CODCATEG = CATE.CODIGO;
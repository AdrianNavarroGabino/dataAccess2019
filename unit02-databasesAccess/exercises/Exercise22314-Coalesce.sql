-- Adrián Navarro Gabino

/*
 * 2.2.3.14: Show the description of all the tasks, together with the
 * description of the category to which they belong or "(None)"" in case
 * that category has not been indicated.
 */

SELECT TAR.DESCRIPCION, COALESCE(CATE.DESCRIPCION, '(Ninguna)') AS DESCR_CATEG
FROM tareas TAR LEFT JOIN categorias CATE
ON CODCATEG = CATE.CODIGO;
-- Adrián Navarro Gabino

/*
 * 2.2.3.12: Display the description of all tasks, together with the
 * description of the category to which they belong, for all categories of
 * which description has been indicated.
 */

SELECT TAR.DESCRIPCION, CATE.DESCRIPCION
FROM tareas TAR, categorias CATE
WHERE CODCATEG = CATE.CODIGO;
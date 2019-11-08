-- Adrián Navarro Gabino

/*
 * 3.1.5.4: Shows only equipment that does not have alternative names.
 */

SELECT MARCA, MODELO, NOMBRES_ALTERNATIVOS
FROM EQUIPOS
WHERE NOMBRES_ALTERNATIVOS IS NOT NULL;

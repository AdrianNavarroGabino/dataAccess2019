-- Adrián Navarro Gabino

/*
 * 3.1.5.1: In the "computers" database, expand the "computers" table, with a
 * field "alternative names", which will be a vector of texts.
 */

ALTER TABLE EQUIPOS ADD COLUMN nombres_alternativos TEXT[];

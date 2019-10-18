-- Adrián Navarro Gabino

/*
 * 2.2.3.11: Create a new “categories” table. Each category will have two
 * fields: a code (text, primary key) and the description. Add the category
 * "E", "Studies" and the category "O", "Leisure". Modify the "tasks" table to
 * add the category code ("codcateg"). Assign category “E” to tasks 1 and 2.
 */

CREATE TABLE categorias
(
    CODIGO TEXT PRIMARY KEY,
    DESCRIPCION TEXT
);

INSERT INTO categorias
(CODIGO, DESCRIPCION)
VALUES
('E', 'Estudios');

INSERT INTO categorias
(CODIGO, DESCRIPCION)
VALUES
('O', 'Ocio');

ALTER TABLE tareas
    ADD  CODCATEG TEXT REFERENCES categorias(CODIGO);

UPDATE tareas
SET CODCATEG = 'E'
WHERE CODIGO = 1 OR CODIGO = 2;


-- Adrián Navarro Gabino

/*
 * Modify the "tareas" table, adding a "categoria" field, which may take the
 * value "trabajo", "ocio" or "familia". Add some example data.
 */

CREATE TYPE CAT AS ENUM('trabajo', 'ocio', 'familia');

ALTER TABLE TAREAS ADD COLUMN CATEGORIA CAT;

INSERT INTO TAREAS
(DESCRIPCION, CATEGORIA)
VALUES
('Task 5', 'familia');

INSERT INTO TAREAS
(DESCRIPCION, CATEGORIA)
VALUES
('Task 6', 'ocio');

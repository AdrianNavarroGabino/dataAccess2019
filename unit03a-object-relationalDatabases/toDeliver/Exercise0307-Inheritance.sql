-- Adrián Navarro Gabino

/*
 * Create an "encargos" table, which inherits from the "tareas" table, and add
 * a "requested by" field, which will be a text, the name of the person who has
 * commissioned that task. Add your primary key and try adding data and showing
 * both tables and one of them.
 */

CREATE TABLE ENCARGOS
(PEDIDO_POR VARCHAR(30))
INHERITS(TAREAS);

ALTER TABLE ENCARGOS ADD CONSTRAINT
PK_ENCARGOS PRIMARY KEY (ID);

INSERT INTO ENCARGOS
(DESCRIPCION, PEDIDO_POR)
VALUES
('Order 1', 'John');

INSERT INTO ENCARGOS
(DESCRIPCION, PEDIDO_POR)
VALUES
('Order 2', 'James');

SELECT * FROM ENCARGOS;

SELECT * FROM TAREAS;

SELECT * FROM ONLY TAREAS;

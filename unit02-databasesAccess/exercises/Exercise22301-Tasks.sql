-- Adrián Navarro Gabino

/*
 * 2.2.3.1: Create a database called “BdTareas”, which we will use to write
 * down tasks. In principle, there will be a single table called "tareas", with
 * two fields: the code of the task (numerical, which will be the primary key)
 * and the description of the task. Add two tasks: 1 will be "Repasar acceso a
 * datos" and 2 will be "Instalar NetBeans".
 */

CREATE DATABASE BdTareas;

CREATE TABLE tareas
(
    CODIGO NUMERIC PRIMARY KEY,
    DESCRIPCION VARCHAR(200)
);

INSERT INTO tareas
(CODIGO, DESCRIPCION)
VALUES
(1, 'Repasar acceso a datos');

INSERT INTO tareas
(CODIGO, DESCRIPCION)
VALUES
(1, 'Instalar NetBeans');
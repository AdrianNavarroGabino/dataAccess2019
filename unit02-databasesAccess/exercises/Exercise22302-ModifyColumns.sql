-- Adrián Navarro Gabino

/*
 * 2.2.3.2: Modify the table to add two fields (at the end of the existing
 * ones): one will be the scheduled date for the task and another will be
 * whether it is finished or not.
 */

ALTER TABLE tareas
    ADD FECHA_PREVISTA DATE,
    ADD TERMINADA BOOLEAN NOT NULL DEFAULT FALSE;
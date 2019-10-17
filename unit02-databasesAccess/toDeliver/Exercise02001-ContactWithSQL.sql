-- Adrián Navarro Gabino

/*
 * Create a database called "ikea", with a database "muebles", which at the
 * moment will contain 4 fields: an id, a name, a price and materials.
 */

CREATE DATABASE ikea;

CREATE TABLE muebles
(
    id INTEGER PRIMARY KEY,
    nombre TEXT,
    precio REAL,
    materiales TEXT
);
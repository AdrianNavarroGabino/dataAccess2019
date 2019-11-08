-- Adrián Navarro Gabino

/*
 * 3.1.4.1: Creates an enumerated data type called "Almac unit", which will
 * refer to one of the most common storage units in computing: byte, kilobyte,
 * megagbyte, gigabyte, terabyte.
 */

CREATE TYPE UNIDAD_ALMAC AS
ENUM('byte', 'kilobyte', 'megabyte', 'gigabyte', 'terabyte');

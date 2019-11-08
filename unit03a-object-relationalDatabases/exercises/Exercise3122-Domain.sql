-- Adrián Navarro Gabino

/*
 * 3.1.2.2: Create a domain called "numero_habitantes", which allows to
 * save non-negative integers, of a maximum of 10 digits.
 */

CREATE DOMAIN cantidadHabitantes AS NUMERIC(10)
CHECK (VALUE >= 0);

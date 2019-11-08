-- Adrián Navarro Gabino

/*
 * 3.1.3.3: Add two example cities. On Wikipedia you can see the location of
 * many cities, but usually in the format "second minute degree"; to see them
 * as a single figure with decimals, it is usually enough to click on that
 * location.
 */

INSERT INTO CIUDADES
(NOMBRE, UBIC)
VALUES
('Alicante', (38.35782, -0.5425641));

INSERT INTO CIUDADES
(NOMBRE, UBIC)
VALUES
('Valencia', (39.4077013, -0.5015955));

SELECT NOMBRE, (UBIC).LATITUD, (UBIC).LONGITUD FROM CIUDADES;

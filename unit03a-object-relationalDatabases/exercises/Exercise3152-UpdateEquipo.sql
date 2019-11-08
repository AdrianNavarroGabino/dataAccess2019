-- Adrián Navarro Gabino

/*
 * 3.1.5.2: Add the alternative name "Timex Sinclair 2068" to the ZxSpectrum
 * 48K, which is the name given by your distributor in the United States.
 */

UPDATE EQUIPOS
SET NOMBRES_ALTERNATIVOS = '{"Timex Sinclair 2068"}'
WHERE COD = 'spec48';

-- Adrián Navarro Gabino

/*
 * 3.1.4.3: Add these three devices:
 * The Sinclair (brand) ZxSpectrum 48K
 * (model), with code "spec48" and memory of 48 kilobytes
 * The IBM (brand) PS / 1 2011 (model), with code "ps1- 2011 ", with 1 megabyte memory.
 * Raspberry Pi (brand) 3 B (model), with code "raspi3b", with 1 gigabyte memory.
 */

INSERT INTO EQUIPOS
(COD, MARCA, MODELO, CANT_MEMORIA, UNIDAD_MEM)
VALUES
('spec48', 'Sinclair', 'ZxSpectrum 48K', 48, 'kilobyte');

INSERT INTO EQUIPOS
(COD, MARCA, MODELO, CANT_MEMORIA, UNIDAD_MEM)
VALUES
('ps1-2011', 'IBM', 'PS/1 201', 1, 'megabyte');

INSERT INTO EQUIPOS
(COD, MARCA, MODELO, CANT_MEMORIA, UNIDAD_MEM)
VALUES
('raspi3b', 'Raspberry Pi', '3B', 1, 'gigabyte');

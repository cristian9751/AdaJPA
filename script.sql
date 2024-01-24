
DROP DATABASE IF EXISTS SerpisFP;
CREATE DATABASE SerpisFP;
USE SerpisFP;
DROP TABLE IF EXISTS GRUPO_CP19;
DROP TABLE IF EXISTS ALUMNO_CP19;
DROP TABLE IF EXISTS PROYECTO_CP19;
DROP TABLE IF EXISTS MODULO_CP19;
DROP TABLE IF EXISTS MATRICULA_CP19;
CREATE TABLE GRUPO_CP19
(
CODGRUPO INT,
DESCRIPCION VARCHAR (50),
AULA VARCHAR (10),
PRIMARY KEY (CODGRUPO)
);
CREATE TABLE ALUMNO_CP19
(
NIA VARCHAR (10),
NOMBRE VARCHAR (50),
APELLIDOS VARCHAR (50),
CODGRUPO INT NOT NULL,
PRIMARY KEY (NIA ),
FOREIGN KEY (CODGRUPO) REFERENCES GRUPO_CP19 (CODGRUPO)
);
CREATE TABLE MODULO_CP19
(
CODMODULO INT,
DESCRIPCION VARCHAR (50),
NUMHORAS INT,
PRIMARY KEY (CODMODULO)
);
-- (N-N ALUMNO Y MÓDULO)
CREATE TABLE MATRICULA_CP19
(
IDENROLLMENT INT PRIMARY KEY AUTO_INCREMENT,    
NIA VARCHAR (10),
CODMODULO INT,
DESCRIPCION VARCHAR (50),
FOREIGN KEY (NIA) REFERENCES ALUMNO_CP19 (NIA),
FOREIGN KEY (CODMODULO) REFERENCES MODULO_CP19 (CODMODULO)
);
CREATE TABLE PROYECTO_CONVOCATORIA_CP19
(
CODPROYECTO VARCHAR (10),
TITULO VARCHAR (200),
NIA VARCHAR (10) NOT NULL UNIQUE,
PRIMARY KEY (CODPROYECTO),
FOREIGN KEY (NIA ) REFERENCES ALUMNO_CP19 (NIA)
);

-- Add Groups
INSERT INTO GRUPO_CP19 (CODGRUPO, DESCRIPCION, AULA) VALUES
(1, '2DAMC', 'Aula1'),
(2, '2DAMR', 'Aula2'),
(3, '2ASIRC', 'Aula3');

-- Add Students
INSERT INTO ALUMNO_CP19 (NIA, NOMBRE, APELLIDOS, CODGRUPO) VALUES
('3', 'CRISTIAN', 'POPICA', 1),
('4', 'DANIEL', 'RUIZ MONTERO', 1),
('5', 'DAVID', 'GUAITA ONSURBE', 1),
('6', 'DUNCAN', 'RUA VALIENTE', 1),
('7', 'HERMES', 'PEREZ PARRONDO', 1);
('123456', 'Nuevo', 'Estudiante', 1);

-- Add Modules
INSERT INTO MODULO_CP19 (CODMODULO, DESCRIPCION, NUMHORAS) VALUES
(1, 'ADA', 100),
(2, 'PSP', 100),
(3, 'PMDM', 100),
(4, 'SGE', 100);

-- Add Matriculations
INSERT INTO MATRICULA_CP19 (NIA, CODMODULO, DESCRIPCION) VALUES
('3', 1, 'ADA'),
('4', 2, 'PSP'),
('3', 2, 'PSP');

-- Add Project
INSERT INTO PROYECTO_CONVOCATORIA_CP19 (CODPROYECTO, TITULO, NIA) VALUES
('1234', 'App per a reptes personals', '3');

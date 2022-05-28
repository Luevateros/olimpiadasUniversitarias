drop database if EXISTS proyecto;
CREATE database proyecto;

USE proyecto;


CREATE TABLE IF NOT EXISTS `Disciplina` (
  `iddisciplina` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `responsable` varchar(50) NOT NULL,
  `imagen` varchar(60) DEFAULT NULL,
  `descripcion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`iddisciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `Usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `correo` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `tipousuario` enum('ADMIN','ENTRENADOR','COMPETIDOR','JUEZ') NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `correo_UNIQUE` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `Competidor` (
  `idcompetidor` char(9) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidop` varchar(45) NOT NULL,
  `apellidom` varchar(45) NOT NULL,
  `sexo` tinyint(3) NOT NULL,
  `nacimiento` varchar(45)  NOT NULL,
  `escuela` varchar(45) NOT NULL,
  `idusuario` int(11) NOT NULL,
   `iddisciplina` int(11),
  PRIMARY KEY (`idcompetidor`),
  KEY `fk_evento_disciplina_idx` (`iddisciplina`),
  CONSTRAINT `fk_evento_disciplina` FOREIGN KEY (`iddisciplina`) REFERENCES `disciplina` (`iddisciplina`),
  UNIQUE KEY `idcompetidor_UNIQUE` (`idcompetidor`),
  KEY `fk_usuario_idx` (`idusuario`),
  CONSTRAINT `fk_competidor_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `Entrenador` (
  `identrenador` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidop` varchar(45) NOT NULL,
  `apellidom` varchar(45) NOT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`identrenador`),
  KEY `fk_usuario_idx` (`idusuario`),
  KEY `fk_entrenador_usuario_idx` (`idusuario`),
  CONSTRAINT `fk_entrenador_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE IF NOT EXISTS `Asesorar` (
  `idcompetidor` char(9) NOT NULL,
  `identrenador` int(11) NOT NULL,
  KEY `fk_competidor_idx` (`idcompetidor`),
  KEY `fk_entrenador_idx` (`identrenador`),
  CONSTRAINT `fk_competidor` FOREIGN KEY (`idcompetidor`) REFERENCES `competidor` (`idcompetidor`),
  CONSTRAINT `fk_entrenador` FOREIGN KEY (`identrenador`) REFERENCES `entrenador` (`identrenador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE IF NOT EXISTS `Juez` (
  `idjuez` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidop` varchar(45) NOT NULL,
  `apellidom` varchar(45) NOT NULL,
  `iddisciplina` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idjuez`),
  KEY `fk_juez_usuario_idx` (`idusuario`),
  KEY `fk_juez_disciplina_idx` (`iddisciplina`),
  CONSTRAINT `fk_juez_disciplina` FOREIGN KEY (`iddisciplina`) REFERENCES `disciplina` (`iddisciplina`),
  CONSTRAINT `fk_juez_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `Calificacion` (
  `idcalificacion` int(11) NOT NULL AUTO_INCREMENT,
  `comentario` varchar(100) NOT NULL,
  `calificacion` float NOT NULL,
  `idcompetidor` char(9) NOT NULL,
  `idjuez` int(11) NOT NULL,
  PRIMARY KEY (`idcalificacion`),
  KEY `idcalificacion_juez_idx` (`idjuez`),
  KEY `idcalificacion_competidor_idx` (`idcompetidor`),
  CONSTRAINT `idcalificacion_competidor` FOREIGN KEY (`idcompetidor`) REFERENCES `competidor` (`idcompetidor`),
  CONSTRAINT `idcalificacion_juez` FOREIGN KEY (`idjuez`) REFERENCES `juez` (`idjuez`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE VIEW IF NOT EXISTS
	CalificacionEntrenador AS
    SELECT calificacion.idcalificacion,
    competidor.nombre as nombre,
    competidor.apellidop, competidor.apellidom,
    juez.nombre as juez, calificacion, disciplina.nombre as disciplina
    FROM calificacion INNER JOIN asesorar
    ON calificacion.idcompetidor = asesorar.idcompetidor
    INNER JOIN competidor
    ON calificacion.idcompetidor = asesorar.idcompetidor
    INNER JOIN disciplina
    ON disciplina.iddisciplina = disciplina.iddisciplina
    INNER JOIN juez
    ON juez.idjuez = calificacion.idjuez;
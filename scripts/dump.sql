-- MySQL dump 10.13  Distrib 8.0.39, for Linux (aarch64)
--
-- Host: localhost    Database: sgd
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE sgd;
--
-- Table structure for table `REVINFO`
--

DROP TABLE IF EXISTS `REVINFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REVINFO` (
  `REV` int NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `adscripcion_carrera`
--

DROP TABLE IF EXISTS `adscripcion_carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adscripcion_carrera` (
  `usuario_id` bigint NOT NULL,
  `carrera_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`carrera_id`),
  KEY `FK4apq2nr901m48gx7m3itya89g` (`carrera_id`),
  CONSTRAINT `FK4apq2nr901m48gx7m3itya89g` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`id`),
  CONSTRAINT `FKitjgm1knaseeyx4yebk0raubs` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `adscripcion_carrera_AUD`
--

DROP TABLE IF EXISTS `adscripcion_carrera_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adscripcion_carrera_AUD` (
  `REV` int NOT NULL,
  `usuario_id` bigint NOT NULL,
  `carrera_id` bigint NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  PRIMARY KEY (`REV`,`usuario_id`,`carrera_id`),
  CONSTRAINT `FK6uoov347h77t93leyyb75nig` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `adscripcion_ua`
--

DROP TABLE IF EXISTS `adscripcion_ua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adscripcion_ua` (
  `usuario_id` bigint NOT NULL,
  `ua_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`ua_id`),
  KEY `FKhp6nkkrn084va7fo3u76utnrs` (`ua_id`),
  CONSTRAINT `FKhp6nkkrn084va7fo3u76utnrs` FOREIGN KEY (`ua_id`) REFERENCES `unidades_academicas` (`id`),
  CONSTRAINT `FKhpdaratidplytkw3j9eecyhby` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `adscripcion_ua_AUD`
--

DROP TABLE IF EXISTS `adscripcion_ua_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adscripcion_ua_AUD` (
  `REV` int NOT NULL,
  `usuario_id` bigint NOT NULL,
  `ua_id` bigint NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  PRIMARY KEY (`REV`,`usuario_id`,`ua_id`),
  CONSTRAINT `FK13lsxr1r4aeilnca0i16svbgn` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aprobaciones`
--

DROP TABLE IF EXISTS `aprobaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aprobaciones` (
  `id` bigint NOT NULL,
  `estado` tinyint DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `comentarios` varchar(255) DEFAULT NULL,
  `despachoData` mediumblob,
  `despachoFileName` varchar(255) DEFAULT NULL,
  `id_programa` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaybkk2lg4ctwym1tscym3sab8` (`id_programa`),
  KEY `FKm3o1mwa61r5odyft65wrhofb3` (`id_usuario`),
  CONSTRAINT `FKaybkk2lg4ctwym1tscym3sab8` FOREIGN KEY (`id_programa`) REFERENCES `programas` (`id`),
  CONSTRAINT `FKm3o1mwa61r5odyft65wrhofb3` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `aprobaciones_chk_1` CHECK ((`estado` between 0 and 8))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aprobaciones_SEQ`
--

DROP TABLE IF EXISTS `aprobaciones_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aprobaciones_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `areas`
--

DROP TABLE IF EXISTS `areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `areas` (
  `id` bigint NOT NULL,
  `habilitada` bit(1) NOT NULL,
  `nombreArea` varchar(255) DEFAULT NULL,
  `id_carrera` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtpepeu3tnphberm1osngggpv2` (`id_carrera`),
  CONSTRAINT `FKtpepeu3tnphberm1osngggpv2` FOREIGN KEY (`id_carrera`) REFERENCES `carreras` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `areas_AUD`
--

DROP TABLE IF EXISTS `areas_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `areas_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `habilitada` bit(1) DEFAULT NULL,
  `nombreArea` varchar(255) DEFAULT NULL,
  `id_carrera` bigint DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FK5nbtopds0isjp4m5fjg3vv0nf` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `areas_SEQ`
--

DROP TABLE IF EXISTS `areas_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `areas_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bibliografia`
--

DROP TABLE IF EXISTS `bibliografia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibliografia` (
  `id` bigint NOT NULL,
  `esTitulo` bit(1) NOT NULL,
  `orden` int DEFAULT NULL,
  `titulo` text,
  `id_programa` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm1t3n65j11h8rfc21oi7djvm7` (`id_programa`),
  CONSTRAINT `FKm1t3n65j11h8rfc21oi7djvm7` FOREIGN KEY (`id_programa`) REFERENCES `programas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bibliografia_AUD`
--

DROP TABLE IF EXISTS `bibliografia_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibliografia_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `esTitulo` bit(1) DEFAULT NULL,
  `orden` int DEFAULT NULL,
  `titulo` varchar(512) DEFAULT NULL,
  `id_programa` bigint DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FKn2cik0w18mtroxcueqeho0s2h` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bibliografia_SEQ`
--

DROP TABLE IF EXISTS `bibliografia_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibliografia_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carreras`
--

DROP TABLE IF EXISTS `carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carreras` (
  `id` bigint NOT NULL,
  `habilitada` bit(1) NOT NULL,
  `nombreCarrera` varchar(255) DEFAULT NULL,
  `id_ua` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK55oiy3lgegaim73repr57wcyb` (`id_ua`),
  CONSTRAINT `FK55oiy3lgegaim73repr57wcyb` FOREIGN KEY (`id_ua`) REFERENCES `unidades_academicas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carreras_AUD`
--

DROP TABLE IF EXISTS `carreras_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carreras_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `habilitada` bit(1) DEFAULT NULL,
  `nombreCarrera` varchar(255) DEFAULT NULL,
  `id_ua` bigint DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FKqj7afiq92t360ad17fa2sp1en` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carreras_SEQ`
--

DROP TABLE IF EXISTS `carreras_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carreras_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `configuraciones`
--

DROP TABLE IF EXISTS `configuraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuraciones` (
  `id` bigint NOT NULL,
  `config` tinyint DEFAULT NULL,
  `isDate` bit(1) NOT NULL,
  `value` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `configuraciones_SEQ`
--

DROP TABLE IF EXISTS `configuraciones_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuraciones_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `integrantes`
--

DROP TABLE IF EXISTS `integrantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `integrantes` (
  `id` bigint NOT NULL,
  `cargo` tinyint DEFAULT NULL,
  `cargoOtro` varchar(255) DEFAULT NULL,
  `rol` tinyint DEFAULT NULL,
  `subunidad_academica` varchar(255) DEFAULT NULL,
  `unidad_academica` varchar(255) DEFAULT NULL,
  `id_docente` bigint DEFAULT NULL,
  `id_programa` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiide2kl0svo0m8upcpkadipeu` (`id_docente`),
  KEY `FKlhme1fgl5136kyw3hmk7jk9kl` (`id_programa`),
  CONSTRAINT `FKiide2kl0svo0m8upcpkadipeu` FOREIGN KEY (`id_docente`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKlhme1fgl5136kyw3hmk7jk9kl` FOREIGN KEY (`id_programa`) REFERENCES `programas` (`id`),
  CONSTRAINT `integrantes_chk_1` CHECK ((`cargo` between 0 and 6)),
  CONSTRAINT `integrantes_chk_2` CHECK ((`rol` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `integrantes_AUD`
--

DROP TABLE IF EXISTS `integrantes_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `integrantes_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `cargo` tinyint DEFAULT NULL,
  `cargoOtro` varchar(255) DEFAULT NULL,
  `rol` tinyint DEFAULT NULL,
  `subunidad_academica` varchar(255) DEFAULT NULL,
  `unidad_academica` varchar(255) DEFAULT NULL,
  `id_docente` bigint DEFAULT NULL,
  `id_programa` bigint DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FK3k949uwhn8d4aurfq6b1iokv8` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`),
  CONSTRAINT `integrantes_AUD_chk_1` CHECK ((`cargo` between 0 and 6)),
  CONSTRAINT `integrantes_AUD_chk_2` CHECK ((`rol` between 0 and 3))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `integrantes_SEQ`
--

DROP TABLE IF EXISTS `integrantes_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `integrantes_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marco_area`
--

DROP TABLE IF EXISTS `marco_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marco_area` (
  `marco_id` bigint NOT NULL,
  `area_id` bigint NOT NULL,
  PRIMARY KEY (`marco_id`,`area_id`),
  KEY `FK3jor1o9makghqj3qws39ndymu` (`area_id`),
  CONSTRAINT `FK3jor1o9makghqj3qws39ndymu` FOREIGN KEY (`area_id`) REFERENCES `areas` (`id`),
  CONSTRAINT `FKp4j2s0gcioykaos4wbhaswtp2` FOREIGN KEY (`marco_id`) REFERENCES `marcos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marco_area_AUD`
--

DROP TABLE IF EXISTS `marco_area_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marco_area_AUD` (
  `REV` int NOT NULL,
  `marco_id` bigint NOT NULL,
  `area_id` bigint NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  PRIMARY KEY (`marco_id`,`REV`,`area_id`),
  KEY `FK5cajr7ahh0a80hhl1uvpcr1d` (`REV`),
  CONSTRAINT `FK5cajr7ahh0a80hhl1uvpcr1d` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marco_carrera`
--

DROP TABLE IF EXISTS `marco_carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marco_carrera` (
  `marco_id` bigint NOT NULL,
  `carrera_id` bigint NOT NULL,
  PRIMARY KEY (`marco_id`,`carrera_id`),
  KEY `FKakbccajtcae2aesqs67s0ti0q` (`carrera_id`),
  CONSTRAINT `FK1gh7uiqsgw7qwe24qjlo00bt1` FOREIGN KEY (`marco_id`) REFERENCES `marcos` (`id`),
  CONSTRAINT `FKakbccajtcae2aesqs67s0ti0q` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marco_carrera_AUD`
--

DROP TABLE IF EXISTS `marco_carrera_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marco_carrera_AUD` (
  `REV` int NOT NULL,
  `marco_id` bigint NOT NULL,
  `carrera_id` bigint NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  PRIMARY KEY (`marco_id`,`REV`,`carrera_id`),
  KEY `FKcyr3qh13gw3q77b7l81i0mh9w` (`REV`),
  CONSTRAINT `FKcyr3qh13gw3q77b7l81i0mh9w` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marco_ua`
--

DROP TABLE IF EXISTS `marco_ua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marco_ua` (
  `marco_id` bigint NOT NULL,
  `ua_id` bigint NOT NULL,
  PRIMARY KEY (`marco_id`,`ua_id`),
  KEY `FK3vl6uphkshehwxwc9aqvuwb01` (`ua_id`),
  CONSTRAINT `FK3vl6uphkshehwxwc9aqvuwb01` FOREIGN KEY (`ua_id`) REFERENCES `unidades_academicas` (`id`),
  CONSTRAINT `FK9fed85ypxnlkuijse6eqxneb2` FOREIGN KEY (`marco_id`) REFERENCES `marcos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marco_ua_AUD`
--

DROP TABLE IF EXISTS `marco_ua_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marco_ua_AUD` (
  `REV` int NOT NULL,
  `marco_id` bigint NOT NULL,
  `ua_id` bigint NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  PRIMARY KEY (`marco_id`,`REV`,`ua_id`),
  KEY `FK464bel4e0kr2s0ae65t9qgryn` (`REV`),
  CONSTRAINT `FK464bel4e0kr2s0ae65t9qgryn` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marcos`
--

DROP TABLE IF EXISTS `marcos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcos` (
  `id` bigint NOT NULL,
  `id_programa` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfyvd6oot67g7jmsnirhiq5lvt` (`id_programa`),
  CONSTRAINT `FKiwwpfk9co3p2cin2bkhhuh5k` FOREIGN KEY (`id_programa`) REFERENCES `programas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marcos_AUD`
--

DROP TABLE IF EXISTS `marcos_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcos_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `id_programa` bigint DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FK1dqumfrkhvdmruhxkjksjs4jv` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marcos_SEQ`
--

DROP TABLE IF EXISTS `marcos_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcos_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `programas`
--

DROP TABLE IF EXISTS `programas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programas` (
  `id` bigint NOT NULL,
  `adecuaciones` bit(1) NOT NULL,
  `contenidos` text,
  `creditos` int DEFAULT NULL,
  `descrEvaluacion` text,
  `descrMetodologia` text,
  `descripcionAdecuaciones` varchar(255) DEFAULT NULL,
  `duracion` tinyint DEFAULT NULL,
  `duracionOtro` varchar(255) DEFAULT NULL,
  `estado` tinyint DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `formato` tinyint DEFAULT NULL,
  `horasAula` int DEFAULT NULL,
  `horasLecturas` int DEFAULT NULL,
  `horasOtrosConAcompa` int DEFAULT NULL,
  `horasOtrosSinAcompa` int DEFAULT NULL,
  `horasTareas` int DEFAULT NULL,
  `horasTotales` int DEFAULT NULL,
  `horasTrabajosConAcompa` int DEFAULT NULL,
  `horasTrabajosFinales` int DEFAULT NULL,
  `horasTrabajosSinAcompa` int DEFAULT NULL,
  `ingreso` bit(1) NOT NULL,
  `modalidad` tinyint DEFAULT NULL,
  `modoAprobacion` tinyint DEFAULT NULL,
  `objetivos` text,
  `otrasAclaracionesCarrera` varchar(255) DEFAULT NULL,
  `otrosConAcompa` varchar(255) DEFAULT NULL,
  `otrosServicios` bit(1) NOT NULL,
  `otrosSinAcompa` varchar(255) DEFAULT NULL,
  `recomNoCorresponde` bit(1) NOT NULL,
  `recomendaciones` varchar(255) DEFAULT NULL,
  `regimen` tinyint DEFAULT NULL,
  `requisitos` bit(1) NOT NULL,
  `requisitosCuales` varchar(255) DEFAULT NULL,
  `semestre` tinyint DEFAULT NULL,
  `tareas75obligatoria` bit(1) NOT NULL,
  `year` int DEFAULT NULL,
  `id_uc` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKem803mmak9qw0tq83gq1q652e` (`id_uc`),
  KEY `FK91aqxc34ses1fgw516s0em8je` (`id_usuario`),
  CONSTRAINT `FK91aqxc34ses1fgw516s0em8je` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKem803mmak9qw0tq83gq1q652e` FOREIGN KEY (`id_uc`) REFERENCES `unidades_curriculares` (`id`),
  CONSTRAINT `programas_chk_1` CHECK ((`duracion` between 0 and 2)),
  CONSTRAINT `programas_chk_2` CHECK ((`estado` between 0 and 8)),
  CONSTRAINT `programas_chk_3` CHECK ((`formato` between 0 and 2)),
  CONSTRAINT `programas_chk_4` CHECK ((`modalidad` between 0 and 3)),
  CONSTRAINT `programas_chk_5` CHECK ((`modoAprobacion` between 0 and 2)),
  CONSTRAINT `programas_chk_6` CHECK ((`regimen` between 0 and 2)),
  CONSTRAINT `programas_chk_7` CHECK ((`semestre` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `programas_AUD`
--

DROP TABLE IF EXISTS `programas_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programas_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `adecuaciones` bit(1) DEFAULT NULL,
  `contenidos` text,
  `creditos` int DEFAULT NULL,
  `descrEvaluacion` text,
  `descrMetodologia` text,
  `descripcionAdecuaciones` varchar(255) DEFAULT NULL,
  `duracion` tinyint DEFAULT NULL,
  `duracionOtro` varchar(255) DEFAULT NULL,
  `estado` tinyint DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `formato` tinyint DEFAULT NULL,
  `horasAula` int DEFAULT NULL,
  `horasLecturas` int DEFAULT NULL,
  `horasOtrosConAcompa` int DEFAULT NULL,
  `horasOtrosSinAcompa` int DEFAULT NULL,
  `horasTareas` int DEFAULT NULL,
  `horasTotales` int DEFAULT NULL,
  `horasTrabajosConAcompa` int DEFAULT NULL,
  `horasTrabajosFinales` int DEFAULT NULL,
  `horasTrabajosSinAcompa` int DEFAULT NULL,
  `ingreso` bit(1) DEFAULT NULL,
  `modalidad` tinyint DEFAULT NULL,
  `modoAprobacion` tinyint DEFAULT NULL,
  `objetivos` text,
  `otrasAclaracionesCarrera` varchar(255) DEFAULT NULL,
  `otrosConAcompa` varchar(255) DEFAULT NULL,
  `otrosServicios` bit(1) DEFAULT NULL,
  `otrosSinAcompa` varchar(255) DEFAULT NULL,
  `recomNoCorresponde` bit(1) DEFAULT NULL,
  `recomendaciones` varchar(255) DEFAULT NULL,
  `regimen` tinyint DEFAULT NULL,
  `requisitos` bit(1) DEFAULT NULL,
  `requisitosCuales` varchar(255) DEFAULT NULL,
  `semestre` tinyint DEFAULT NULL,
  `tareas75obligatoria` bit(1) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `id_uc` bigint DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FK8i32ejd3ixoqjkuulii0ii0j6` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`),
  CONSTRAINT `programas_AUD_chk_1` CHECK ((`duracion` between 0 and 2)),
  CONSTRAINT `programas_AUD_chk_2` CHECK ((`estado` between 0 and 8)),
  CONSTRAINT `programas_AUD_chk_3` CHECK ((`formato` between 0 and 2)),
  CONSTRAINT `programas_AUD_chk_4` CHECK ((`modalidad` between 0 and 3)),
  CONSTRAINT `programas_AUD_chk_5` CHECK ((`modoAprobacion` between 0 and 2)),
  CONSTRAINT `programas_AUD_chk_6` CHECK ((`regimen` between 0 and 2)),
  CONSTRAINT `programas_AUD_chk_7` CHECK ((`semestre` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `programas_SEQ`
--

DROP TABLE IF EXISTS `programas_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programas_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `revisiones`
--

DROP TABLE IF EXISTS `revisiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revisiones` (
  `id` bigint NOT NULL,
  `estado` tinyint DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `adecDescrSug` varchar(255) DEFAULT NULL,
  `adecSug` varchar(255) DEFAULT NULL,
  `aprobDirectaSug` varchar(255) DEFAULT NULL,
  `areaSug` varchar(255) DEFAULT NULL,
  `biblioSug` varchar(255) DEFAULT NULL,
  `carreraSug` varchar(255) DEFAULT NULL,
  `contSug` varchar(255) DEFAULT NULL,
  `creditosSug` varchar(255) DEFAULT NULL,
  `docentesSug` varchar(255) DEFAULT NULL,
  `duracionSug` varchar(255) DEFAULT NULL,
  `evaluacionSug` varchar(255) DEFAULT NULL,
  `formatoSug` varchar(255) DEFAULT NULL,
  `ingresoSug` varchar(255) DEFAULT NULL,
  `metodologiaSug` varchar(255) DEFAULT NULL,
  `modalidadSug` varchar(255) DEFAULT NULL,
  `objSug` varchar(255) DEFAULT NULL,
  `otrosServSug` varchar(255) DEFAULT NULL,
  `recomSug` varchar(255) DEFAULT NULL,
  `regimenSug` varchar(255) DEFAULT NULL,
  `requisitosCualesSug` varchar(255) DEFAULT NULL,
  `requisitosSug` varchar(255) DEFAULT NULL,
  `semestreSug` varchar(255) DEFAULT NULL,
  `tareas75obligSug` varchar(255) DEFAULT NULL,
  `uaSug` varchar(255) DEFAULT NULL,
  `ucSug` varchar(255) DEFAULT NULL,
  `id_programa` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3odp6mg4c8xp3apwuvsbd4a66` (`id_programa`),
  KEY `FKrsj3htn4pxk7uoi3gi8fecgnn` (`id_usuario`),
  CONSTRAINT `FK3odp6mg4c8xp3apwuvsbd4a66` FOREIGN KEY (`id_programa`) REFERENCES `programas` (`id`),
  CONSTRAINT `FKrsj3htn4pxk7uoi3gi8fecgnn` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `revisiones_chk_1` CHECK ((`estado` between 0 and 8))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `revisiones_SEQ`
--

DROP TABLE IF EXISTS `revisiones_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revisiones_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `operaciones` varbinary(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles_AUD`
--

DROP TABLE IF EXISTS `roles_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `operaciones` varbinary(255) DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FK3udarg0q4wa62rnnfcf6b46a2` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles_SEQ`
--

DROP TABLE IF EXISTS `roles_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unidades_academicas`
--

DROP TABLE IF EXISTS `unidades_academicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidades_academicas` (
  `id` bigint NOT NULL,
  `habilitada` bit(1) NOT NULL,
  `nombreUA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unidades_academicas_AUD`
--

DROP TABLE IF EXISTS `unidades_academicas_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidades_academicas_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `habilitada` bit(1) DEFAULT NULL,
  `nombreUA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FKakp9nq2o9t5wy2j95qcs3q6v1` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unidades_academicas_SEQ`
--

DROP TABLE IF EXISTS `unidades_academicas_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidades_academicas_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unidades_curriculares`
--

DROP TABLE IF EXISTS `unidades_curriculares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidades_curriculares` (
  `id` bigint NOT NULL,
  `nombreUC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unidades_curriculares_AUD`
--

DROP TABLE IF EXISTS `unidades_curriculares_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidades_curriculares_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `nombreUC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FK7ulfolleryiaig1x6ml8wv0et` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unidades_curriculares_SEQ`
--

DROP TABLE IF EXISTS `unidades_curriculares_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidades_curriculares_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL,
  `creationDate` datetime(6) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `tipoAdscripcion` tinyint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `id_rol` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3kl77pehgupicftwfreqnjkll` (`id_rol`),
  CONSTRAINT `FK3kl77pehgupicftwfreqnjkll` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`),
  CONSTRAINT `usuarios_chk_1` CHECK ((`tipoAdscripcion` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios_AUD`
--

DROP TABLE IF EXISTS `usuarios_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_AUD` (
  `id` bigint NOT NULL,
  `REV` int NOT NULL,
  `REVTYPE` tinyint DEFAULT NULL,
  `creationDate` datetime(6) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `tipoAdscripcion` tinyint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `id_rol` bigint DEFAULT NULL,
  PRIMARY KEY (`REV`,`id`),
  CONSTRAINT `FK1oawa29hei7b89ugobb3m3adx` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`),
  CONSTRAINT `usuarios_AUD_chk_1` CHECK ((`tipoAdscripcion` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios_SEQ`
--

DROP TABLE IF EXISTS `usuarios_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_SEQ` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-14 13:52:19

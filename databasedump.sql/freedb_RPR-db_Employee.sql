-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPR-db
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `First_name` varchar(45) NOT NULL,
  `Last_name` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Hire_date` date NOT NULL,
  `department_id` int DEFAULT NULL,
  `Education` varchar(45) NOT NULL,
  `project_id` int DEFAULT NULL,
  `payoff` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_project_id_idx` (`project_id`),
  KEY `fk_department_id_idx` (`department_id`),
  CONSTRAINT `fk_department_id` FOREIGN KEY (`department_id`) REFERENCES `Departments` (`id`),
  CONSTRAINT `fk_project_id` FOREIGN KEY (`project_id`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (2,'Zack','Kenns','street200.B','2001-02-20',20,'Master',200,1764),(3,'Emily','Mick','street333.A','2008-10-20',40,'Bachelor',202,924),(4,'Miley ','Sarah','street454.C','2020-09-20',10,'Bachelor',201,1260),(5,'Deen','Berg','steet222.B','2011-03-20',50,'Bachelor',202,1617),(6,'Vanna','Welruck','street102.A','2005-05-20',40,'Master',200,1848),(7,'Diana','Bell','street303.A','2007-08-20',20,'Bachelor',201,1764),(8,'Billie','Lars','street222.C','2004-11-20',30,'Master',201,2520),(9,'Nick','Maddy','street100.B','2022-02-20',50,'Bachelor',200,1155),(10,'Hannah','Trace','street252.A','2015-06-20',40,'Master',201,1155),(11,'Mark','Twen','street112.C','2022-12-18',20,'Bachelor',202,1035),(12,'Zanna','Naomi','street111.C','2022-12-27',20,'Bachelor',200,900),(14,'Ana','Loren','street333.B','2023-01-05',10,'Bachelor',202,1000),(15,'lda','Chris','street222.B','2023-01-05',10,'Master',200,1000),(16,'Myla','Kay','street222.A','2020-01-15',10,'Master',200,1200),(17,'Tom','Cruz','street221.B','2020-01-09',10,'Bachelor',202,1350);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-06 22:52:20

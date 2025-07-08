-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: company
-- ------------------------------------------------------
-- Server version	8.0.37

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

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `DNAME` char(15) NOT NULL,
  `DNUMBER` int NOT NULL,
  `MGR_SSN` int DEFAULT NULL,
  `MGR_START_DATE` date DEFAULT NULL,
  PRIMARY KEY (`DNUMBER`),
  KEY `MGR_SSN` (`MGR_SSN`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`MGR_SSN`) REFERENCES `employee` (`SSN`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('HEADQUARTERS',1,888665555,'1981-06-19'),('ADMINISTRATION',4,987554321,'1995-01-01'),('RESEARCH',5,333445555,'1988-05-22');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dependent`
--

DROP TABLE IF EXISTS `dependent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dependent` (
  `ESSN` int NOT NULL,
  `DEPENDENT_NAME` char(15) NOT NULL,
  `GENDER` char(1) NOT NULL,
  `BDATE` date NOT NULL,
  `RELATIONSHIP` char(15) NOT NULL,
  PRIMARY KEY (`ESSN`,`DEPENDENT_NAME`),
  CONSTRAINT `dependent_ibfk_1` FOREIGN KEY (`ESSN`) REFERENCES `employee` (`SSN`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dependent`
--

LOCK TABLES `dependent` WRITE;
/*!40000 ALTER TABLE `dependent` DISABLE KEYS */;
INSERT INTO `dependent` VALUES (123456789,'ALICE','F','1983-12-30','DAUGHTER'),(123456789,'ELIZABETH','F','1967-05-05','SPOUSE'),(123456789,'MICHAEL','M','1988-01-04','SON'),(333445555,'ALICE','F','1986-04-05','DAUGHTER'),(333445555,'JOY','F','1958-05-03','SPOUSE'),(333445555,'THEODORE','M','1983-10-25','SON'),(987554321,'ABNAR','M','1942-02-28','SPOUSE');
/*!40000 ALTER TABLE `dependent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept_location`
--

DROP TABLE IF EXISTS `dept_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept_location` (
  `DNUMBER` int NOT NULL,
  `DLOCATION` char(20) NOT NULL,
  PRIMARY KEY (`DNUMBER`,`DLOCATION`),
  CONSTRAINT `dept_location_ibfk_1` FOREIGN KEY (`DNUMBER`) REFERENCES `department` (`DNUMBER`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept_location`
--

LOCK TABLES `dept_location` WRITE;
/*!40000 ALTER TABLE `dept_location` DISABLE KEYS */;
INSERT INTO `dept_location` VALUES (1,'HOUSTON'),(4,'STAFFORD'),(5,'BELLAIRE'),(5,'HOUSTON'),(5,'SUGARLAND');
/*!40000 ALTER TABLE `dept_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `FNAME` char(10) NOT NULL,
  `MINIT` char(1) DEFAULT NULL,
  `LNAME` char(10) NOT NULL,
  `SSN` int NOT NULL,
  `BDATE` date NOT NULL,
  `ADDRESS` char(30) NOT NULL,
  `GENDER` char(1) NOT NULL,
  `SALARY` decimal(5,0) NOT NULL,
  `SUPER_SSN` int DEFAULT NULL,
  `DNO` int DEFAULT NULL,
  PRIMARY KEY (`SSN`),
  KEY `SUPER_SSN` (`SUPER_SSN`),
  KEY `DNO` (`DNO`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`SUPER_SSN`) REFERENCES `employee` (`SSN`) ON DELETE CASCADE,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`DNO`) REFERENCES `department` (`DNUMBER`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('JOHN','B','SMITH',123456789,'1965-01-09','731 FONDREN,HOUSTON ,TX','M',30000,333445555,5),('FRANKLIN','T','WONG',333445555,'1955-12-08','638 VOSS,HOUSTON,TX','M',40000,888665555,5),('JOYCE','A','ENGLISH',453453453,'1972-07-31','5631 RICE,HOUSTON,TX','F',25000,333445555,5),('RAMESH','K','NARAYAN',666884444,'1962-09-15','975 FIRE OAK,HUMBLE,TX','M',38000,333445555,5),('JAMES','E','BORG',888665555,'1937-11-10','450 STONE,HOUSTON,TX','M',55000,NULL,1),('JENNFER','S','WAILACE',987554321,'1941-06-20','291 BERRRY,BELLAIRE,TX','F',43000,888665555,4),('AHMAD','V','JEBBAR',987987987,'1969-03-29','980 DAILAS,HOUSTON,TX','M',25000,987554321,4),('ALICE','J','ZELAYA',999387777,'1968-01-19','3321 CASTLE,SPRING,TX','F',25000,987554321,4);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `PNAME` char(15) NOT NULL,
  `PNUMBER` int NOT NULL,
  `PLOCATION` char(15) NOT NULL,
  `DNUM` int NOT NULL,
  PRIMARY KEY (`PNUMBER`),
  KEY `DNUM` (`DNUM`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`DNUM`) REFERENCES `department` (`DNUMBER`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('PRODUCTX',1,'BELLAIRE',5),('PRODUCTY',2,'SUGARLAND',5),('PRODUCTZ',3,'HOUSTON',5),('COMPUTERIZATION',10,'STAFFORD',4),('REORGANIZATION',20,'HOUSTON',1),('NEWBENEFITS',30,'STAFFORD',4);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `works_on`
--

DROP TABLE IF EXISTS `works_on`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `works_on` (
  `IDE` int NOT NULL,
  `PNO` int NOT NULL,
  `HOURS` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`IDE`,`PNO`),
  KEY `PNO` (`PNO`),
  CONSTRAINT `works_on_ibfk_1` FOREIGN KEY (`PNO`) REFERENCES `project` (`PNUMBER`) ON DELETE CASCADE,
  CONSTRAINT `works_on_ibfk_2` FOREIGN KEY (`IDE`) REFERENCES `employee` (`SSN`) ON DELETE CASCADE,
  CONSTRAINT `works_on_ibfk_3` FOREIGN KEY (`PNO`) REFERENCES `project` (`PNUMBER`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `works_on`
--

LOCK TABLES `works_on` WRITE;
/*!40000 ALTER TABLE `works_on` DISABLE KEYS */;
INSERT INTO `works_on` VALUES (123456789,1,33),(123456789,2,8),(333445555,2,10),(333445555,3,10),(333445555,10,10),(333445555,20,10),(453453453,1,20),(453453453,2,20),(666884444,3,40),(888665555,20,NULL),(987554321,20,15),(987554321,30,20),(987987987,10,35),(987987987,30,5),(999887777,10,10),(999887777,30,30);
/*!40000 ALTER TABLE `works_on` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-13  0:05:07

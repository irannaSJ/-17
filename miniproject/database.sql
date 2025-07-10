CREATE DATABASE  IF NOT EXISTS `hybridcloud` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hybridcloud`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: hybridcloud
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `filesofprivatecloud`
--

DROP TABLE IF EXISTS `filesofprivatecloud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filesofprivatecloud` (
  `Fid` int NOT NULL AUTO_INCREMENT,
  `FileName` varchar(255) NOT NULL,
  `FilePath` varchar(255) NOT NULL,
  `FileHash` varchar(255) NOT NULL,
  `Uid` int NOT NULL,
  PRIMARY KEY (`Fid`),
  UNIQUE KEY `FileHash` (`FileHash`),
  KEY `Uid` (`Uid`),
  CONSTRAINT `filesofprivatecloud_ibfk_1` FOREIGN KEY (`Uid`) REFERENCES `privatecloudregisteration` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filesofprivatecloud`
--

LOCK TABLES `filesofprivatecloud` WRITE;
/*!40000 ALTER TABLE `filesofprivatecloud` DISABLE KEYS */;

/*!40000 ALTER TABLE `filesofprivatecloud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filesofpubliccloud`
--

DROP TABLE IF EXISTS `filesofpubliccloud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filesofpubliccloud` (
  `Fid` int NOT NULL AUTO_INCREMENT,
  `FileName` varchar(255) NOT NULL,
  `FilePath` varchar(255) NOT NULL,
  `FileHash` varchar(64) NOT NULL,
  `Uid` int NOT NULL,
  PRIMARY KEY (`Fid`),
  UNIQUE KEY `filehash_constraint` (`FileHash`),
  KEY `Uid` (`Uid`),
  CONSTRAINT `filesofpubliccloud_ibfk_1` FOREIGN KEY (`Uid`) REFERENCES `publiccloudregisteration` (`UserId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filesofpubliccloud`
--

LOCK TABLES `filesofpubliccloud` WRITE;
/*!40000 ALTER TABLE `filesofpubliccloud` DISABLE KEYS */;
/*!40000 ALTER TABLE `filesofpubliccloud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privatecloudregisteration`
--

DROP TABLE IF EXISTS `privatecloudregisteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `privatecloudregisteration` (
  `UserId` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) NOT NULL,
  `Age` int NOT NULL,
  `Gender` char(6) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `encryptedPass` varchar(255) NOT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UserName` (`UserName`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `encryptedPass` (`encryptedPass`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privatecloudregisteration`
--

LOCK TABLES `privatecloudregisteration` WRITE;
/*!40000 ALTER TABLE `privatecloudregisteration` DISABLE KEYS */;
/*!40000 ALTER TABLE `privatecloudregisteration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publiccloudregisteration`
--

DROP TABLE IF EXISTS `publiccloudregisteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publiccloudregisteration` (
  `UserId` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) NOT NULL,
  `Age` int NOT NULL,
  `Gender` char(6) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UserName` (`UserName`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publiccloudregisteration`
--

LOCK TABLES `publiccloudregisteration` WRITE;
/*!40000 ALTER TABLE `publiccloudregisteration` DISABLE KEYS */;
/*!40000 ALTER TABLE `publiccloudregisteration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-08 15:28:49

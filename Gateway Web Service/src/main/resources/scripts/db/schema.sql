-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: scigw
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `job_details`
--

DROP TABLE IF EXISTS `job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_details` (
  `UUID` varchar(255) DEFAULT NULL,
  `JOBID` varchar(255) DEFAULT NULL,
  `WALLTIME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `job_status` varchar(45) DEFAULT NULL,
  `submit_time` bigint(15) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `jobname` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_details`
--

LOCK TABLES `job_details` WRITE;
/*!40000 ALTER TABLE `job_details` DISABLE KEYS */;
INSERT INTO `job_details` VALUES ('bc15534c-031c-43ae-ba7e-293904fb6a60','1282365.m2','00:00:10','sagabhan@indiana.edu','C',1457605180801,'test','sagar'),('b70b693f-9f69-4c4f-94ee-e02d630a0e0c','1282390.m2','00:00:10','sagabhan@indiana.edu','Q',1457614367798,'test','sagar'),('f42e60dc-99e0-4c80-93d3-4ec6e5facfca','1282391.m2','00:00:10','sagabhan@indiana.edu','Q',1457614411215,'test','sagar1'),('d322e67a-6681-47a5-9a55-a9584a5ba6a4','1282392.m2','00:00:10','sagabhan@indiana.edu','C',1457614535774,'test','sagar12'),('4ebf6487-1ac8-406d-980a-ec10f23ff633','1282405.m2','00:00:10','sagabhan@indiana.edu','C',1457617017182,'test','test'),('3026102b-fd7c-46b0-a032-5df252a28258','1282407.m2','00:00:10','sagabhan@indiana.edu','Q',1457617650966,'test','ashu');
/*!40000 ALTER TABLE `job_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-10 20:43:29

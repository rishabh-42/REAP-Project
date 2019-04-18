-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: reap
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

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
-- Table structure for table `user_star_received`
--

DROP TABLE IF EXISTS `user_star_received`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_star_received` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bronze_star_recieved` int(11) DEFAULT NULL,
  `gold_star_recieved` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `silver_star_recieved` int(11) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn70dntj4ayc5t8gtltexd6yw9` (`user_user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_star_received`
--

LOCK TABLES `user_star_received` WRITE;
/*!40000 ALTER TABLE `user_star_received` DISABLE KEYS */;
INSERT INTO `user_star_received` VALUES (1,1,0,7986,0,1),(2,2,2,49,1,2),(3,1,3,120,1,3),(4,2,1,79,1,4),(5,0,1,3020,1,5),(6,0,0,1,0,6),(7,2,0,20,0,7),(8,0,0,0,0,8),(9,0,1,50,1,9),(10,1,0,10,0,10),(11,0,3,24,0,11),(12,0,1,30,0,12),(13,0,1,30,0,13),(14,0,0,21,0,15),(15,0,0,11,0,14),(16,0,0,0,0,16);
/*!40000 ALTER TABLE `user_star_received` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-18 10:15:44

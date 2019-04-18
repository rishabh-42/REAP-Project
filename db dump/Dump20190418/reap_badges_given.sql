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
-- Table structure for table `badges_given`
--

DROP TABLE IF EXISTS `badges_given`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `badges_given` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_date_time` datetime DEFAULT NULL,
  `flag` bit(1) NOT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `giver_user_id` int(11) DEFAULT NULL,
  `receiver_user_id` int(11) DEFAULT NULL,
  `star_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm98o11s45bk89okwsena9how0` (`giver_user_id`),
  KEY `FKrltrvjpgs5lvsygsw8w65r6h4` (`receiver_user_id`),
  KEY `FK7pai231f3hakfwr3spjv67aj6` (`star_id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badges_given`
--

LOCK TABLES `badges_given` WRITE;
/*!40000 ALTER TABLE `badges_given` DISABLE KEYS */;
INSERT INTO `badges_given` VALUES (1,_binary '\0','heyy','2019-04-12 14:58:04',_binary '','2019-04-12 14:59:11',2,1,3),(2,_binary '','heyy','2019-04-12 15:02:32',_binary '','2019-04-12 15:02:32',1,2,1),(3,_binary '','Hey ... Whats up','2019-04-13 09:39:51',_binary '','2019-04-13 09:39:51',1,2,1),(4,_binary '','okkk','2019-04-13 09:40:19',_binary '','2019-04-13 09:40:19',1,2,2),(5,_binary '','ok','2019-04-13 09:41:18',_binary '','2019-04-13 09:41:18',1,2,3),(6,_binary '','hola','2019-04-13 09:41:32',_binary '','2019-04-13 09:41:32',1,2,3),(7,_binary '\0','Super Saiyannn','2019-04-13 16:07:23',_binary '','2019-04-14 12:33:01',4,3,3),(8,_binary '','gjh','2019-04-13 16:22:19',_binary '','2019-04-13 16:22:19',1,3,2),(9,_binary '','Ok ','2019-04-13 16:25:15',_binary '','2019-04-13 16:25:15',1,4,1),(10,_binary '','text','2019-04-13 16:27:33',_binary '','2019-04-13 16:27:33',2,1,3),(11,_binary '\0','New Features added !!','2019-04-13 21:53:01',_binary '','2019-04-13 21:53:25',1,4,3),(12,_binary '','New Feature  added !!','2019-04-13 21:53:56',_binary '','2019-04-13 21:53:56',1,4,3),(13,_binary '','Bhaiyo aur beheno...','2019-04-13 22:19:55',_binary '','2019-04-13 22:19:55',5,4,2),(14,_binary '','Hello Modi ji..','2019-04-13 22:25:53',_binary '','2019-04-13 22:25:53',6,5,1),(15,_binary '\0','Thanks for adding me..','2019-04-13 22:31:42',_binary '','2019-04-14 12:09:07',7,1,3),(16,_binary '\0','lorem ispum....','2019-04-13 22:52:38',_binary '','2019-04-14 08:21:59',8,2,3),(17,_binary '\0','Thanks for adding me..','2019-04-14 12:35:44',_binary '','2019-04-14 12:45:09',9,2,3),(18,_binary '','Hey rahul...','2019-04-14 12:47:54',_binary '','2019-04-14 12:47:54',10,7,3),(19,_binary '','Hii','2019-04-14 12:48:14',_binary '','2019-04-14 12:48:14',10,5,2),(20,_binary '','Holaaaaa.....','2019-04-14 12:48:30',_binary '','2019-04-14 12:48:30',10,9,1),(21,_binary '\0','Kame hame haa..........','2019-04-14 12:50:17',_binary '','2019-04-14 12:57:53',11,4,1),(22,_binary '\0','Welcoe Rahul','2019-04-14 13:03:40',_binary '','2019-04-14 13:03:50',1,7,1),(23,_binary '','Welcome rahul...','2019-04-14 13:04:09',_binary '','2019-04-14 13:04:09',1,7,3),(24,_binary '','Getting bored...','2019-04-14 16:45:13',_binary '','2019-04-14 16:45:13',1,9,2),(25,_binary '\0','Good morning','2019-04-15 09:41:05',_binary '','2019-04-15 09:41:33',1,10,3),(26,_binary '','Good morning...assessment today ..','2019-04-15 09:41:56',_binary '','2019-04-15 09:41:56',1,10,3),(27,_binary '\0','Thanks for adding me..','2019-04-15 10:17:19',_binary '','2019-04-15 10:18:12',12,9,3),(28,_binary '','GOT ','2019-04-15 10:18:34',_binary '','2019-04-15 10:18:34',1,12,1),(29,_binary '','test','2019-04-15 11:53:01',_binary '','2019-04-15 11:53:01',1,3,1),(30,_binary '','test','2019-04-15 11:53:46',_binary '','2019-04-15 11:53:46',1,3,1),(31,_binary '','test','2019-04-15 11:53:51',_binary '','2019-04-15 11:53:51',1,3,1),(32,_binary '','heyy','2019-04-15 11:54:24',_binary '','2019-04-15 11:54:24',1,3,3),(33,_binary '','test user','2019-04-15 12:41:56',_binary '','2019-04-15 12:41:56',13,4,3),(34,_binary '\0','Again test','2019-04-15 12:42:11',_binary '','2019-04-15 12:43:20',13,10,1),(35,_binary '','Giving You a gold..','2019-04-15 12:42:40',_binary '','2019-04-15 12:42:40',1,13,1),(36,_binary '','testing gold badge','2019-04-15 15:54:55',_binary '','2019-04-15 15:54:55',15,11,1),(37,_binary '','testig','2019-04-15 15:56:55',_binary '','2019-04-15 15:56:55',15,11,1),(38,_binary '\0','testing','2019-04-15 15:57:13',_binary '','2019-04-15 16:08:33',15,11,1),(39,_binary '','tesfdfhjsg','2019-04-15 15:57:51',_binary '','2019-04-15 15:57:51',1,11,1);
/*!40000 ALTER TABLE `badges_given` ENABLE KEYS */;
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

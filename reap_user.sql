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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `create_date_time` datetime DEFAULT NULL,
  `current_role_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `point_spent` int(11) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `user_star_count_id` int(11) DEFAULT NULL,
  `user_star_received_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK938txvbb8mrevyu337bu7vio0` (`user_star_count_id`),
  KEY `FKoyden9o5invqj9ej2m1kwfp15` (`user_star_received_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '','','2019-04-12 13:08:38',NULL,'rishabh.rajput@tothenew.com','Rishabh','Admin','$2a$04$0z5zLR/Ws6CfP34leB8TAuXXRXOmUJlp9kw.ENHVzBGJHjdnuNqDm','/assets/profileImages/1.png',0,NULL,'2019-04-14 08:23:39',1,1),(2,_binary '','','2019-04-12 14:56:55',NULL,'lootzone42@gmail.com','Kratos','Prime','$2a$04$ZhbRM8vC7Aao4/AQKpqEfud7pXEc/LzSco9ab8SWycRXLM9oM.Ljm','/assets/profileImages/2.png',0,NULL,'2019-04-18 10:12:40',2,2),(3,_binary '','','2019-04-13 15:49:55',NULL,'rishabh.15bit1086@abes.ac.in','Peter','Parker','$2a$04$jl6Hy.iluEvQCy./Ix9smOk1iOCwFeCKKkuiwbv3mptaMEt9sQ.Me','/assets/profileImages/default.png',0,NULL,'2019-04-13 15:50:22',3,3),(4,_binary '','','2019-04-13 15:53:07',NULL,'rish0bh@gmail.com','Bruce','Wayne','$2a$04$wlcZgGubij6ZbKEg87wXvOOOPyuKSJ/7rBCTxaePivXAVIFsJLklq','/assets/profileImages/4.png',0,NULL,'2019-04-13 22:44:23',4,4),(5,_binary '','','2019-04-13 22:17:38',NULL,'abc@abc.com','Nrapendra','Kumar','$2a$04$68acL5aBmZL.rrvSm3N9jOYkGP0p0QYUwpCMJEVyS2lRgUaG9Ej1e','/assets/profileImages/default.png',0,NULL,'2019-04-14 08:14:11',5,5),(6,_binary '','','2019-04-13 22:24:30',NULL,'donald@trump.com','Donald','Trump','$2a$04$yENq001Fbt/V9fVonBe8U.HTHV/.VTM8M2eLVPkU8FPPT0DPgvxdy','/assets/profileImages/6.png',0,NULL,'2019-04-14 08:13:07',6,6),(7,_binary '','','2019-04-13 22:27:46',NULL,'rahul@rahul.com','Rahul ','Kumar','$2a$04$lJcH.GJKH0mFzvTbUtzrDOroCd7EBAdqiPoUHozv7X17MfZzXA5nS','/assets/profileImages/default.png',0,NULL,'2019-04-13 22:30:42',7,7),(8,_binary '','','2019-04-13 22:49:26',NULL,'monu.15bit1141@abes.ac.in','Monu','Kumar','$2a$04$GFm2X57.eVtU/QNirrKeMubv8wMxKTw7TxBGJ8e1IZ9t/hUBT1Uoy','/assets/profileImages/8.png',0,NULL,'2019-04-15 15:42:33',8,8),(9,_binary '','','2019-04-14 12:34:50',NULL,'rishabhr264@gmail.com','Reshu','Rajput','$2a$04$ZB2xS8IQUvvKBEWw.L5T..bRzQ6SKQkY7PwgofupSu6EmdawF65IC','/assets/profileImages/default.png',0,'92ca3848-edd7-4c56-b8f6-fa65edb4f427','2019-04-15 12:38:11',9,9),(10,_binary '','','2019-04-14 12:46:46',NULL,'rishabh989765@gmail.com','Shivam','Kumar','$2a$04$pqEJEIYlDYpqx7O18zAjveBs7FN.dL0L1hMmxHXSTdZmjeHWU.c.C','/assets/profileImages/default.png',0,NULL,'2019-04-14 12:47:29',10,10),(11,_binary '','','2019-04-14 12:49:18',NULL,'aditya@aditya.com','Aditya','Desai','$2a$04$XXO54Xu.lBiUp2XARW1hNubUkps0FlNAbQLiHlObCfuRLCkAPIHm2','/assets/profileImages/11.png',0,NULL,'2019-04-15 16:25:09',11,11),(12,_binary '','','2019-04-15 10:14:52',NULL,'gagan.kushwaha@tothenew.com','Gagan','Kushwah','$2a$04$pc6TtLU6bfgj3r37R4nxUe8YY/Qu8FqFp4cDhW/WUo4o4ogDHUVSK','/assets/profileImages/12.png',0,NULL,'2019-04-15 10:16:49',12,12),(13,_binary '','','2019-04-15 12:41:01',NULL,'prateek.nagar@tothenew.com','Prateek','Nagar','$2a$04$qryC.DtAFnTbXYuSiJF1S.joR9Lhteio9Kr42WCR8xNXWhgT2uzWm','/assets/profileImages/default.png',0,NULL,'2019-04-15 12:41:24',13,13),(14,_binary '','','2019-04-15 15:31:13',NULL,'546@khjvj','123456','6585^&*(%*%*%(','$2a$04$8loMl9D4s8tsV/PFrQ8mDu7yqvWsX2.MXRfEdsC80f4x5DmIs8pVy','/assets/profileImages/default.png',0,NULL,'2019-04-15 15:38:19',15,15),(15,_binary '','','2019-04-15 15:33:25',NULL,'dhanendra.kumar@tothenew.com','montu','kumar','$2a$04$kXgljTgcq1L19p5fsGBjzOvG7OD2Hdwb5rdKO/9uoJb1zC2lGepL2','/assets/profileImages/default.png',0,NULL,'2019-04-15 15:50:22',14,14),(16,_binary '','','2019-04-18 10:12:23',NULL,'vagish.dixit@tothenew.com','Vagish','Dixit','$2a$04$bm1hvd4ZzdmZU4BZDAsvzeSSGBm6ppBejFtj1/xjh3k0CXoUcb7MS','/assets/profileImages/default.png',0,NULL,'2019-04-18 10:14:28',16,16);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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

CREATE DATABASE  IF NOT EXISTS `pickme` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pickme`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: pickme
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `carpool_details`
--

DROP TABLE IF EXISTS `carpool_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carpool_details` (
  `pool_id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_id` int(11) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL,
  `passenger_count` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `route` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pool_id`),
  KEY `vehicle_id_idx` (`vehicle_id`),
  KEY `driver_id_idx` (`driver_id`),
  CONSTRAINT `driver_id` FOREIGN KEY (`driver_id`) REFERENCES `driver_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehicle_id` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`vehicle_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carpool_details`
--

LOCK TABLES `carpool_details` WRITE;
/*!40000 ALTER TABLE `carpool_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `carpool_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispatcher`
--

DROP TABLE IF EXISTS `dispatcher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispatcher` (
  `pool_id` int(11) NOT NULL,
  `ride_id` int(11) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pool_id`,`ride_id`),
  KEY `pool_id_idx` (`pool_id`),
  KEY `ride_id_idx` (`ride_id`),
  CONSTRAINT `pool_id` FOREIGN KEY (`pool_id`) REFERENCES `carpool_details` (`pool_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ride_id` FOREIGN KEY (`ride_id`) REFERENCES `ride_details` (`ride_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispatcher`
--

LOCK TABLES `dispatcher` WRITE;
/*!40000 ALTER TABLE `dispatcher` DISABLE KEYS */;
/*!40000 ALTER TABLE `dispatcher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver_details`
--

DROP TABLE IF EXISTS `driver_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `license_number` varchar(45) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `memb_id_idx` (`member_id`),
  CONSTRAINT `memb_id` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver_details`
--

LOCK TABLES `driver_details` WRITE;
/*!40000 ALTER TABLE `driver_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contact` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `is_driver` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'chetan','punekar','0000-00-00','San Jose',2147483647,'chetan.punekar@sjsu.edu',NULL),(2,'fff','gggg','1984-09-07','fffff',666666,'ckckck@sjsu.com','N'),(3,'jjj','eee','1976-09-07','jjj',3333,'dkdkd@kk.com','N');

/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `notify_id` int(11) NOT NULL AUTO_INCREMENT,
  `notifyuser_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `message` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`notify_id`),
  KEY `notifyuser_id_idx` (`notifyuser_id`),
  CONSTRAINT `notifyuser_id` FOREIGN KEY (`notifyuser_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,1,'2016-08-04 00:00:00','Ride Booked');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking` (
  `parking_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`parking_id`),
  KEY `own_id_idx` (`owner_id`),
  KEY `loc_id_idx` (`location_id`),
  CONSTRAINT `loc_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `own_id` FOREIGN KEY (`owner_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_details`
--

DROP TABLE IF EXISTS `parking_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_details` (
  `parking_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `parker_id` int(11) DEFAULT NULL,
  `parking_id` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`parking_details_id`),
  KEY `parking_id_idx` (`parking_id`),
  KEY `parker_id` (`parker_id`),
  CONSTRAINT `parker_id` FOREIGN KEY (`parker_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parking_id` FOREIGN KEY (`parking_id`) REFERENCES `parking` (`parking_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_details`
--

LOCK TABLES `parking_details` WRITE;
/*!40000 ALTER TABLE `parking_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `parking_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `carpool_id` int(11) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `memb_id_idx` (`member_id`),
  KEY `carp_id_idx` (`carpool_id`),
  CONSTRAINT `carpo_id` FOREIGN KEY (`carpool_id`) REFERENCES `carpool_details` (`pool_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `membe_id` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_details`
--

DROP TABLE IF EXISTS `payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_details` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `card_number` varchar(45) DEFAULT NULL,
  `card_type` varchar(45) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `mem_id_idx` (`member_id`),
  CONSTRAINT `mem_id` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_details`
--

LOCK TABLES `payment_details` WRITE;
/*!40000 ALTER TABLE `payment_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ride_details`
--

DROP TABLE IF EXISTS `ride_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ride_details` (
  `ride_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `source_id` int(11) DEFAULT NULL,
  `dest_id` int(11) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `payment_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ride_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `source_id_idx` (`source_id`),
  KEY `dest_id_idx` (`dest_id`),
  CONSTRAINT `dest_id` FOREIGN KEY (`dest_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `source_id` FOREIGN KEY (`source_id`) REFERENCES `location` (`location_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ride_details`
--

LOCK TABLES `ride_details` WRITE;
/*!40000 ALTER TABLE `ride_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `ride_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_map`
--

DROP TABLE IF EXISTS `route_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route_map` (
  `location_1` int(11) NOT NULL,
  `location_2` int(11) NOT NULL,
  `distance` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`location_1`,`location_2`),
  KEY `location_2_idx` (`location_2`),
  CONSTRAINT `location_1` FOREIGN KEY (`location_1`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `location_2` FOREIGN KEY (`location_2`) REFERENCES `location` (`location_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_map`
--

LOCK TABLES `route_map` WRITE;
/*!40000 ALTER TABLE `route_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `route_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `vehicle_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) DEFAULT NULL,
  `registration_id` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`vehicle_id`),
  KEY `owner_id_idx` (`owner_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `driver_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `driver_vehicle` (
  `vehicle_id` INT(11) NOT NULL COMMENT '',
  `member_id` INT(11) NOT NULL COMMENT '',
  `is_current` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '',
  INDEX `fk_driver_vehicle_vehicle1_idx` (`vehicle_id` ASC)  COMMENT '',
  INDEX `fk_driver_vehicle_member1_idx` (`member_id` ASC)  COMMENT '',
  PRIMARY KEY (`vehicle_id`, `member_id`)  COMMENT '',
  CONSTRAINT `fk_driver_vehicle_vehicle1`
  FOREIGN KEY (`vehicle_id`)
  REFERENCES `vehicle` (`vehicle_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_driver_vehicle_member1`
  FOREIGN KEY (`member_id`)
  REFERENCES `member` (`member_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
  ENGINE = InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `driver_vehicle` WRITE;
/*!40000 ALTER TABLE `driver_vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver_vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


--
-- DML statements for location and route_map
--

INSERT INTO `location` (`location_id`,`name`) VALUES (1,'A');
INSERT INTO `location` (`location_id`,`name`) VALUES (2,'B');
INSERT INTO `location` (`location_id`,`name`) VALUES (3,'C');
INSERT INTO `location` (`location_id`,`name`) VALUES (4,'D');
INSERT INTO `location` (`location_id`,`name`) VALUES (5,'E');
INSERT INTO `location` (`location_id`,`name`) VALUES (6,'F');


INSERT INTO `route_map` (`location_1`,`location_2`,`distance`,`time`) VALUES (1,2,7,7);
INSERT INTO `route_map` (`location_1`,`location_2`,`distance`,`time`) VALUES (1,3,9,9);
INSERT INTO `route_map` (`location_1`,`location_2`,`distance`,`time`) VALUES (2,3,10,10);
INSERT INTO `route_map` (`location_1`,`location_2`,`distance`,`time`) VALUES (2,4,15,15);
INSERT INTO `route_map` (`location_1`,`location_2`,`distance`,`time`) VALUES (3,4,11,11);
INSERT INTO `route_map` (`location_1`,`location_2`,`distance`,`time`) VALUES (3,6,2,2);
INSERT INTO `route_map` (`location_1`,`location_2`,`distance`,`time`) VALUES (4,5,6,6);
INSERT INTO `route_map` (`location_1`,`location_2`,`distance`,`time`) VALUES (5,6,9,9);
INSERT INTO `route_map` (`location_1`,`location_2`,`distance`,`time`) VALUES (6,1,14,14);

-- Dump completed on 2016-08-09 22:04:27

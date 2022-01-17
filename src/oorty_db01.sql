/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 8.0.27 : Database - oorty_db01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oorty_db01` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `oorty_db01`;

/*Table structure for table `basic` */

DROP TABLE IF EXISTS `basic`;

CREATE TABLE `basic` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '城市id',
  `name` varchar(20) NOT NULL COMMENT '城市名',
  `latitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市纬度',
  `longitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208878088 DEFAULT CHARSET=utf8mb3;

/*Data for the table `basic` */

insert  into `basic`(`id`,`name`,`latitude`,`longitude`) values (101010100,'北京','39.90498','116.40528'),(101020100,'上海','31.23170','121.47264'),(101230101,'福州','26.07530','119.30623'),(101280101,'广州','23.12517','113.28064');

/*Table structure for table `weather` */

DROP TABLE IF EXISTS `weather`;

CREATE TABLE `weather` (
  `id` int NOT NULL COMMENT '城市id',
  `fxDate` date NOT NULL COMMENT '当日日期',
  `tempMax` int NOT NULL COMMENT '当日最高气温',
  `tempMin` int NOT NULL COMMENT '当日最低气温',
  `textDay` varchar(20) NOT NULL COMMENT '当日天气',
  PRIMARY KEY (`id`,`fxDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `weather` */

insert  into `weather`(`id`,`fxDate`,`tempMax`,`tempMin`,`textDay`) values (101010100,'2022-01-16',0,-7,'多云'),(101010100,'2022-01-17',3,-8,'晴'),(101010100,'2022-01-18',2,-7,'多云'),(101020100,'2022-01-16',11,6,'多云'),(101020100,'2022-01-17',11,5,'阴'),(101020100,'2022-01-18',11,4,'多云'),(101230101,'2022-01-16',15,11,'小雨'),(101230101,'2022-01-17',13,11,'阴'),(101230101,'2022-01-18',13,11,'阴'),(101280101,'2022-01-16',22,12,'多云'),(101280101,'2022-01-17',16,11,'阴'),(101280101,'2022-01-18',15,12,'小雨');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

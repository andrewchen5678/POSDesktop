/*
SQLyog Community Edition- MySQL GUI v6.56
MySQL - 5.0.51b-community-nt : Database - pos
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`pos` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `pos`;

/*Table structure for table `condiment` */

DROP TABLE IF EXISTS `condiment`;

CREATE TABLE `condiment` (
  `condID` int(11) NOT NULL auto_increment,
  `condName` varchar(255) collate utf8_unicode_ci NOT NULL,
  `condNameCN` varchar(255) collate utf8_unicode_ci default NULL,
  `extranolittle` tinyint(1) NOT NULL default '0',
  `sub` tinyint(1) NOT NULL default '0',
  `extraPrice` decimal(10,2) NOT NULL default '0.00',
  `subPrice` decimal(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`condID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `condiment` */

insert  into `condiment`(`condID`,`condName`,`condNameCN`,`extranolittle`,`sub`,`extraPrice`,`subPrice`) values (1,'spicy','辣',1,0,'0.00','0.00'),(2,'onion','洋葱',1,1,'0.00','0.00'),(3,'soy sauce','是油',1,1,'0.00','0.00'),(4,'salt','盐',1,0,'0.00','0.00'),(5,'shrimp','虾',1,1,'1.00','0.00'),(6,'beef','牛',1,1,'1.00','0.00'),(7,'chicken','鸡',1,1,'1.00','0.00'),(8,'pork','猪',1,1,'1.00','0.00'),(9,'vegetable','菜',1,1,'1.00','0.00'),(10,'sauce','锁士',1,1,'0.00','0.00'),(11,'steam rice','白饭',1,1,'1.00','0.00'),(12,'fried rice','炒饭',1,1,'1.00','1.00'),(13,'green onion','青葱',1,1,'0.00','0.00'),(14,'green pepper','青椒',1,1,'0.00','0.00'),(15,'black pepper','黑椒',1,1,'0.00','0.00'),(16,'crispy','脆',1,0,'0.00','0.00'),(17,'bean sprout','芽菜',1,0,'1.00','0.00'),(18,'hot sauce','辣椒酱',1,0,'0.00','0.00'),(19,'chili oil','辣椒油',0,0,'0.00','0.00'),(20,'fried','油炸',1,0,'0.00','0.00'),(21,'beans','豆',1,1,'0.00','0.00'),(22,'carrot','萝卜',1,0,'0.00','0.00');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `CustomerID` int(11) NOT NULL auto_increment,
  `Phone` char(10) collate utf8_unicode_ci NOT NULL,
  `Name` varchar(40) collate utf8_unicode_ci NOT NULL,
  `Address` varchar(255) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`CustomerID`),
  UNIQUE KEY `Phone` (`Phone`)
) ENGINE=MyISAM AUTO_INCREMENT=198 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `customer` */

insert  into `customer`(`CustomerID`,`Phone`,`Name`,`Address`) values (195,'5628764634','doessave','yesitdoes'),(194,'5625649876','fsdfds','testsave'),(193,'5436544325','fdsfds','testsave'),(11,'5624217288','a','4413 Paramount Blvd\nLakewood, CA 90712\n\n'),(12,'5626309340','b','2706 South St\nLakewood, CA'),(13,'5622902127','c','4528 Lakewood Blvd\n'),(14,'5626346079','d','3430 E La Jara St\nLong Beach, CA 90805\n'),(15,'3106973049','1','4213 Pixie Ave\nLakewood, CA 90712'),(16,'5628188714','Lisa','4616 Maybank Ave\nLakewood, CA 90712'),(17,'5623771599','3','4398 Levelside Ave\nLakewood, CA 90712\n\n'),(18,'5625524007','Victoria','4800 Clair Del Ave,'),(19,'5625958056','Maggaret','3756 Gardenia Ave\nLong Beach, CA 90807\n\n'),(20,'5624222028','Rita','5905 Paramount Blvd'),(21,'5624204719','4','4343 Vangold Ave\nLakewood, CA 90712\n'),(22,'3232725796','5','6147 Oliva Ave\nLakewood, CA 90712'),(23,'5626342500','6','4943 Paramount Blvd'),(24,'5626309401','Richie','2620 South St'),(25,'5626339425','Muldoom','5646 Paramount Blvd'),(26,'5624296124','7','4233 pixie ave'),(27,'5627042713','8','4500 N Lakewood #12'),(28,'5627395205','9','2613 Dashwood Lakewood'),(29,'5624255210','Nakim','4700 Airport Plaza #734 (Marriot Hotel)'),(30,'5624353145','10','4406 pixie ave'),(31,'5623134568','11','3223 Candlewood'),(32,'5624228792','12','1714 E Silva St\nLong Beach, CA 90807'),(33,'5623131726','ruvi','3737 Atlantic Ave\nLong Beach, CA 90807\n'),(34,'9168372250','13','5058 Autry Ave\nLakewood, CA 90712\n\nbellflower/hardwick'),(35,'5629200445','','5830 benmead lw'),(36,'5624251357','15','4858 Autry Ave\nLakewood, CA 90712'),(37,'5628181220','16','6459 Turnergrove Dr\nLakewood, CA 90713\n\ndel amo/palo verde ave'),(38,'5622546379','17','4222 obispo ave'),(39,'5626121264','18','5930 Rose Ave\nLong Beach, CA 90805\n\nCherry/E South St'),(40,'5627541768','','5290 ceritos ave.'),(41,'5625954112','19','3625 Gardenia Ave\nLong Beach, CA 90807\n'),(42,'5627195365','20','328 E Harding St\nLong Beach, CA 90805\n\natlantic, artesia/south'),(43,'6198132650','Arthur','11334 Muller St\nDowney, CA 90241\nstuderbaker/5 freeway\ntoo far, selective costumer'),(44,'5624288656','21','78 W 51st St\nLong Beach, CA 90805\n\ndel amo/long beach'),(45,'3235599483','chanzra','13919 Arthur Ave\nParamount, CA 90723\nparamount/105 freeway, too far, selective customer\n'),(46,'5624247058','22','3760 Gardenia Ave\nLong Beach, CA 90807\n\n'),(47,'5624294914','23','3702 Del Amo Blvd\n\nparamount blvd/lakewood blvd'),(48,'5624219597','24','2833 Harvey Way\nLakewood, CA 90712\n\n'),(49,'5624225667','25','2413 Allred St\nLakewood, CA 90712\n\n'),(50,'5626306995','26','2829 Dollar St\nLakewood'),(51,'5624929626','28','3855 gardenia'),(52,'5629844404','29','168 E Hullett St\nLong Beach, CA 90805\n\natlantic/ E south'),(53,'5624210480','30','4839 Pepperwood Ave, Long Beach\n\ndel amo/lakewood'),(54,'5629845596','31','6021 John Ave\nLong Beach\n\npass south, cherry/orange'),(55,'5626683636','32','2616 Greenmeadow Rd\nLakewood\n\ncarson/paramount'),(56,'5629883140','33','1132 E San Antonio Dr, Long Beach\n\norange/atlantic'),(57,'5628798664','34','4121 Country Club Dr, Lakewood\n\nlakewood/del amo'),(58,'5625317129','sharon','2430 dollar street'),(59,'5628524807','monica','4545 california ave\n\ndel amo/atlantic/san antonio'),(60,'5622539733','36','4436 lakewood Blvd #B'),(61,'5628667142','angie','5400 clark ave #246 lakewood '),(62,'5625089646','37','5806 Castana Ave\nLakewood\n\nsouth/lakewood'),(63,'5625228544','39','2903 Eckleson St\nLakewood'),(64,'5628045444','38','5308 montair ave\n\nbellflower/candlewood'),(65,'5624236446','40','2840 Allred St\nLakewood'),(66,'5629970700','Serina','3333 East Spring St\nlakewood/cherry/405 freeway'),(67,'5625954601','41','3940 Cherry Ave\n\npass Carson St'),(68,'5626345448','43','3308 Eckleson st'),(69,'5625371890','shay','2706 South st'),(70,'5624256126','44','3206 centralia St'),(71,'5624221668','45','4527 paramount blvd'),(72,'3109223030','46','1712 Hardwick'),(73,'5624298582','47','4322 Levelside Ave\nLakewood\n\nby obispo'),(74,'5624277752','jose','4007 paramount blvd #105'),(75,'5627284711','49','5922 John Ave\n\ncherry/orange/E South'),(76,'5629841021','50','1990 del amor\n\nshell gas station'),(77,'5624231123','51','4716 deeboyar ave\n\nparamount/cherry'),(78,'5623236292','52','4101 cherry ave'),(79,'5624235125','48','466 E Platt St\nLong Beach\n\natlantic/e market'),(80,'5624964409','53','2719 Freckles Rd\nLakewood\n\nparamount/carson'),(81,'5624228935','54','1901 E 63rd St\nLong Beach\n\ncherry/south'),(82,'5627080612','55','2945 allred st'),(83,'5629844304','56','331 E Hullett St\nLong Beach\n\nsouth/atlantic'),(84,'5626739689','57','5933 rose ave'),(85,'5624245778','59','3982 rose ave'),(86,'5624243033','tomy','3565 linden ave #323'),(87,'5625228483','60','6055 Gundry Ave\nLong Beach\n\nsouth/orange'),(88,'5624080726','58','2622 dollar st'),(89,'5626343546','61','2922 Dashwood'),(90,'5622782541','62','5003 Coldbrook Ave\nLakewood, CA 90713\n\ndel amo/bellflower'),(91,'7147438570','63','3207 Arbor Rd\nLakewood, CA 90712\n\n'),(92,'3107662308','65','4541 E carson'),(93,'5622154878','66','2512 deerford '),(94,'5104483556','brandon','5833 Gaviota Ave\nLong Beach\n\ncherry/south/orange'),(95,'5628046262','64','5623 Lorelei Ave\nLakewood, CA 90712\n\nsouth/bellflower'),(96,'5624282307','67','5060 gardenia'),(97,'5624277646','68','3651 gardenia'),(98,'5624226671','69','5901 california'),(99,'7144656381','70','4466 Paula Ave\nLakewood\n\ndel amo/palo verde/carson'),(100,'5626066849','71','1719 E Hardwick Lakewood'),(101,'3104034935','72','6171 lewis ave'),(102,'5624256993','73','4132 pixie #9'),(103,'5624172200','74','18900 susana st'),(104,'5626342003','75','15302 gundry #115'),(105,'4247857047','76','5117 deeboyar ave\n\nparamount/del amo/cherry'),(106,'5802363752','whortley','1725 Long Beach Blvd\nLong Beach\ntoo far, selective'),(107,'5624229217','77','2212 E McKenzie St\nLong Beach, CA 90805\n\ncherry/artesia, btwn 63rd & 65th Street'),(108,'5624223966','78','1203 E Jackson St\nLong Beach, CA 90805\n\ndel amo/orange ave'),(109,'5623316558','79','5035 Pearce Ave\nLakewood, CA 90712\n\nbellflower/del amo'),(110,'5624250162','80','3513 Allred St\nLakewood, CA 90712\n\n'),(111,'5624233765','81','Clair Del Ave #523 code#022\n\ncherry/del amo/paramount'),(112,'5622300841','82','6125 lemon ave'),(113,'5624214709','amanda','4522 lakewood blvd #307'),(114,'5624216360','Dabe','3327 Fairman St\nLakewood, CA 90712\n\n'),(115,'5623073333','monica 1','1847 E Poppy St\nLong Beach, CA 90805\n\norange/cherry/artesia/south'),(116,'5624218591','clark','3504 Deeford St'),(117,'5626062605','Gail','1910 E 59th St'),(118,'5624210701','88','4232 pixie ave'),(119,'5624255925','89','4502 obispo ave'),(120,'5626269818','90','3254 Lewis Ave\nLong Beach, CA 90807\nE wardlow/405 freeway/orange ave'),(121,'5623165220','92','4603 woodruff ave lakewood'),(122,'5624471200','93','18900 susana road'),(123,'5624080826','94','4923 deeboy ave'),(124,'5625318767','95','3213 silva st'),(125,'7149135358','96','2803 eckleson st'),(126,'5626336399','97','3102 Yearling St\nLakewood'),(127,'3105372000','98','19710 susana rd'),(128,'5623944325','lorna','L.B.airport jet blue'),(129,'5626331812','nakia','4927 minturn ave\n\nparam/del amo/lakewood'),(130,'5624971563','99','4121 Country Club Dr, Lakewood'),(131,'5626730340','100','3609 orange ave \n\n/36th st L.B.'),(132,'5623770567','101','6212 Henrilee st, lakewood '),(133,'5624210383','102','4831 levelside ave '),(134,'5624207562','103','2707 Greentop St\nLakewood\n\n/paramount'),(135,'5629252503','104','15718 Santa Ana Ave\n\n/alondra, bellflower'),(136,'5624286950','105','4672 vangold ave'),(137,'3107397132','106','1946 E Jackson St\n\n/cherry'),(138,'5629656045','107','5044 3 1/4 Hayter Ave'),(139,'5628651882','108','11507 renville st\n\n/del amo/norwalk blvd'),(140,'5624203515','109','4313 Canehill Ave\nLakewood\n\nwoodruff/palo verde/harvey way'),(141,'5626852727','111','12004 69th St\n\npioneer/artesia'),(142,'5626062999','112','5844 GUNDRY AVE\n\nSOUTH/LONG BEACH'),(143,'5624961779','114','3223 CENTRALIA ST\n\n/PARAMOUNT'),(144,'5629845684','115','4800 CLAIRDEL AVE #320'),(145,'5624006147','116','4819 LEVELSIDE AVE'),(146,'8636617500','117','5649 LANGPORT AVE'),(147,'5626302885','118','6162 PIMENTA AVE '),(148,'5623553476','119','5060 PACIFIC AVE\n\nL.B./DEL AMO'),(149,'5624248686','121','3912 MARSHALL WAY'),(150,'5625952401','124','2613 YEARLING \n\nPARAMOUNT/HARDWICK\n\n\n'),(151,'5624281034','125','6020 GARDENIA'),(152,'5624221792','126','6182 GUNDRY '),(153,'5626330139','127','5003 VERDURA'),(154,'5624961729','128','3223 CENTRALIA '),(155,'5626301981','129','5602 GRYWOOD\n\nMICHELSON/DOWNEY'),(156,'5624211357','dina','4206 MAYBANK \n\nGREENMEADOW/PARAMOUNT'),(157,'5624226517','manuel','1830 McKenzie\n\ncherry/63rd st'),(158,'5656456454','vghvhvghvghvg','gyhgvhjvycghcgvbkvgh\nnjnbjkbh'),(159,'5787654765','Customer 159','gfndjgntjknkjretr'),(160,'1234567890','hfdhfugd','gbtrbgutrkhtrhtrhtr'),(161,'6666666666','fdfdsf','dsfds'),(162,'1234564532','fsdfs','fdsgfdgfdgfd'),(163,'9876543210','fndjnj','addddddrrrrrr'),(164,'5629475645','fdsfsd',''),(165,'5463789653','fhrejhfbrejname','ytuhfrccaddr'),(166,'5627687890','fhudhud','fhdshfudshuggsdgfd'),(167,'5624567682','fhsrfhsegb','fhdshufhsruehrerwreuw'),(168,'5628574643','hfruhure','fdfd'),(169,'4356434643','rere','fdd'),(170,'6265182641','claaudia','4819 paramount blvd'),(171,'6265182642','','546 hujkfbj'),(172,'5624729566','lam','6319 california ave'),(173,'5626305167','','8636 cedar st bellflower    artesia/downey'),(174,'5625311185','',''),(175,'5626337563','',''),(176,'5626196079','','3739 candlewood street        downey/candlewood'),(177,'5624921230','',''),(178,'5624232274','',''),(179,'3233638873','','12516 lemming st lw  broomfield/del amo'),(180,'5623944307','LB airport','4225 donanod douglas dr   LB'),(181,'5629052600','','6033 Turner Grove Dr LW paraverde/del amo'),(182,'9492953651','','4800 clairdel ave #353 LB delamo/cherry'),(183,'5621961779','','3223 Centralia ave LW'),(184,'3102212756','','4541 east carson LB'),(185,'5626764487','','1970 e. 53th st. lb'),(186,'5628049799','','5029 briervrest lk'),(187,'5624256490','','4326 sunfield ave. lb'),(188,'5624298708','','5313 e. carson st. lb'),(189,'5624297515','alnorld',''),(190,'5624218396','','4848 coobrook lw'),(191,'5629259935','','4945 woodruff'),(192,'5621234567','one to seven','one to seven addr'),(196,'5564454339','hugug','hjhuhjhgf'),(197,'6473847463','testcreatename','testcreateaddr');

/*Table structure for table `customerorder` */

DROP TABLE IF EXISTS `customerorder`;

CREATE TABLE `customerorder` (
  `OrderID` int(11) NOT NULL auto_increment,
  `CustomerName` varchar(40) collate utf8_unicode_ci default NULL,
  `Phone` varchar(255) collate utf8_unicode_ci default NULL,
  `Address` varchar(255) collate utf8_unicode_ci default NULL,
  `OrderType` enum('here','pick up','delivery') collate utf8_unicode_ci default NULL,
  `Subtotal` decimal(10,2) default NULL,
  `Tax` decimal(10,2) default NULL,
  `Total` decimal(10,2) default NULL,
  `Time` timestamp NULL default NULL,
  PRIMARY KEY  (`OrderID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `customerorder` */

insert  into `customerorder`(`OrderID`,`CustomerName`,`Phone`,`Address`,`OrderType`,`Subtotal`,`Tax`,`Total`,`Time`) values (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 17:00:52'),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 17:02:17'),(3,NULL,NULL,NULL,'delivery','10.95','0.90','11.85','2008-07-29 17:17:26'),(4,NULL,NULL,NULL,'delivery','11.90','0.98','12.88','2008-07-29 17:18:33'),(5,NULL,NULL,NULL,'delivery','10.95','0.90','11.85','2008-07-29 17:18:47'),(6,NULL,NULL,NULL,'delivery','7.50','0.62','8.12','2008-07-29 17:18:53'),(7,NULL,NULL,NULL,'delivery','4.95','0.41','5.36','2008-07-29 17:19:00'),(8,NULL,NULL,NULL,'delivery','5.95','0.49','6.44','2008-07-29 17:19:09'),(9,NULL,NULL,NULL,'delivery','4.95','0.41','5.36','2008-07-29 17:19:15'),(10,NULL,NULL,NULL,'delivery','8.25','0.68','8.93','2008-07-29 17:19:20'),(11,NULL,NULL,NULL,'delivery','5.95','0.49','6.44','2008-07-29 17:19:33'),(12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 17:24:06'),(13,NULL,NULL,NULL,'delivery','62.95','5.19','68.14','2008-07-29 17:25:46'),(14,NULL,NULL,NULL,'delivery','62.00','5.12','67.12','2008-07-29 17:26:36'),(15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 17:26:45'),(16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 17:35:40'),(17,NULL,NULL,NULL,'delivery','105.15','8.67','113.82','2008-07-29 17:43:19'),(18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 17:43:34'),(19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 17:51:10'),(20,NULL,NULL,NULL,'delivery','7.95','0.66','8.61','2008-07-29 17:51:17'),(21,NULL,NULL,NULL,'delivery','0.00','0.00','0.00','2008-07-29 17:51:25'),(22,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 17:51:31'),(23,NULL,NULL,NULL,'delivery','0.00','0.00','0.00','2008-07-29 17:51:38'),(24,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 17:51:45'),(25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 18:01:18'),(26,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 18:12:22'),(27,NULL,NULL,NULL,'delivery','0.00','0.00','0.00','2008-07-29 18:16:28'),(28,NULL,NULL,NULL,'delivery','0.00','0.00','0.00','2008-07-29 18:18:10'),(29,NULL,NULL,NULL,'delivery','0.00','0.00','0.00','2008-07-29 18:24:56'),(30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 18:30:57'),(31,'hfdhfugd','1234567890','gbtrbgutrkhtrhtrhtr','delivery','5.25','0.43','5.68','2008-07-29 18:31:22'),(32,NULL,NULL,NULL,'delivery','4.95','0.41','5.36','2008-07-29 18:31:58'),(33,NULL,NULL,NULL,'delivery','11.50','0.95','12.45','2008-07-29 18:40:49'),(34,NULL,NULL,NULL,'delivery','12.95','1.07','14.02','2008-07-29 18:41:26'),(35,NULL,NULL,NULL,'delivery','57.50','4.74','62.24','2008-07-29 18:42:07'),(36,NULL,NULL,NULL,'delivery','25.70','2.12','27.82','2008-07-29 18:48:27'),(37,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 18:48:30'),(38,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 18:52:08'),(39,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 18:53:57'),(40,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-07-29 18:54:32');

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `MenuEntryID` int(11) NOT NULL auto_increment,
  `MenuEntryName` varchar(40) collate utf8_unicode_ci NOT NULL,
  `MenuEntryNameCN` varchar(20) collate utf8_unicode_ci NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  `LunchSpecial` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`MenuEntryID`)
) ENGINE=MyISAM AUTO_INCREMENT=193 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `menu` */

insert  into `menu`(`MenuEntryID`,`MenuEntryName`,`MenuEntryNameCN`,`Price`,`LunchSpecial`) values (1,'Curry Chicken','咖哩鸡(特)','5.95',1),(2,'Chicken w/Broccoli','芥兰鸡(特)','5.95',1),(3,'Chicken w/Vegetables','菜鸡(特)','5.95',1),(4,'Cashew Nut Chicken','腰果鸡(特)','5.95',1),(5,'Almond Chicken','杏仁鸡(特)','5.95',1),(6,'Chicken w/Garlic Sauce','蒜汁鸡(特)','5.95',1),(7,'Sweet & Sour Chicken','甜酸鸡(特)','5.95',1),(8,'Kung Pao Chicken','公保鸡(特)','5.95',1),(9,'Orange Chicken','陳皮鸡(特)','5.95',1),(10,'Szechwan Chicken','鱼香鸡(特)','5.95',1),(11,'Sweet & Sour Pork','甜酸肉(特)','5.95',1),(12,'Chicken w/Lemon Sauce','柠檬鸡(特)','5.95',1),(13,'Beef w/Broccoli','介兰牛(特)','6.25',1),(14,'Beef w/Satay Sauce','沙爹牛(特)','6.25',1),(15,'Beef w/Black Pepper Sauce','黑椒牛(特)','6.25',1),(16,'Beef w/Oyster Sauce','蚝油牛(特)','6.25',1),(17,'Green Pepper Steak','青椒牛扒(特)','6.25',1),(18,'Beef w/Ginger& Green Onion','姜葱牛(特)','6.25',1),(19,'Mongolian Beef','蒙古牛(特)','6.25',1),(20,'Kung Pao Beef','公保牛(特)','6.25',1),(21,'Beef w/Galic Sauce','蒜汁牛(特)','6.25',1),(22,'Beef w/Mixed Vegetable','菜牛(特)','6.25',1),(23,'Curry Beef','咖哩牛(特)','6.25',1),(24,'Szechuan Beef','鱼香牛(特)','6.25',1),(25,'Shrimp w/Broccoli','芥兰虾(特)','6.95',1),(26,'Shrimp w/Mixed Vegetable','菜虾(特)','6.95',1),(27,'Cashew Shrimp','腰果虾(特)','6.95',1),(28,'Kung Pao Shrimp','公保虾(特)','6.95',1),(29,'Shrimp W/Garlic Sauce','蒜汁虾(特)','6.95',1),(30,'Sweet & Sour Shrimp','甜酸虾(特)','6.95',1),(31,'Szechwan Shrimp','鱼香虾(特)','6.95',1),(32,'Szechuan Tofu','鱼香豆腐(特)','4.95',1),(33,'Stir-Fried Broccoli','清炒芥兰(特)','4.95',1),(34,'Stir-Fried Vegetable','清炒菜(特)','4.95',1),(35,'Stir-Fried Green Bean','清炒四季豆(特)','4.95',1),(36,'Braised Crushed Tofu','---','4.95',1),(37,'Kung Pao Tofu','公保豆腐(特)','4.95',1),(38,'Salt & Pepper Tofu','椒盐豆腐(特)','4.95',1),(39,'Orange Tofu','陈皮豆腐(特)','4.95',1),(40,'Salted Pepper Calamari','椒盐尤鱼','4.95',0),(41,'Cream Cheese Wonton(8)','炸芝士云吞','4.95',0),(42,'Chicken Egg Roll(4)','鸡卷','4.95',0),(43,'Vegetable Egg Roll(4)','菜卷','4.50',0),(44,'Fried Wonton(10)','炸云吞','4.95',0),(45,'Fried Shrimp(6)','炸虾','5.95',0),(46,'Dumpling (Postickers)(10)','煎锅贴','5.25',0),(47,'Steamed Dumpling(10)','蒸水饺','5.25',0),(48,'Fried Chicken Wings(8)','炸鸡翼','5.25',0),(49,'BBQ Ribs(4)','烧排骨','7.50',0),(50,'BBQ Pork','叉烧','7.50',0),(51,'Chinese Chicken Salad','鸡沙拉','5.95',0),(52,'Vegetables Salad','菜沙拉','5.25',0),(53,'Moo Shu Pork/Chicken/Beef','木须猪/鸡/牛','6.95',0),(54,'Moo Shu Shrimp','木须虾','7.95',0),(55,'Seafood Soup','海鲜汤','8.95',0),(56,'Egg Flower Soup','蛋花汤','5.95',0),(57,'Wor Wonton Soup','禾云吞汤','7.50',0),(58,'Wonton Soup','云吞汤','6.50',0),(59,'Sizzling Rice Soup w/ Three De','三鲜汤','7.95',0),(60,'Corn Cream w/ Chicken Soup','鸡粟汤','5.95',0),(61,'Hot and Sour Soup','酸辣汤','5.95',0),(62,'Vegetable Soup','菜汤','5.95',0),(63,'Asparagus Beef/Pork/Chicken','离荀牛/猪/鸡','10.95',0),(64,'Asparagus Shrimp','离荀虾','12.95',0),(65,'Walnut Shrimp','合桃虾','12.25',0),(66,'Phoenix Chicken','凤凰鸡','9.95',0),(67,'Snow Peas Beef','雪豆牛','10.25',0),(68,'Crispy Beef','炸脆牛','10.25',0),(69,'Crispy Duck (half)','炸鸭(半只)','9.95',0),(70,'BBQ Duck(half)','烧鸭(半只)','12.95',0),(71,'Sauteed Three Delight','炒三鲜','12.95',0),(72,'Sweet and Pungent Shrimp','溜炒虾','12.50',0),(73,'Baked Pork Chop w/Spicy Salt','椒盐猪扒','8.25',0),(74,'Sweet and Sour Pork','甜酸猪肉','8.25',0),(75,'Twice Cooked Pork','回锅肉','8.25',0),(76,'Shredded Pork w/ Szechuan Sauce','鱼香猪肉','8.25',0),(77,'Pork w/ Mixed Vegetables','菜炒猪肉','8.25',0),(78,'Curry Pork','咖哩猪肉','8.25',0),(79,'Bean Sprout Pork','芽菜猪肉','8.25',0),(80,'Green Bean Chicken','四季豆鸡','8.25',0),(81,'Chicken Broccoli','芥兰鸡','8.25',0),(82,'Chicken w/ Mixed Vegetable','菜鸡','8.25',0),(83,'Cashew Nut Chicken','腰果鸡','8.25',0),(84,'Kung Pao Chicken','宫保鸡','8.25',0),(85,'Green Pepper Chicken','青椒鸡','8.25',0),(86,'Curry Chicken','咖哩鸡','8.25',0),(87,'Chicken w/ Lemon Sauce','柠檬鸡','8.25',0),(88,'Sweet and Sour Chicken','甜酸鸡','8.25',0),(89,'Sesame Chicken','芝麻鸡','8.25',0),(90,'Orange Chicken','陈皮鸡','8.25',0),(91,'Chicken w/ Garlic Sauce','蒜汁鸡','8.25',0),(92,'Teriyaki Chicken','日本鸡','8.25',0),(93,'Moo Goo Gai Pan','蒙古鸡片','8.25',0),(94,'Almond Chicken','杏仁鸡','8.25',0),(95,'Szechuan Chicken','鱼香鸡','8.25',0),(96,'Green Bean Beef','四季牛','8.75',0),(97,'Beef Broccoli','介兰牛','8.75',0),(98,'Ginger Beef w/ Green Onion','姜葱牛','8.75',0),(99,'Beef w/ Lobster Sauce','龙虾糊牛','8.75',0),(100,'Beef w/ Mixed Vegetable','菜牛','8.75',0),(101,'Beef w/ Mushroom','香菇牛','8.75',0),(102,'Mongolian Beef','蒙古牛','8.75',0),(103,'Szechuan Beef','鱼香牛','8.75',0),(104,'Green Pepper Beef','青椒牛','8.75',0),(105,'Beef w Black Pepper Sauce','黑椒牛','8.75',0),(106,'Curry Beef','咖哩牛','8.75',0),(107,'Beef w/ Satay Sauce','沙爹牛','8.75',0),(108,'Beef w/ Garlic Sauce','蒜汁牛','8.75',0),(109,'Orange Beef','陈皮牛','8.75',0),(110,'Phoenix Beef','凤凰牛','8.75',0),(111,'Kung Pao Beef','宫保牛','8.75',0),(112,'Cashew Shrimp','腰果虾','11.50',0),(113,'Shrimp Broccoli','介兰虾','11.50',0),(114,'Shrimp w/ Mixed Vegetable','菜虾','11.50',0),(115,'Kung Pao Shrimp','宫保虾','11.50',0),(116,'Shrimp w/ Garlic Sauce','蒜汁虾','11.50',0),(117,'Curry Shrimp','咖哩虾','11.50',0),(118,'Szechuan Shrimp','鱼香虾','11.50',0),(119,'Sweet and Sour Shrimp','甜酸虾','11.50',0),(120,'Salt & Pepper Shrimp','椒盐虾','11.50',0),(121,'Shrimp w/ Lobster Sauce','龙虾糊','11.50',0),(122,'Broccoli w/ Fish Fillet','介蓝鱼片','11.50',0),(123,'Fish Fillet w/ Sweet and Sour ','甜酸鱼片','11.50',0),(124,'Fish Fillet Slice w/ Black Bean','黑汁鱼片','11.50',0),(125,'Sauteed Stir-Fried Fish Fillet','沙爹炒鱼片','11.50',0),(126,'Sauteed Shrimp','沙爹虾','11.50',0),(127,'Stir-Fried Broccoli','炒介兰','6.95',0),(128,'Stir-Fried Mixed Vegetable','炒菜','6.95',0),(129,'Mushroom w/ Broccoli','磨菇介兰','6.95',0),(130,'Szechuan Tofu','鱼香豆腐','6.95',0),(131,'Spicy Eggplants','辣茄子','6.95',0),(132,'Stir-Fried Green Bean','炒四季豆','6.95',0),(133,'Chinese Broccoli w/ Oyster Sauce','中国蚝油介兰','6.95',0),(134,'Salt and Pepper Tofu','花椒盐豆腐','6.95',0),(135,'Kung Pao Tofu','宫保豆腐','6.95',0),(136,'Orange Tofu','陈皮豆腐','6.95',0),(137,'Shrimp Fried Rice','虾炒饭','7.50',0),(138,'House Special Fried Rice','本楼炒饭','7.50',0),(139,'Chicken Fried Rice','鸡炒饭','6.50',0),(140,'BBQ Pork Fried Rice','叉烧炒饭','6.50',0),(141,'Beef Fried Rice','牛肉炒饭','6.50',0),(142,'Vegetable Fried Rice','菜炒饭','6.25',0),(143,'Shrimp Chow Mein','虾炒面','7.50',0),(144,'Chicken Chow Mein','鸡炒面','6.95',0),(145,'Beef Chow Mein','牛炒面','6.95',0),(146,'BBQ Pork Chow Mein','叉烧炒面','6.95',0),(147,'House Special Chow Mein','本楼炒面','7.50',0),(148,'Pad Thai','泰粉','7.25',0),(149,'Shrimp Fried Rice Noodle','虾米粉','7.50',0),(150,'BBQ Pork Fried Rice Noodle','叉烧米粉','6.95',0),(151,'Chicken Rice Noodle','鸡米粉','6.95',0),(152,'House Special Rice Noodle','本楼米粉','7.95',0),(153,'Shrimp Chow Fun','虾炒河粉','7.50',0),(154,'Seafood Chow Fun','海鲜炒河粉','7.95',0),(155,'House Special Chow Fun','本楼炒河粉','7.95',0),(156,'Chicken Chow Fun','鸡炒河粉','6.95',0),(157,'Beef Chow Fun','牛河','6.95',0),(158,'Shrimp Crispy Pan-Fried Noodle','虾两面黄','8.25',0),(159,'Seafood Crispy Pan-Fried Noodle','海鲜两面黄','8.25',0),(160,'House Special Crispy Pan-Fried','本楼两面黄','8.25',0),(161,'Chicken Crispy Pan-Fried Noodle','鸡两面黄','7.50',0),(162,'BBQ Pork Crispy-Fried Noodle','叉烧两面黄','7.50',0),(163,'Beef Crispy Pan-Fried Noodle','牛肉两面黄','7.50',0),(164,'Shrimp Egg Foo Young','虾夫蓉','7.95',0),(165,'House Special Egg Foo Young','本楼夫蓉','7.95',0),(166,'Chicken Egg Foo Young','鸡夫蓉','7.50',0),(167,'BBQ Pork Egg Foo Young','叉烧夫蓉','7.50',0),(168,'Beef Egg Foo Young','牛夫蓉','7.50',0),(169,'Vegetable Egg Foo Young','菜夫蓉','6.50',0),(170,'Steamed Mixed Vegetable','水煮菜','6.95',0),(171,'Steamed Vegetable with Tofu','水煮菜豆腐','7.50',0),(172,'Chicken Mixed Vegetable (steam)','水煮鸡肉菜','7.95',0),(173,'Shrimp/Fish Mixed Vegetable','水煮虾/鱼菜','9.95',0),(174,'Bottle Water','水','1.25',0),(175,'Can soda','汽水','1.25',0),(176,'Crispy Noodle(1bag)','炸面包','1.00',0),(177,'No.#1','一号餐','25.95',0),(178,'No.#2','二号餐','25.95',0),(179,'No.#3','三号餐','35.95',0),(180,'No.#4','四号餐','35.95',0),(181,'Vegetable Chow Mein','菜炒面','6.50',0),(182,'Tea','茶','1.25',0),(183,'Chili Oil','辣椒油','0.00',0),(184,'Hot Sauce','辣椒酱','0.00',0),(185,'Steam Rice(S)','白饭(小)','2.00',0),(186,'Fried Rice(S)','炒饭(小)','2.00',0),(187,'Walnut Chicken','合桃鸡','10.25',0),(188,'Steam Rice(L)','白饭(大)','4.00',0),(189,'Fried Rice(L)','炒饭(大)','4.00',0),(191,'Shrimp Black Beans','是汁虾','11.50',0),(192,'Fillet W/Black Bean Sauce','豆是鱼','12.25',0);

/*Table structure for table `multipleitem` */

DROP TABLE IF EXISTS `multipleitem`;

CREATE TABLE `multipleitem` (
  `ID` int(11) NOT NULL auto_increment,
  `MenuEntryID` int(11) NOT NULL,
  `MenuEntryName` varchar(40) collate utf8_unicode_ci NOT NULL,
  `MenuEntryNameCN` varchar(20) collate utf8_unicode_ci NOT NULL default '---',
  `Price` decimal(10,2) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `multipleitem` */

insert  into `multipleitem`(`ID`,`MenuEntryID`,`MenuEntryName`,`MenuEntryNameCN`,`Price`) values (1,53,'Moo Shu Pork','木须猪','6.95'),(2,53,'Moo Shu Chicken','木须鸡','6.95'),(3,53,'Moo Shu Beef','木须牛','6.95'),(4,63,'Asparagus Beef','离荀牛','10.95'),(5,63,'Asparagus Pork','离荀猪','10.95'),(6,63,'Asparagus Chicken','离荀鸡','10.95'),(7,173,'Shrimp Mixed Vegetable (Steam)','水煮菜虾','9.95'),(8,173,'Fish Mixed Vegetable (Steam)','水煮菜鱼','9.95'),(9,175,'Coke','可乐','1.25'),(10,175,'Seven Up','七喜','1.25'),(11,175,'Sunkist','橙汁','1.25'),(12,175,'Diet Coke','减肥可乐','1.25'),(13,175,'Sprite','雪碧','1.25');

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `EntryID` int(11) NOT NULL auto_increment,
  `OrderID` int(11) NOT NULL,
  `ItemQuantity` int(11) NOT NULL,
  `ItemName` varchar(40) collate utf8_unicode_ci NOT NULL,
  `ItemNameCN` varchar(20) collate utf8_unicode_ci default NULL,
  `ItemPrice` decimal(10,2) NOT NULL,
  `MiscItem` tinyint(2) default NULL,
  `notes` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`EntryID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `orderdetail` */

/*Table structure for table `pass` */

DROP TABLE IF EXISTS `pass`;

CREATE TABLE `pass` (
  `id` int(11) NOT NULL auto_increment,
  `passtype` varchar(12) collate utf8_unicode_ci default NULL,
  `passvalue` char(32) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `pass` */

insert  into `pass`(`id`,`passtype`,`passvalue`) values (1,'settle','b0447595a667a6c19d7ded9ff6605d05');

/*Table structure for table `shifts` */

DROP TABLE IF EXISTS `shifts`;

CREATE TABLE `shifts` (
  `shiftnumber` int(11) NOT NULL auto_increment,
  `endorder` int(11) NOT NULL,
  PRIMARY KEY  (`shiftnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `shifts` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

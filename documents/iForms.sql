# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.25)
# Database: iForms
# Generation Time: 2020-03-03 08:43:23 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table answer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
  `answer_id` varchar(100) NOT NULL DEFAULT '',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` int(11) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `answer_description` varchar(200) DEFAULT NULL,
  `answer_option_id` varchar(200) DEFAULT NULL,
  `answer_value` varchar(200) DEFAULT NULL,
  `total_value` int(11) DEFAULT NULL,
  `reference` varchar(200) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `language` varchar(10) NOT NULL DEFAULT 'en-us',
  PRIMARY KEY (`id`),
  KEY `fk_form_id_idx` (`form_id`),
  KEY `fk_question_id_idx` (`question_id`),
  CONSTRAINT `fk_form_answer_on_form_id` FOREIGN KEY (`form_id`) REFERENCES `form` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_answer_on_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;

INSERT INTO `answer` (`answer_id`, `id`, `form_id`, `question_id`, `answer_description`, `answer_option_id`, `answer_value`, `total_value`, `reference`, `created_by`, `created_date`, `modified_by`, `modified_date`, `customer_id`, `language`)
VALUES
	('5b27ffbf53de4bdfabf68d0ff62537f1',1,19,76,NULL,'1',NULL,NULL,'order-10000001','peng','2020-02-24 21:36:39','peng','2020-01-31 23:25:07',101,'en-us'),
	('5b27ffbf53de4bdfabf68d0ff62537f1',2,19,77,NULL,'1',NULL,NULL,'order-10000001','peng','2020-02-24 21:36:46','peng','2020-01-31 23:25:07',101,'en-us'),
	('5b27ffbf53de4bdfabf68d0ff62537f1',3,19,77,NULL,'2',NULL,NULL,'order-10000001','peng','2020-02-24 21:39:12','peng','2020-01-31 23:25:07',102,'en-us'),
	('5b27ffbf53de4bdfabf68d0ff62537f1',4,19,78,'test',NULL,NULL,NULL,'order-10000001','peng','2020-02-24 21:39:14','peng','2020-01-31 23:25:07',102,'en-us'),
	('5b27ffbf53de4bdfabf68d0ff62537f1',5,19,79,NULL,NULL,'4',5,'order-10000001','peng','2020-02-24 21:39:17','peng','2020-01-31 23:25:07',102,'en-us'),
	('5b27ffbf53de4bdfabf68d0ff62537f1',6,19,80,'2020-01-01',NULL,NULL,NULL,'order-10000001','peng','2020-02-24 21:39:19','peng','2020-01-31 23:25:07',102,'en-us'),
	('5b27ffbf53de4bdfabf68d0ff62537f1',7,19,81,NULL,NULL,'6',10,'order-10000001','peng','2020-02-24 21:39:20','peng','2020-01-31 23:25:07',102,'en-us'),
	('1e469dc52dbe4777b25855cf5ecf7546',8,19,76,NULL,'1',NULL,NULL,'order-000001','peng','2020-03-02 22:10:13','peng','2020-03-02 22:10:13',103,'en-us'),
	('1e469dc52dbe4777b25855cf5ecf7546',9,19,77,NULL,'1',NULL,NULL,'order-000001','peng','2020-03-02 22:10:13','peng','2020-03-02 22:10:13',103,'en-us'),
	('1e469dc52dbe4777b25855cf5ecf7546',10,19,77,NULL,'2',NULL,NULL,'order-000001','peng','2020-03-02 22:10:13','peng','2020-03-02 22:10:13',103,'en-us'),
	('1e469dc52dbe4777b25855cf5ecf7546',11,19,78,'test',NULL,NULL,NULL,'order-000001','peng','2020-03-02 22:10:13','peng','2020-03-02 22:10:13',103,'en-us'),
	('1e469dc52dbe4777b25855cf5ecf7546',12,19,79,NULL,NULL,'4',5,'order-000001','peng','2020-03-02 22:10:13','peng','2020-03-02 22:10:13',103,'en-us'),
	('1e469dc52dbe4777b25855cf5ecf7546',13,19,80,'2020-01-01',NULL,NULL,NULL,'order-000001','peng','2020-03-02 22:10:13','peng','2020-03-02 22:10:13',103,'en-us'),
	('1e469dc52dbe4777b25855cf5ecf7546',14,19,81,NULL,NULL,'6',10,'order-000001','peng','2020-03-02 22:10:13','peng','2020-03-02 22:10:13',103,'en-us'),
	('29837e04135d4b1db0651760d9ad638c',15,19,76,NULL,'1',NULL,NULL,'order-000001','peng','2020-03-02 22:20:59','peng','2020-03-02 22:20:59',104,'en-us'),
	('29837e04135d4b1db0651760d9ad638c',16,19,77,NULL,'1',NULL,NULL,'order-000001','peng','2020-03-02 22:20:59','peng','2020-03-02 22:20:59',104,'en-us'),
	('29837e04135d4b1db0651760d9ad638c',17,19,77,NULL,'2',NULL,NULL,'order-000001','peng','2020-03-02 22:20:59','peng','2020-03-02 22:20:59',104,'en-us'),
	('29837e04135d4b1db0651760d9ad638c',18,19,78,'test',NULL,NULL,NULL,'order-000001','peng','2020-03-02 22:20:59','peng','2020-03-02 22:20:59',104,'en-us'),
	('29837e04135d4b1db0651760d9ad638c',19,19,79,NULL,NULL,'4',5,'order-000001','peng','2020-03-02 22:20:59','peng','2020-03-02 22:20:59',104,'en-us'),
	('29837e04135d4b1db0651760d9ad638c',20,19,80,'2020-01-01',NULL,NULL,NULL,'order-000001','peng','2020-03-02 22:20:59','peng','2020-03-02 22:20:59',104,'en-us'),
	('29837e04135d4b1db0651760d9ad638c',21,19,81,NULL,NULL,'6',10,'order-000001','peng','2020-03-02 22:20:59','peng','2020-03-02 22:20:59',104,'en-us');

/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table client
# ------------------------------------------------------------

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  `token` varchar(500) NOT NULL DEFAULT '',
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;

INSERT INTO `client` (`id`, `name`, `token`, `is_active`)
VALUES
	(1,'iForms_GTA','YWVzLTI1Ni1nY206Y0c5UGMwMXFRWGxOUXpCM1RWTXdlVTlUTUhoT1VWZFJZMUA1Mi4zOS45MC4yNjo1MjIxOQNv6RRuGEVvmGjB+jimI/gw==',1);

/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table customer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '',
  `email` varchar(100) DEFAULT '',
  `contact_no` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;

INSERT INTO `customer` (`id`, `name`, `email`, `contact_no`)
VALUES
	(101,'PengLiu','peng.liu@volvo.com','123456789'),
	(102,'L','L','123'),
	(103,'peng','liupeng826@163.com','123456'),
	(104,'peng','liupeng826@163.com','123456');

/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table dept
# ------------------------------------------------------------

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(200) NOT NULL DEFAULT '',
  `market_id` varchar(100) NOT NULL DEFAULT '' COMMENT '上级部门',
  `contact_no` varchar(100) DEFAULT '1',
  `email` varchar(300) DEFAULT '1',
  `address` varchar(200) DEFAULT '',
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `created_by` varchar(50) DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dept_market_on_market_id_idx` (`market_id`),
  CONSTRAINT `fk_dept_market_on_market_id` FOREIGN KEY (`market_id`) REFERENCES `market` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;

INSERT INTO `dept` (`id`, `name`, `market_id`, `contact_no`, `email`, `address`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`)
VALUES
	('10086','移动','1','10086','liupeng826@163.com','天津市',0,'10086','2020-02-29 14:51:48',NULL,NULL),
	('703518','Japan dealer','40','','','',1,'','2020-02-29 14:30:23',NULL,NULL),
	('v1003','dept','40','123456',NULL,'address',0,'v1003','2020-02-29 10:26:53',NULL,NULL);

/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table email_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `email_config`;

CREATE TABLE `email_config` (
  `language` varchar(20) NOT NULL DEFAULT '',
  `subject` varchar(5000) DEFAULT '',
  `body` varchar(5000) DEFAULT '',
  PRIMARY KEY (`language`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `email_config` WRITE;
/*!40000 ALTER TABLE `email_config` DISABLE KEYS */;

INSERT INTO `email_config` (`language`, `subject`, `body`)
VALUES
	('ar-ar','شكرا لمشاركتك في الاستبيان ','عزيزي العميل،,&lt;br/&gt;&lt;br/&gt;نشكرك على تخصيص بعض الوقت  لردك على الاستبيان في ما يلي ردك الذي قدمته خلال الاستطلاع. وسيتصل بك احد  موظيفنا للمزيد من المعلومات&lt;br/&gt;&lt;br/&gt;{0}&lt;br/&gt;{1}&lt;br/&gt;{2}&lt;br/&gt;التقييم{3}&lt;br/&gt;&lt;br/&gt;أطيب التحيات&lt;br/&gt;{4}'),
	('en-us','Thank you for your feedback','Dear Customer,&lt;br/&gt;&lt;br/&gt;Thank you for taking time to provide your feedback. Below is your response that you have provided during the survey. Our dealer personnel will contact you to discuss further.&lt;br/&gt;Your Response to survey :&lt;br/&gt;&lt;br/&gt;{0}&lt;br/&gt;{1}&lt;br/&gt;{2}&lt;br/&gt;Your Feedback:{3}&lt;br/&gt;&lt;br/&gt;Kind Regards&lt;br/&gt;{4}'),
	('es-ni','Gracias por su comentario','Estimado cliente,&lt;br/&gt;&lt;br/&gt;Gracias por tomarse el tiempo para enviarnos sus comentarios. A continuación se encuentra su respuesta que ha proporcionado durante la encuesta. Nuestro personal del concesionario se comunicará con usted para analizarlo más al detalle.&lt;br/&gt;Su respuesta a la encuesta:&lt;br/&gt;&lt;br/&gt;{0}&lt;br/&gt;{1}&lt;br/&gt;{2}&lt;br/&gt;Sus comentarios: {3}&lt;br/&gt;&lt;br/&gt;Muy atentamente&lt;br/&gt;{4}'),
	('id-id','Terima kasih atas tanggapan anda','Pelanggan yang terhormat,&lt;br/&gt;&lt;br/&gt;Terima kasih telah meluangkan waktu untuk memberikan tanggapan Anda. Berikut adalah tanggapanyang Anda berikan selama survei berlangsung. Petugas dealer kami akan menghubungi Anda untuk mendiskusikan lebih lanjut.&lt;br/&gt;Tanggapan survey anda:&lt;br/&gt;&lt;br/&gt;{0}&lt;br/&gt;{1}&lt;br/&gt;{2}&lt;br/&gt;Tanggapan Anda:{3}&lt;br/&gt;&lt;br/&gt;Salam&lt;br/&gt;{4}'),
	('ja-jp','',''),
	('th-th','ขอบคุณสำหรับการให้ข้อมูล','เรียน ลูกค้า,&lt;br/&gt;&lt;br/&gt;ขอขอบคุณที่สละเวลาในการให้คำติชมของคุณ ซึ่งคำตอบของคุณที่ให้ไว้ในระหว่างการสำรวจ ศูนย์บริการของเราจะติดต่อคุณเพื่อนำข้อมูลไปพัฒนาการทำงานให้ดีย่งขึ้น&lt;br/&gt;คำตอบสำหรับการสำรวจของคุณ:&lt;br/&gt;&lt;br/&gt;{0}&lt;br/&gt;{1}&lt;br/&gt;{2}&lt;br/&gt;ความคิดเห็นของคุณ:{3}&lt;br/&gt;&lt;br/&gt;ขอแสดงความนับถือ&lt;br/&gt;{4}'),
	('zh-cn','',''),
	('zh-tw','','');

/*!40000 ALTER TABLE `email_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table form
# ------------------------------------------------------------

DROP TABLE IF EXISTS `form`;

CREATE TABLE `form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `super_form_id` varchar(200) NOT NULL DEFAULT '',
  `title` varchar(200) NOT NULL DEFAULT '',
  `description` varchar(500) NOT NULL DEFAULT '',
  `level` varchar(50) DEFAULT '' COMMENT '问卷归属级别',
  `market_id` varchar(50) DEFAULT NULL,
  `dept_id` varchar(50) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `client` int(11) NOT NULL,
  `send_email` tinyint(1) NOT NULL DEFAULT '0',
  `type` varchar(100) DEFAULT NULL,
  `include_section` tinyint(1) NOT NULL,
  `language` varchar(10) NOT NULL DEFAULT 'en-us',
  `created_by` varchar(50) NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_form_client_on_client_id_idx` (`client`),
  CONSTRAINT `fk_form_client_on_client_id` FOREIGN KEY (`client`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `form` WRITE;
/*!40000 ALTER TABLE `form` DISABLE KEYS */;

INSERT INTO `form` (`id`, `super_form_id`, `title`, `description`, `level`, `market_id`, `dept_id`, `is_active`, `client`, `send_email`, `type`, `include_section`, `language`, `created_by`, `created_date`, `modified_by`, `modified_date`)
VALUES
	(19,'5b27ffbf53de4bdfabf68d0ff62537f1','Survey','test survey','market','40','',1,1,1,'volvo GTA',1,'en-us','1','2020-02-29 14:34:21','peng','2020-01-31 22:37:20'),
	(20,'5b27ffbf53de4bdfabf68d0ff62537f0','Survey','test survey','','40','703518',1,1,1,'volvo GTA',1,'en-us','1','2020-02-29 14:34:24','peng','2020-02-11 17:58:08'),
	(21,'fd0814952fa64940af83d2a80c06de11','Survey','test survey','','40','703518',1,1,1,'volvo GTA',1,'en-us','1','2020-02-29 14:34:27','peng','2020-02-11 17:59:48'),
	(22,'63fa0da6b4654829968e6380fce17746','Survey','test survey','market','40','',1,1,1,'volvo GTA',1,'en-us','1','2020-02-29 14:34:28','peng','2020-02-20 10:48:04');

/*!40000 ALTER TABLE `form` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table language
# ------------------------------------------------------------

DROP TABLE IF EXISTS `language`;

CREATE TABLE `language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(100) NOT NULL DEFAULT '',
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;

INSERT INTO `language` (`id`, `code`, `description`, `is_active`)
VALUES
	(1,'en-us','United States - English',1),
	(2,'zh-cn','中国 - 简体中文',1),
	(3,'ja-jp','日本 - 日本語',1),
	(4,'zh-tw','台灣 - 繁體中文',1),
	(5,'es-ni','Nicaragua - Español',1),
	(6,'ar-ar','العراق - العربية',1),
	(7,'id-id','Indonesia - Bahasa Indonesia',1),
	(8,'th-th','ไทย - ไทย',1);

/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table market
# ------------------------------------------------------------

DROP TABLE IF EXISTS `market`;

CREATE TABLE `market` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) NOT NULL DEFAULT '',
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `market` WRITE;
/*!40000 ALTER TABLE `market` DISABLE KEYS */;

INSERT INTO `market` (`id`, `name`, `is_active`)
VALUES
	('1','中国',1),
	('40','Japan',1);

/*!40000 ALTER TABLE `market` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table question
# ------------------------------------------------------------

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL DEFAULT '',
  `subtitle` varchar(200) DEFAULT NULL,
  `question_type_id` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `created_by` varchar(50) DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `language` varchar(10) NOT NULL DEFAULT 'en-us',
  `mandatory` tinyint(1) NOT NULL DEFAULT '1',
  `sequence` int(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_type_1_idx` (`question_type_id`),
  CONSTRAINT `fk_question_type_1_idx` FOREIGN KEY (`question_type_id`) REFERENCES `question_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;

INSERT INTO `question` (`id`, `section_id`, `title`, `subtitle`, `question_type_id`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`, `language`, `mandatory`, `sequence`)
VALUES
	(76,3,'question1','test question1',1,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,1),
	(77,3,'question2','test question2',2,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',0,2),
	(78,3,'question3','test question3',3,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,3),
	(79,3,'question4','test question4',4,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,4),
	(80,3,'question5','test question5',5,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,5),
	(81,3,'question6','test question6',6,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,6),
	(82,4,'question1','test question1',1,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,1),
	(83,4,'question2','test question2',2,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',0,2),
	(84,4,'question3','test question3',3,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,3),
	(85,4,'question4','test question4',4,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,4),
	(86,4,'question5','test question5',5,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,5),
	(87,4,'question6','test question6',6,1,'peng','2020-01-31 22:37:20','peng','2020-01-31 22:37:20','en-us',1,6),
	(88,5,'question1','test question1',1,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,1),
	(89,5,'question2','test question2',2,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',0,2),
	(90,5,'question3','test question3',3,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,3),
	(91,5,'question4','test question4',4,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,4),
	(92,5,'question5','test question5',5,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,5),
	(93,5,'question6','test question6',6,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,6),
	(94,6,'question1','test question1',1,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,1),
	(95,6,'question2','test question2',2,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',0,2),
	(96,6,'question3','test question3',3,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,3),
	(97,6,'question4','test question4',4,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,4),
	(98,6,'question5','test question5',5,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,5),
	(99,6,'question6','test question6',6,1,'peng','2020-02-11 17:58:08','peng','2020-02-11 17:58:08','en-us',1,6),
	(100,7,'question1','test question1',1,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,1),
	(101,7,'question2','test question2',2,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',0,2),
	(102,7,'question3','test question3',3,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,3),
	(103,7,'question4','test question4',4,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,4),
	(104,7,'question5','test question5',5,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,5),
	(105,7,'question6','test question6',6,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,6),
	(106,8,'question1','test question1',1,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,1),
	(107,8,'question2','test question2',2,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',0,2),
	(108,8,'question3','test question3',3,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,3),
	(109,8,'question4','test question4',4,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,4),
	(110,8,'question5','test question5',5,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,5),
	(111,8,'question6','test question6',6,1,'peng','2020-02-11 17:59:48','peng','2020-02-11 17:59:48','en-us',1,6),
	(112,9,'question1','test question1',1,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,1),
	(113,9,'question2','test question2',2,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',0,2),
	(114,9,'question3','test question3',3,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,3),
	(115,9,'question4','test question4',4,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,4),
	(116,9,'question5','test question5',5,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,5),
	(117,9,'question6','test question6',6,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,6),
	(118,10,'question1','test question1',1,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,1),
	(119,10,'question2','test question2',2,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',0,2),
	(120,10,'question3','test question3',3,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,3),
	(121,10,'question4','test question4',4,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,4),
	(122,10,'question5','test question5',5,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,5),
	(123,10,'question6','test question6',6,1,'peng','2020-02-20 10:48:04','peng','2020-02-20 10:48:04','en-us',1,6);

/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table question_option
# ------------------------------------------------------------

DROP TABLE IF EXISTS `question_option`;

CREATE TABLE `question_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) NOT NULL,
  `description` varchar(500) DEFAULT '',
  `sequence` int(11) DEFAULT NULL,
  `total_value` int(11) DEFAULT NULL,
  `net_promoter_from` varchar(100) DEFAULT '',
  `net_promoter_to` varchar(100) DEFAULT '',
  `language` varchar(10) NOT NULL DEFAULT 'en-us',
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_index` (`question_id`),
  CONSTRAINT `fk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `question_option` WRITE;
/*!40000 ALTER TABLE `question_option` DISABLE KEYS */;

INSERT INTO `question_option` (`id`, `question_id`, `description`, `sequence`, `total_value`, `net_promoter_from`, `net_promoter_to`, `language`, `is_active`)
VALUES
	(13,76,'questionOptions1',1,0,NULL,NULL,'en-us',1),
	(14,76,'questionOptions2',2,0,NULL,NULL,'en-us',1),
	(15,77,'questionOptions21',1,0,NULL,NULL,'en-us',1),
	(16,77,'questionOptions22',2,0,NULL,NULL,'en-us',1),
	(17,79,NULL,0,5,NULL,NULL,'en-us',1),
	(18,81,NULL,0,10,'Not at all likely','Extremely likely','en-us',1),
	(19,82,'questionOptions1',1,0,NULL,NULL,'en-us',1),
	(20,82,'questionOptions2',2,0,NULL,NULL,'en-us',1),
	(21,83,'questionOptions21',1,0,NULL,NULL,'en-us',1),
	(22,83,'questionOptions22',2,0,NULL,NULL,'en-us',1),
	(23,85,NULL,0,5,NULL,NULL,'en-us',1),
	(24,87,NULL,0,10,'Not at all likely','Extremely likely','en-us',1),
	(25,88,'questionOptions1',1,0,NULL,NULL,'en-us',1),
	(26,88,'questionOptions2',2,0,NULL,NULL,'en-us',1),
	(27,89,'questionOptions21',1,0,NULL,NULL,'en-us',1),
	(28,89,'questionOptions22',2,0,NULL,NULL,'en-us',1),
	(29,91,NULL,0,5,NULL,NULL,'en-us',1),
	(30,93,NULL,0,10,'Not at all likely','Extremely likely','en-us',1),
	(31,94,'questionOptions1',1,0,NULL,NULL,'en-us',1),
	(32,94,'questionOptions2',2,0,NULL,NULL,'en-us',1),
	(33,95,'questionOptions21',1,0,NULL,NULL,'en-us',1),
	(34,95,'questionOptions22',2,0,NULL,NULL,'en-us',1),
	(35,97,NULL,0,5,NULL,NULL,'en-us',1),
	(36,99,NULL,0,10,'Not at all likely','Extremely likely','en-us',1),
	(37,100,'questionOptions1',1,0,NULL,NULL,'en-us',1),
	(38,100,'questionOptions2',2,0,NULL,NULL,'en-us',1),
	(39,101,'questionOptions21',1,0,NULL,NULL,'en-us',1),
	(40,101,'questionOptions22',2,0,NULL,NULL,'en-us',1),
	(41,103,NULL,0,5,NULL,NULL,'en-us',1),
	(42,105,NULL,0,10,'Not at all likely','Extremely likely','en-us',1),
	(43,106,'questionOptions1',1,0,NULL,NULL,'en-us',1),
	(44,106,'questionOptions2',2,0,NULL,NULL,'en-us',1),
	(45,107,'questionOptions21',1,0,NULL,NULL,'en-us',1),
	(46,107,'questionOptions22',2,0,NULL,NULL,'en-us',1),
	(47,109,NULL,0,5,NULL,NULL,'en-us',1),
	(48,111,NULL,0,10,'Not at all likely','Extremely likely','en-us',1),
	(49,112,'questionOptions1',1,0,NULL,NULL,'en-us',1),
	(50,112,'questionOptions2',2,0,NULL,NULL,'en-us',1),
	(51,113,'questionOptions21',1,0,NULL,NULL,'en-us',1),
	(52,113,'questionOptions22',2,0,NULL,NULL,'en-us',1),
	(53,115,NULL,0,5,NULL,NULL,'en-us',1),
	(54,117,NULL,0,10,'Not at all likely','Extremely likely','en-us',1),
	(55,118,'questionOptions1',1,0,NULL,NULL,'en-us',1),
	(56,118,'questionOptions2',2,0,NULL,NULL,'en-us',1),
	(57,119,'questionOptions21',1,0,NULL,NULL,'en-us',1),
	(58,119,'questionOptions22',2,0,NULL,NULL,'en-us',1),
	(59,121,NULL,0,5,NULL,NULL,'en-us',1),
	(60,123,NULL,0,10,'Not at all likely','Extremely likely','en-us',1),
	(61,88,'option3',3,NULL,'','','en-us',1),
	(62,88,'option4',4,NULL,'','','en-us',1);

/*!40000 ALTER TABLE `question_option` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table question_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `question_type`;

CREATE TABLE `question_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL DEFAULT '',
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `question_type` WRITE;
/*!40000 ALTER TABLE `question_type` DISABLE KEYS */;

INSERT INTO `question_type` (`id`, `description`, `is_active`)
VALUES
	(1,'Choice',1),
	(2,'Multiple Choice',1),
	(3,'Text',1),
	(4,'Rating',1),
	(5,'Date',1),
	(6,'Net promoter score',1);

/*!40000 ALTER TABLE `question_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table section
# ------------------------------------------------------------

DROP TABLE IF EXISTS `section`;

CREATE TABLE `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL DEFAULT '',
  `description` varchar(500) DEFAULT '',
  `sequence` int(6) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `language` varchar(10) NOT NULL DEFAULT 'en-us',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;

INSERT INTO `section` (`id`, `form_id`, `title`, `description`, `sequence`, `is_active`, `language`)
VALUES
	(3,19,'section1','test section1',1,1,'en-us'),
	(4,19,'section2','test section2',2,1,'en-us'),
	(5,20,'section1','test section1',0,1,'en-us'),
	(6,20,'section2','test section2',0,1,'en-us'),
	(7,21,'section1','test section1',0,1,'en-us'),
	(8,21,'section2','test section2',0,1,'en-us'),
	(9,22,'section1','test section1',0,1,'en-us'),
	(10,22,'section2','test section2',0,1,'en-us');

/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(200) NOT NULL DEFAULT '' COMMENT '用户名',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(190) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `job_id` bigint(20) DEFAULT NULL COMMENT '岗位名称',
  `sex` tinyint(1) NOT NULL DEFAULT '1' COMMENT '性别',
  `role` varchar(10) NOT NULL DEFAULT '' COMMENT '职责(10：管理员，20：管理员，30：普通用户)',
  `dept_id` varchar(100) NOT NULL DEFAULT '' COMMENT '部门ID',
  `client_id` int(11) NOT NULL COMMENT '客户端',
  `is_active` tinyint(1) NOT NULL COMMENT '状态：1启用、0禁用',
  `created_by` varchar(50) NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_client_on_client_id_idx` (`client_id`),
  KEY `fk_dept_id_on_dept_id` (`dept_id`),
  CONSTRAINT `fk_dept_id_on_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_client_on_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统用户';

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `user_name`, `nick_name`, `password`, `email`, `phone`, `job_id`, `sex`, `role`, `dept_id`, `client_id`, `is_active`, `created_by`, `created_date`, `modified_by`, `modified_date`)
VALUES
	(1,'v0001','super admin','$2a$10$HhxyGZy.ulf3RvAwaHUGb.k.2i9PBpv4YbLMJWp8pES7pPhTyRCF.','liupeng826@hotmail.com','18888888888',11,0,'10','703518',1,1,'peng','2020-02-29 21:48:21','peng','2020-02-25 06:59:32'),
	(2,'v1000','admin','$2a$10$HhxyGZy.ulf3RvAwaHUGb.k.2i9PBpv4YbLMJWp8pES7pPhTyRCF.','liupeng826@163.com','17777777777',12,1,'20','703518',1,1,'peng','2020-02-29 21:47:17','peng','2020-02-23 06:09:35'),
	(3,'v1001','tester','$2a$10$HhxyGZy.ulf3RvAwaHUGb.k.2i9PBpv4YbLMJWp8pES7pPhTyRCF.','peng.liu@volvo.com','17777777777',12,1,'30','703518',1,1,'peng','2020-02-29 21:48:23','peng','2020-02-23 06:09:35'),
	(4,'v1002','nickName','$2a$10$HhxyGZy.ulf3RvAwaHUGb.k.2i9PBpv4YbLMJWp8pES7pPhTyRCF.',NULL,'12345',NULL,0,'30','703518',1,0,'v1002','2020-02-29 21:48:24',NULL,NULL),
	(5,'v1003','peng','$2a$10$HhxyGZy.ulf3RvAwaHUGb.k.2i9PBpv4YbLMJWp8pES7pPhTyRCF.',NULL,'string',NULL,0,'30','v1003',1,0,'v1003','2020-02-29 21:48:25',NULL,NULL),
	(8,'10086','移动','$2a$10$kv8wBGUejAnnaRG4giyhr.T2CY0NSksU46NyJlqr4FzXw5txjyWJ2','liupeng826@163.com','10086',NULL,0,'30','10086',1,0,'10086','2020-02-29 14:51:48',NULL,NULL);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

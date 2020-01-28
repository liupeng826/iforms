# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.25)
# Database: iForms
# Generation Time: 2020-01-27 04:21:51 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table form
# ------------------------------------------------------------

DROP TABLE IF EXISTS `form`;

CREATE TABLE `form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL DEFAULT '',
  `description` varchar(500) NOT NULL DEFAULT '',
  `level` varchar(50) DEFAULT NULL,
  `market_id` varchar(50) DEFAULT NULL,
  `dealer_id` varchar(50) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `customer_name` varchar(100) DEFAULT NULL,
  `customer_email` varchar(100) DEFAULT NULL,
  `contact_no` varchar(100) DEFAULT NULL,
  `system_token` varchar(100) DEFAULT NULL,
  `send_email` tinyint(1) NOT NULL DEFAULT '0',
  `type` varchar(100) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `language` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table form_question
# ------------------------------------------------------------

DROP TABLE IF EXISTS `form_question`;

CREATE TABLE `form_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` int(11) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `is_mandatory` varchar(100) NOT NULL DEFAULT '0',
  `sequence` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `language` varchar(10) NOT NULL DEFAULT '',
  `supper_id` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `fk_idx` (`question_id`),
  KEY `fk_idx2` (`form_id`),
  CONSTRAINT `fk_form_id` FOREIGN KEY (`form_id`) REFERENCES `form` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table form_question_answer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `form_question_answer`;

CREATE TABLE `form_question_answer` (
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
  PRIMARY KEY (`id`),
  KEY `fk_form_id_idx` (`form_id`),
  KEY `fk_question_id_idx` (`question_id`),
  CONSTRAINT `fk_form_answer_on_form_id` FOREIGN KEY (`form_id`) REFERENCES `form` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_answer_on_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



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
	(4,'zh-tw','台灣 - 繁體中文',1);

/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table question
# ------------------------------------------------------------

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL DEFAULT '',
  `subtitle` varchar(200) DEFAULT NULL,
  `question_type_id` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `created_by` varchar(50) DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `language` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `fk_question_type_1_idx` (`question_type_id`),
  CONSTRAINT `fk_question_type_1_idx` FOREIGN KEY (`question_type_id`) REFERENCES `question_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table question_option
# ------------------------------------------------------------

DROP TABLE IF EXISTS `question_option`;

CREATE TABLE `question_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) NOT NULL,
  `description` varchar(500) NOT NULL DEFAULT '',
  `sequence` int(11) DEFAULT NULL,
  `total_value` int(11) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `language` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `fk_index` (`question_id`),
  CONSTRAINT `fk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



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



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

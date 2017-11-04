-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.17-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for eboard
CREATE DATABASE IF NOT EXISTS `eboard` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `eboard`;

-- Dumping structure for table eboard.choice
CREATE TABLE IF NOT EXISTS `choice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK, serial',
  `topic` char(3) NOT NULL,
  `seq` int(11) NOT NULL,
  `value` varchar(40) NOT NULL,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `topic_seq` (`topic`,`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table eboard.choice: ~59 rows (approximately)
/*!40000 ALTER TABLE `choice` DISABLE KEYS */;
INSERT INTO `choice` (`id`, `topic`, `seq`, `value`, `name`) VALUES
	(1, 'E', 0, '', 'Select a Type'),
	(2, 'E', 1, 'MUSICAL', 'Musical'),
	(3, 'E', 2, 'CONCERT', 'Concert'),
	(4, 'E', 3, 'PLAY', 'Play'),
	(5, 'E', 4, 'MOVIE', 'Movie'),
	(6, 'E', 5, 'OTHER', 'Other'),
	(7, 'R', 1, 'ROLE_USER', 'Ordinary User'),
	(8, 'R', 2, 'ROLE_ADMIN', 'Administrator'),
	(9, 'S', 0, '', 'Select a State'),
	(10, 'S', 1, 'AL', 'Alabama'),
	(12, 'S', 3, 'AZ', 'Arizona'),
	(13, 'S', 4, 'AR', 'Arkansas'),
	(14, 'S', 5, 'CA', 'California'),
	(15, 'S', 6, 'CO', 'Colorado'),
	(16, 'S', 7, 'CT', 'Connecticut'),
	(17, 'S', 8, 'DC', 'District of Columbia'),
	(18, 'S', 9, 'DE', 'Delaware'),
	(19, 'S', 10, 'FL', 'Florida'),
	(20, 'S', 11, 'GA', 'Georgia'),
	(21, 'S', 12, 'HI', 'Hawaii'),
	(22, 'S', 13, 'ID', 'Idaho'),
	(23, 'S', 14, 'IL', 'Illinois'),
	(24, 'S', 15, 'IN', 'Indiana'),
	(25, 'S', 16, 'IA', 'Iowa'),
	(26, 'S', 17, 'KS', 'Kansas'),
	(27, 'S', 18, 'KY', 'Kentucky'),
	(28, 'S', 19, 'LA', 'Louisiana'),
	(29, 'S', 20, 'ME', 'Maine'),
	(30, 'S', 21, 'MD', 'Maryland'),
	(31, 'S', 22, 'MA', 'Massachusetts'),
	(32, 'S', 23, 'MI', 'Michigan'),
	(33, 'S', 24, 'MN', 'Minnesota'),
	(34, 'S', 25, 'MS', 'Mississippi'),
	(35, 'S', 26, 'MO', 'Missouri'),
	(36, 'S', 27, 'MT', 'Montana'),
	(37, 'S', 28, 'NE', 'Nebraska'),
	(38, 'S', 29, 'NV', 'Nevada'),
	(39, 'S', 30, 'NH', 'New Hampshire'),
	(40, 'S', 31, 'NJ', 'New Jersey'),
	(41, 'S', 32, 'NM', 'New Mexico'),
	(42, 'S', 33, 'NY', 'New York'),
	(43, 'S', 34, 'NC', 'North Carolina'),
	(44, 'S', 35, 'ND', 'North Dakota'),
	(45, 'S', 36, 'OH', 'Ohio'),
	(46, 'S', 37, 'OK', 'Oklahoma'),
	(47, 'S', 38, 'OR', 'Oregon'),
	(48, 'S', 39, 'PA', 'Pennsylvania'),
	(49, 'S', 40, 'RI', 'Rhode Island'),
	(50, 'S', 41, 'SC', 'South Carolina'),
	(51, 'S', 42, 'SD', 'South Dakota'),
	(52, 'S', 43, 'TN', 'Tennessee'),
	(53, 'S', 44, 'TX', 'Texas'),
	(54, 'S', 45, 'UT', 'Utah'),
	(55, 'S', 46, 'VT', 'Vermont'),
	(56, 'S', 47, 'VA', 'Virginia'),
	(57, 'S', 48, 'WA', 'Washington'),
	(58, 'S', 49, 'WV', 'West Virginia'),
	(59, 'S', 50, 'WI', 'Wisconsin'),
	(60, 'S', 51, 'WY', 'Wyoming');
/*!40000 ALTER TABLE `choice` ENABLE KEYS */;

-- Dumping structure for table eboard.filter
CREATE TABLE IF NOT EXISTS `filter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK, serial',
  `type` char(1) NOT NULL DEFAULT 'T' COMMENT 'Allowed Trait, Favor',
  `text` varchar(100) NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

-- Dumping data for table eboard.filter: 7 rows
/*!40000 ALTER TABLE `filter` DISABLE KEYS */;
INSERT INTO `filter` (`id`, `type`, `text`, `active`) VALUES
	(22, 'F', 'Favor 22', 1),
	(21, 'T', 'Trait 21', 1),
	(24, 'T', 'Trait 24', 1),
	(25, 'T', 'Trait 25', 1),
	(26, 'T', 'Trait 26', 1),
	(27, 'F', 'Favor 27', 1),
	(28, 'F', 'Favor 28', 1);
/*!40000 ALTER TABLE `filter` ENABLE KEYS */;

-- Dumping structure for table eboard.offer
CREATE TABLE IF NOT EXISTS `offer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK, serial',
  `org_id` int(11) NOT NULL COMMENT 'FK on org.id',
  `template` tinyint(4) NOT NULL DEFAULT '0',
  `title` varchar(100) NOT NULL,
  `open_ended` tinyint(4) NOT NULL DEFAULT '0',
  `event_date` datetime DEFAULT NULL,
  `event_start_time` varchar(50) DEFAULT NULL,
  `event_end_time` varchar(50) DEFAULT NULL,
  `type` varchar(50) NOT NULL,
  `address1` varchar(50) DEFAULT NULL,
  `address2` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` char(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `date_opened` datetime DEFAULT NULL,
  `accessibility` varchar(4096) DEFAULT NULL,
  `notes` varchar(4096) DEFAULT NULL,
  `date_closed` datetime DEFAULT NULL,
  `close_notes` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `org_id id` (`org_id`,`id`)
) ENGINE=MyISAM AUTO_INCREMENT=78 DEFAULT CHARSET=latin1 COMMENT='Need event name, kind, bathroom, \r\nsign interpreter';

-- Dumping data for table eboard.offer: 4 rows
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` (`id`, `org_id`, `template`, `title`, `open_ended`, `event_date`, `event_start_time`, `event_end_time`, `type`, `address1`, `address2`, `city`, `state`, `zip`, `date_opened`, `accessibility`, `notes`, `date_closed`, `close_notes`) VALUES
	(77, 1, 0, 'Offer 77 for Org 1', 0, NULL, NULL, NULL, 'Other', 'Offer 77 address', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(55, 23, 0, 'Offer 55 for Org 23', 0, NULL, '', '', 'Other', 'Offer 55 address', '', '', 'IL', '', '2015-03-16 05:00:00', '', '', NULL, ''),
	(52, 2, 0, 'Offer 52', 0, NULL, NULL, NULL, 'Other', 'Offer 52 Address1', NULL, NULL, 'IL', NULL, NULL, NULL, NULL, NULL, NULL),
	(65, 1, 1, 'New Offer', 0, NULL, NULL, NULL, 'Other', 'New Offer Address1', NULL, NULL, 'IL', NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;

-- Dumping structure for table eboard.offer_filter
CREATE TABLE IF NOT EXISTS `offer_filter` (
  `offer_id` int(11) NOT NULL COMMENT 'FK on offer.id',
  `filter_id` int(11) NOT NULL COMMENT 'FK on filter.id',
  PRIMARY KEY (`offer_id`,`filter_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table eboard.offer_filter: 5 rows
/*!40000 ALTER TABLE `offer_filter` DISABLE KEYS */;
INSERT INTO `offer_filter` (`offer_id`, `filter_id`) VALUES
	(55, 24),
	(55, 25),
	(55, 26),
	(55, 27),
	(64, 21);
/*!40000 ALTER TABLE `offer_filter` ENABLE KEYS */;

-- Dumping structure for table eboard.option
CREATE TABLE IF NOT EXISTS `option` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK, serial',
  `topic` char(3) NOT NULL,
  `seq` int(11) NOT NULL,
  `value` varchar(40) NOT NULL,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `topic_seq` (`topic`,`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Dumping data for table eboard.option: ~64 rows (approximately)
/*!40000 ALTER TABLE `option` DISABLE KEYS */;
INSERT INTO `option` (`id`, `topic`, `seq`, `value`, `name`) VALUES
	(1, 'E', 0, '', 'Select an Event Type'),
	(2, 'S', 1, 'MUSICAL', 'admin'),
	(3, 'S', 2, 'CONCERT', 'Concert'),
	(4, 'S', 3, 'PLAY', 'Play'),
	(5, 'S', 4, 'MOVIE', 'Movie'),
	(6, 'S', 5, 'OTHER', 'Other'),
	(7, 'R', 1, 'ROLE_USER', 'Ordinary User'),
	(8, 'R', 2, 'ROLE_ADMIN', 'Administrator'),
	(9, 'S', 0, '', 'Select a State'),
	(10, 'S', 1, 'AL', 'Alabama'),
	(12, 'S', 3, 'AZ', 'Arizona'),
	(13, 'S', 4, 'AR', 'Arkansas'),
	(14, 'S', 5, 'CA', 'California'),
	(15, 'S', 6, 'CO', 'Colorado'),
	(16, 'S', 7, 'CT', 'Connecticut'),
	(17, 'S', 8, 'DC', 'District of Columbia'),
	(18, 'S', 9, 'DE', 'Delaware'),
	(19, 'S', 10, 'FL', 'Florida'),
	(20, 'S', 11, 'GA', 'Georgia'),
	(21, 'S', 12, 'HI', 'Hawaii'),
	(22, 'S', 13, 'ID', 'Idaho'),
	(23, 'S', 14, 'IL', 'Illinois'),
	(24, 'S', 15, 'IN', 'Indiana'),
	(25, 'S', 16, 'IA', 'Iowa'),
	(26, 'S', 17, 'KS', 'Kansas'),
	(27, 'S', 18, 'KY', 'Kentucky'),
	(28, 'S', 19, 'LA', 'Louisiana'),
	(29, 'S', 20, 'ME', 'Maine'),
	(30, 'S', 21, 'MD', 'Maryland'),
	(31, 'S', 22, 'MA', 'Massachusetts'),
	(32, 'S', 23, 'MI', 'Michigan'),
	(33, 'S', 24, 'MN', 'Minnesota'),
	(34, 'S', 25, 'MS', 'Mississippi'),
	(35, 'S', 26, 'MO', 'Missouri'),
	(36, 'S', 27, 'MT', 'Montana'),
	(37, 'S', 28, 'NE', 'Nebraska'),
	(38, 'S', 29, 'NV', 'Nevada'),
	(39, 'S', 30, 'NH', 'New Hampshire'),
	(40, 'S', 31, 'NJ', 'New Jersey'),
	(41, 'S', 32, 'NM', 'New Mexico'),
	(42, 'S', 33, 'NY', 'New York'),
	(43, 'S', 34, 'NC', 'North Carolina'),
	(44, 'S', 35, 'ND', 'North Dakota'),
	(45, 'S', 36, 'OH', 'Ohio'),
	(46, 'S', 37, 'OK', 'Oklahoma'),
	(47, 'S', 38, 'OR', 'Oregon'),
	(48, 'S', 39, 'PA', 'Pennsylvania'),
	(49, 'S', 40, 'RI', 'Rhode Island'),
	(50, 'S', 41, 'SC', 'South Carolina'),
	(51, 'S', 42, 'SD', 'South Dakota'),
	(52, 'S', 43, 'TN', 'Tennessee'),
	(53, 'S', 44, 'TX', 'Texas'),
	(54, 'S', 45, 'UT', 'Utah'),
	(55, 'S', 46, 'VT', 'Vermont'),
	(56, 'S', 47, 'VA', 'Virginia'),
	(57, 'S', 48, 'WA', 'Washington'),
	(58, 'S', 49, 'WV', 'West Virginia'),
	(59, 'S', 50, 'WI', 'Wisconsin'),
	(60, 'S', 51, 'WY', 'Wyoming');
/*!40000 ALTER TABLE `option` ENABLE KEYS */;

-- Dumping structure for table eboard.org
CREATE TABLE IF NOT EXISTS `org` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK, serial',
  `provider` tinyint(4) NOT NULL DEFAULT '0',
  `name` varchar(100) NOT NULL,
  `address1` varchar(50) NOT NULL,
  `address2` varchar(50) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` char(2) NOT NULL,
  `zip` varchar(10) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `prefer_contact_method` char(1) NOT NULL DEFAULT 'E' COMMENT '(P)hone or (E)mail',
  `accessibility` varchar(4096) DEFAULT NULL,
  `notes` varchar(4096) DEFAULT NULL,
  `may_solicit` tinyint(4) NOT NULL DEFAULT '0',
  `active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

-- Dumping data for table eboard.org: 4 rows
/*!40000 ALTER TABLE `org` DISABLE KEYS */;
INSERT INTO `org` (`id`, `provider`, `name`, `address1`, `address2`, `city`, `state`, `zip`, `phone`, `email`, `prefer_contact_method`, `accessibility`, `notes`, `may_solicit`, `active`) VALUES
	(1, 0, 'Org 1', 'Address 1', NULL, 'City 1', 'IL', '60601', '312-1', 'email1', 'E', NULL, NULL, 0, 1),
	(23, 1, 'Org 23', 'Address 23', NULL, 'City 23', 'IL', '60623', '312-23', 'email23', 'E', NULL, NULL, 0, 1),
	(24, 1, 'Org 24', 'Address 24', NULL, 'City 24', 'IL', '60624', '312-24', 'email24', 'E', NULL, NULL, 0, 1),
	(2, 1, 'Org 2', 'Address 2', NULL, 'City 2', 'IL', '60602', '312-2', 'email2', 'E', NULL, NULL, 0, 1);
/*!40000 ALTER TABLE `org` ENABLE KEYS */;

-- Dumping structure for table eboard.org_filter
CREATE TABLE IF NOT EXISTS `org_filter` (
  `org_id` int(11) NOT NULL COMMENT 'FK on org.id',
  `filter_id` int(11) NOT NULL COMMENT 'FK on filter.id',
  PRIMARY KEY (`org_id`,`filter_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

-- Dumping data for table eboard.org_filter: 1 rows
/*!40000 ALTER TABLE `org_filter` DISABLE KEYS */;
INSERT INTO `org_filter` (`org_id`, `filter_id`) VALUES
	(1, 22);
/*!40000 ALTER TABLE `org_filter` ENABLE KEYS */;

-- Dumping structure for table eboard.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK, serial',
  `org_id` int(11) DEFAULT NULL COMMENT 'FK org.id',
  `org_provider` tinyint(4) DEFAULT '0',
  `username` varchar(100) NOT NULL COMMENT 'Unique across all users',
  `admin` tinyint(4) NOT NULL DEFAULT '0',
  `full_name` varchar(100) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `prefer_contact_method` char(1) NOT NULL DEFAULT 'E' COMMENT '(P)hone or (E)mail',
  `active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

-- Dumping data for table eboard.user: 3 rows
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `org_id`, `org_provider`, `username`, `admin`, `full_name`, `phone`, `email`, `prefer_contact_method`, `active`) VALUES
	(1, 24, 1, 'admin', 1, 'admin', '312.555.1212', 'admin@admin.com', 'E', 1),
	(2, 1, 0, 'user', 0, 'user', '111-111-1111', 'enabled@user.com', 'E', 1),
	(3, 2, 1, 'disabled', 0, 'disabled', '333-333-3333', 'disabled@home.com', 'E', 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               8.0.13
# Server OS:                    Win64
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2018-11-08 13:47:24
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for cms_db
CREATE DATABASE IF NOT EXISTS `cms_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cms_db`;


# Dumping structure for table cms_db. role_privileages
CREATE TABLE IF NOT EXISTS ` role_privileages` (
  `role_id` int(10) NOT NULL,
  `menu_id` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `creator` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table cms_db. role_privileages: ~0 rows (approximately)
/*!40000 ALTER TABLE ` role_privileages` DISABLE KEYS */;
/*!40000 ALTER TABLE ` role_privileages` ENABLE KEYS */;


# Dumping structure for table cms_db.access_token
CREATE TABLE IF NOT EXISTS `access_token` (
  `token` varchar(36) NOT NULL,
  `user_id` int(11) NOT NULL,
  `expiries` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table cms_db.access_token: ~0 rows (approximately)
/*!40000 ALTER TABLE `access_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `access_token` ENABLE KEYS */;


# Dumping structure for table cms_db.articles
CREATE TABLE IF NOT EXISTS `articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_id` int(20) NOT NULL COMMENT '分类ID',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(50) NOT NULL COMMENT '创建人',
  `content` text COMMENT '内容',
  `hits` bigint(20) NOT NULL COMMENT '浏览次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table cms_db.articles: ~0 rows (approximately)
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;


# Dumping structure for table cms_db.article_categories
CREATE TABLE IF NOT EXISTS `article_categories` (
  `id` int(10) NOT NULL COMMENT 'id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级类别id',
  `full_name` varchar(256) DEFAULT NULL COMMENT '类别全称',
  `name` varbinary(50) NOT NULL COMMENT '名称',
  `sort_no` int(11) NOT NULL COMMENT '排序码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator` varchar(50) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table cms_db.article_categories: ~0 rows (approximately)
/*!40000 ALTER TABLE `article_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_categories` ENABLE KEYS */;


# Dumping structure for table cms_db.menus
CREATE TABLE IF NOT EXISTS `menus` (
  `id` int(10) NOT NULL,
  `menu_name` varchar(50) NOT NULL,
  `url` varchar(256) NOT NULL,
  `sort_no` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `creator` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table cms_db.menus: ~0 rows (approximately)
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;


# Dumping structure for table cms_db.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(10) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `creator` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table cms_db.roles: ~0 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


# Dumping structure for table cms_db.role_users
CREATE TABLE IF NOT EXISTS `role_users` (
  `role_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `creator` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table cms_db.role_users: ~0 rows (approximately)
/*!40000 ALTER TABLE `role_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_users` ENABLE KEYS */;


# Dumping structure for table cms_db.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) NOT NULL,
  `logon_name` varchar(50) NOT NULL,
  `display_name` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(256) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_logoff_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `creator` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table cms_db.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `logon_name`, `display_name`, `password`, `email`, `mobile`, `last_login_time`, `last_logoff_time`, `create_time`, `creator`) VALUES
	(1, 'admin', '管理员', 'admin888', NULL, NULL, NULL, NULL, '2018-11-06 14:09:38', 'system');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


# Dumping structure for table cms_db.user_login_logs
CREATE TABLE IF NOT EXISTS `user_login_logs` (
  `id` bigint(20) NOT NULL,
  `user_id` int(10) NOT NULL,
  `login_time` datetime NOT NULL,
  `ip_address` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table cms_db.user_login_logs: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_login_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_login_logs` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

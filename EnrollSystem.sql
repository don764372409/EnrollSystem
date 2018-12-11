
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


DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `headImg` varchar(40) DEFAULT NULL COMMENT '图片地址',
  `phone` varchar(20) NOT NULL COMMENT '电话号码',
  `time` datetime NOT NULL COMMENT '添加时间',
  `loginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `lastTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `ip` char(15) DEFAULT NULL COMMENT '登录ip',
  `lastIp` char(15) DEFAULT NULL COMMENT '上次登录IP',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态  0--正常  1--非正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` VALUES (1,'admin','admin','admin','ndiwdiq','151qq','2018-12-10 18:11:32',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `typeId` bigint(20) NOT NULL COMMENT 'FK 分类id',
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dTypeId` (`typeId`),
  CONSTRAINT `fk_dTypeId` FOREIGN KEY (`typeId`) REFERENCES `t_dictionarytype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_dictionary` WRITE;
/*!40000 ALTER TABLE `t_dictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_dictionary` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_dictionarytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dictionarytype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_dictionarytype` WRITE;
/*!40000 ALTER TABLE `t_dictionarytype` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_dictionarytype` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_enroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_enroll` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uId` bigint(20) NOT NULL COMMENT 'FK 学校外键',
  `mId` bigint(20) DEFAULT NULL COMMENT 'FK 专业外键 为NULL则没专业',
  `batch` varchar(20) NOT NULL COMMENT '批次',
  `number` int(11) NOT NULL COMMENT '招生人数',
  `maxNumber` int(11) DEFAULT NULL,
  `minNumber` int(11) DEFAULT NULL,
  `avgNumber` int(11) DEFAULT NULL,
  `maxRanking` int(11) DEFAULT NULL,
  `minRanking` int(11) DEFAULT NULL,
  `avgRanking` int(11) DEFAULT NULL,
  `time` datetime NOT NULL COMMENT '招生发送时间',
  `tuition` decimal(10,0) NOT NULL COMMENT '学费',
  `studyYear` decimal(10,0) NOT NULL COMMENT '学制',
  PRIMARY KEY (`id`),
  KEY `fk_uId` (`uId`),
  KEY `fk_mId` (`mId`),
  CONSTRAINT `fk_mId` FOREIGN KEY (`mId`) REFERENCES `t_major` (`id`),
  CONSTRAINT `fk_uId` FOREIGN KEY (`uId`) REFERENCES `t_university` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_enroll` WRITE;
/*!40000 ALTER TABLE `t_enroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_enroll` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_major` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `pId` bigint(20) NOT NULL COMMENT 'FK 父级id 属于哪个大专业',
  `type` bigint(20) NOT NULL COMMENT 'FK 参照字典表中专业所属学历分类',
  `remark` text NOT NULL COMMENT '专业简介',
  `explain` text NOT NULL COMMENT '专业解读',
  `ranking` int(11) NOT NULL COMMENT '专业排名',
  PRIMARY KEY (`id`),
  KEY `pId` (`pId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_major` WRITE;
/*!40000 ALTER TABLE `t_major` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_major` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_major_occupation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_major_occupation` (
  `mId` bigint(20) NOT NULL,
  `oId` bigint(20) NOT NULL,
  KEY `2` (`oId`),
  KEY `1` (`mId`),
  CONSTRAINT `1` FOREIGN KEY (`mId`) REFERENCES `t_major` (`id`),
  CONSTRAINT `2` FOREIGN KEY (`oId`) REFERENCES `t_occupatio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_major_occupation` WRITE;
/*!40000 ALTER TABLE `t_major_occupation` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_major_occupation` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_occupatio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_occupatio` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `pId` bigint(20) NOT NULL COMMENT 'FK 父级职业',
  `remark` text NOT NULL COMMENT '职业简介',
  `workContent` varchar(20) DEFAULT NULL COMMENT '工作内容',
  PRIMARY KEY (`id`),
  KEY `pId` (`pId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_occupatio` WRITE;
/*!40000 ALTER TABLE `t_occupatio` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_occupatio` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_payment` (
  `id` bigint(20) NOT NULL,
  `uId` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uId` (`uId`),
  CONSTRAINT `uId` FOREIGN KEY (`uId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_payment` WRITE;
/*!40000 ALTER TABLE `t_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_payment` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_province` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_province` WRITE;
/*!40000 ALTER TABLE `t_province` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_province` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_university` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pId` bigint(20) NOT NULL COMMENT 'FK省外键',
  `address` varchar(50) NOT NULL COMMENT '所在地',
  `quality` bigint(20) NOT NULL COMMENT 'FK 参照字典表的院校水平分类		',
  `type` bigint(20) NOT NULL COMMENT 'FK 参照字典表的院校类型分类		',
  `remark` varchar(50) NOT NULL COMMENT '简介		',
  `ranking` int(11) NOT NULL COMMENT '院校排名		',
  `teachers` text NOT NULL COMMENT '师资团队,百度百科爬取		',
  `record` bigint(20) NOT NULL COMMENT 'FK 参照字典表的院校学历 ',
  `subject` text NOT NULL COMMENT '学科建设(需不需要存到字典中,暂定不存)		',
  PRIMARY KEY (`id`),
  KEY `t_university_ibfk_2` (`record`),
  KEY `t_university_ibfk_3` (`type`),
  KEY `t_university_ibfk_4` (`quality`),
  CONSTRAINT `t_university_ibfk_1` FOREIGN KEY (`id`) REFERENCES `t_province` (`id`),
  CONSTRAINT `t_university_ibfk_2` FOREIGN KEY (`record`) REFERENCES `t_dictionary` (`id`),
  CONSTRAINT `t_university_ibfk_3` FOREIGN KEY (`type`) REFERENCES `t_dictionary` (`id`),
  CONSTRAINT `t_university_ibfk_4` FOREIGN KEY (`quality`) REFERENCES `t_dictionary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_university` WRITE;
/*!40000 ALTER TABLE `t_university` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_university` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_university_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_university_major` (
  `uId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'FK  学校',
  `mId` bigint(20) NOT NULL COMMENT 'FK 专业外键',
  PRIMARY KEY (`uId`),
  KEY `mId` (`mId`),
  CONSTRAINT `t_university_major_ibfk_1` FOREIGN KEY (`uId`) REFERENCES `t_university` (`id`),
  CONSTRAINT `t_university_major_ibfk_2` FOREIGN KEY (`mId`) REFERENCES `t_major` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_university_major` WRITE;
/*!40000 ALTER TABLE `t_university_major` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_university_major` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `vip` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;



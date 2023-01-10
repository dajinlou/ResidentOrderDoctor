/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.27 : Database - communitydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`communitydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `communitydb`;

/*Table structure for table `admin_info` */

DROP TABLE IF EXISTS `admin_info`;

CREATE TABLE `admin_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `admin_info` */

insert  into `admin_info`(`id`,`adminName`,`name`,`tel`) values (1,'admin','靳军辉','18439417759'),(2,'pengMing','彭明','18536941285'),(3,'maoLi','毛利','14785963258');

/*Table structure for table `doctor_info` */

DROP TABLE IF EXISTS `doctor_info`;

CREATE TABLE `doctor_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `doctorname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医生账号',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医生姓名',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `tel` varchar(11) DEFAULT NULL COMMENT '电话',
  `room` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '科室',
  `experience` int DEFAULT NULL COMMENT '执业经历',
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '擅长领域',
  `orderCount` int DEFAULT NULL COMMENT '今日预约数量',
  PRIMARY KEY (`id`),
  KEY `fk_doctor_doctorInfo` (`doctorname`),
  CONSTRAINT `fk_doctor_doctorInfo` FOREIGN KEY (`doctorname`) REFERENCES `tb_doctor` (`doctorname`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `doctor_info` */

insert  into `doctor_info`(`id`,`doctorname`,`name`,`sex`,`tel`,`room`,`experience`,`major`,`orderCount`) values (1,'wangDoctor','王为民','男','18745687956','101科室',5,'耳科\\眼科\\口腔科',5),(2,'liDoctor','李丽丽','女','18236984578','102科室',5,'胸腔科',6),(3,'zhangDoctor','张国良','男','18439413698','103科室',7,'皮肤科',5),(4,'leiDoctor','雷利杰','女','15935897563','104科室',2,'口腔科',5),(5,'caoDoctor','曹操','男','18313133947','105科室',18,'骨科\\眼科',5),(6,'xuDoctor','许褚','男','18532698785','106科室',5,'口腔科',5),(7,'qianDoctor','钱洪军','男','15023698756','107科室',7,'儿科',5),(8,'linDoctor','林时金','男','18539647895','108科室',2,'神经科',5),(9,'gaoDoctor','高崇光','男','15756987235','109科室',4,'咽喉科',5),(10,'zhaoDoctor','赵风','女','15236987523','110科室',8,'眼科',5),(11,'zhouLiang','周亮','男','15039415268','201科室',10,'骨科',5);

/*Table structure for table `health_info` */

DROP TABLE IF EXISTS `health_info`;

CREATE TABLE `health_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `height` float DEFAULT NULL COMMENT '身高',
  `weight` float DEFAULT NULL COMMENT '体重',
  `blood_pressure` int DEFAULT NULL COMMENT '血压',
  `heart_rate` int DEFAULT NULL COMMENT '心率',
  `allergy_history` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '药物过敏史',
  `description` varchar(50) DEFAULT NULL COMMENT '身体状况',
  PRIMARY KEY (`id`),
  KEY `fk_healthInfo_user` (`username`),
  CONSTRAINT `fk_healthInfo_user` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `health_info` */

insert  into `health_info`(`id`,`username`,`name`,`height`,`weight`,`blood_pressure`,`heart_rate`,`allergy_history`,`description`) values (1,'xiaohei','小黑',173,78,101,68,'青霉素','最近有点头痛!!!'),(2,'leizhengya','雷正亚',160,50,95,77,'无','很棒！'),(3,'jinjunhui','靳军辉',175,78,105,73,'盘尼西林','超级棒！！！！！！！！'),(4,'xiaohou','小侯',173,50,101,73,'盘尼西林',NULL),(5,'zhangsan','张三',178,63,114,80,'无',NULL),(6,'limin','李敏',162,48,96,82,'头孢',NULL),(7,'zhaofen','赵芬',165,53,107,90,'无',NULL),(8,'lijinyang','李金阳',161,51,79,95,'无',NULL),(9,'houshun','侯顺',181,75,120,105,'青霉素',NULL),(10,'liuke','刘珂',183,65,75,92,'无',NULL),(11,'zanghao','臧浩',178,65,123,82,'无','很好'),(12,'qinMing','秦明',185,75,105,75,'无',NULL),(13,'liuZhaoHui','刘照辉',172,65,99,72,'无','OK'),(14,'韩春明','韩春明',185,75,100,81,'无','very good'),(15,'苏萌','苏萌',NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `history_order` */

DROP TABLE IF EXISTS `history_order`;

CREATE TABLE `history_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `doctorname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医生账号',
  `dname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医生姓名',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '居民账号',
  `uname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '居民姓名',
  `ordertime` datetime DEFAULT NULL COMMENT '预约时间',
  `orderaddress` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '预约地点',
  `status` int DEFAULT NULL COMMENT '状态:0:预约中 -1:拒绝 1:同意',
  PRIMARY KEY (`id`),
  KEY `fk_user_order` (`username`),
  KEY `fk_doctor_order` (`doctorname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `history_order` */

insert  into `history_order`(`id`,`doctorname`,`dname`,`username`,`uname`,`ordertime`,`orderaddress`,`status`) values (32,'wangDoctor','王为民','jinjunhui','靳军辉','2022-04-23 22:31:05','101科室',1),(33,'wangDoctor','王为民','zanghao','臧浩','2022-04-23 22:32:26','101科室',0),(36,'zhangDoctor','张国良','liMin','李敏','2022-04-30 22:45:25','103科室',0),(38,'liDoctor','李丽丽','limin','李敏','2022-04-22 20:49:58','102科室',0),(44,'liDoctor','李丽丽','xiaohei','小黑','2022-04-30 17:25:58','102科室',1),(45,'liDoctor','李丽丽','leizhengya','雷正亚','2022-04-30 18:44:15','102科室',1),(48,'wangDoctor','王为民','xiaohei','小黑','2022-05-10 18:59:49','101科室',0),(49,'caoDoctor','曹操','xiaohei','小黑','2022-05-21 20:14:22','105科室',-1),(50,'zhaoDoctor','赵风','xiaohei','小黑','2022-05-21 20:15:08','110科室',1),(51,'liDoctor','李丽丽','zanghao','臧浩','2022-05-14 21:32:55','102科室',0),(52,'liDoctor','李丽丽','jinjunhui','靳军辉','2022-05-28 21:39:05','102科室',0);

/*Table structure for table `nav_admin` */

DROP TABLE IF EXISTS `nav_admin`;

CREATE TABLE `nav_admin` (
  `menu_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单索引',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单名称',
  `title2` varchar(30) DEFAULT NULL COMMENT '菜单项名称',
  `icon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路径',
  `item_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单项索引',
  `component` varchar(20) DEFAULT NULL COMMENT '组件',
  PRIMARY KEY (`menu_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nav_admin` */

insert  into `nav_admin`(`menu_name`,`title`,`title2`,`icon`,`path`,`item_name`,`component`) values ('1','个人中心','个人中心','el-icon-user','/adminInfo','1-1','AdminInfo'),('2','用户账号管理','用户账号','el-icon-document','/users','2-1','Users'),('3','医生账号管理','医生账号','el-icon-document','/doctors','3-1','Doctors'),('4','所有预约信息','预约信息','el-icon-document-checked','/orderAll','4-1','OrderAll');

/*Table structure for table `nav_doctor` */

DROP TABLE IF EXISTS `nav_doctor`;

CREATE TABLE `nav_doctor` (
  `menu_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单索引',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单名称',
  `title2` varchar(30) DEFAULT NULL COMMENT '菜单项名字',
  `icon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路由',
  `item_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单项索引',
  `component` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '组件名称',
  PRIMARY KEY (`menu_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nav_doctor` */

insert  into `nav_doctor`(`menu_name`,`title`,`title2`,`icon`,`path`,`item_name`,`component`) values ('1','个人中心','个人中心','el-icon-user','/doctorInfo','1-1','DoctorInfo'),('2','居民信息','居民信息','el-icon-document','/userInfos','2-1','UserInfos'),('3','居民健康','居民健康信息','el-icon-document','/healthInfos','3-1','HealthInfos'),('4','预约信息','居民预约信息','el-icon-message','/orders','4-1','Orders');

/*Table structure for table `nav_user` */

DROP TABLE IF EXISTS `nav_user`;

CREATE TABLE `nav_user` (
  `menu_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单索引',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单名字',
  `title2` varchar(30) DEFAULT NULL COMMENT '菜单项名字',
  `icon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路由路径',
  `item_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单项目索引',
  `component` varchar(20) DEFAULT NULL COMMENT '组件',
  PRIMARY KEY (`menu_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nav_user` */

insert  into `nav_user`(`menu_name`,`title`,`title2`,`icon`,`path`,`item_name`,`component`) values ('1','个人中心','个人中心','el-icon-user','/userInfo','1-1','UserInfo'),('2','健康信息','个人健康信息','el-icon-document','/healthInfo','2-1','HealthInfo'),('3','医生信息','医生信息','el-icon-message','/doctorInfos','3-1','DoctorInfos'),('4','预约信息','我的预约信息','el-icon-document-checked','/order','4-1','Order');

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`id`,`adminName`,`name`,`password`) values (1,'admin','靳军辉','123'),(10,'pengMing','彭明','123'),(11,'maoLi','毛利','123');

/*Table structure for table `tb_doctor` */

DROP TABLE IF EXISTS `tb_doctor`;

CREATE TABLE `tb_doctor` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '医生id',
  `doctorname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '医生账号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`doctorname`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `tb_doctor` */

insert  into `tb_doctor`(`id`,`doctorname`,`name`,`password`) values (1,'caoDoctor','曹操','123'),(2,'gaoDoctor','高崇光','123'),(3,'leiDoctor','雷利杰','123'),(4,'liDoctor','李丽丽','123'),(5,'linDoctor','林时金','123'),(6,'qianDoctor','钱洪军','123'),(7,'wangDoctor','王为民','123'),(8,'xuDoctor','许褚','123'),(9,'zhangDoctor','张国良','123'),(10,'zhaoDoctor','赵风','123'),(11,'zhouLiang','周亮','123');

/*Table structure for table `tb_image` */

DROP TABLE IF EXISTS `tb_image`;

CREATE TABLE `tb_image` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `position` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '职务',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '照片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `tb_image` */

insert  into `tb_image`(`id`,`username`,`name`,`position`,`path`) values (1,'xiaohei','小黑','3','20220503201341b58dab132c2696cd.jpg'),(2,'leizhengya','雷正亚','3','2022043016291153.jpg'),(3,'liuke','刘珂','3','20220512171828beauty.jpg'),(4,'xiaohou','小侯','3','202204291804596479d8d60847b6a2.jpg'),(5,'caoDoctor','曹操','6','20220429181618QQ图片20220429181407.jpg'),(6,'admin','靳军辉','9','20220430201639靳军辉+河南理工大学.jpg'),(7,'lidoctor','李丽丽','6','20220430201520QQ图片20220429181414.jpg'),(8,'wangDoctor','王为民','6','20220430232806QQ图片20220429181400.jpg'),(9,'zhouliang','周亮','6','20220501153608QQ图片20220429181410.jpg'),(10,'zhaoDoctor','赵风','6','20220430234433QQ图片20220429181419.jpg'),(11,'linDoctor','林时金','6','20220430234638QQ图片20220429181231.jpg'),(12,'jinjunhui','靳军辉','3','20220430234947ad189962efe0305.jpg'),(13,'zanghao','臧浩','3','20220501153418e84c2cf36e386b90.jpg'),(16,'zhaofen','赵芬','3','20220508151125beauty.jpg'),(17,'qinming','秦明','3','20220512172147adfce0e287814148.jpg'),(18,'韩春明','韩春明','3','202205122102295cff42fba1b1d0d349b237a2105a61c2.jpeg'),(19,'苏萌','苏萌','3','20220512213218beauty.jpg');

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `doctorname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医生账号',
  `dname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医生姓名',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '居民账号',
  `uname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '居民姓名',
  `ordertime` datetime DEFAULT NULL COMMENT '预约时间',
  `orderaddress` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '预约地点',
  `status` int DEFAULT NULL COMMENT '状态:0:预约中 -1:拒绝 1:同意',
  PRIMARY KEY (`id`),
  KEY `fk_user_order` (`username`),
  KEY `fk_doctor_order` (`doctorname`),
  CONSTRAINT `fk_doctor_order` FOREIGN KEY (`doctorname`) REFERENCES `tb_doctor` (`doctorname`),
  CONSTRAINT `fk_user_order` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `tb_order` */

insert  into `tb_order`(`id`,`doctorname`,`dname`,`username`,`uname`,`ordertime`,`orderaddress`,`status`) values (32,'wangDoctor','王为民','jinjunhui','靳军辉','2022-04-23 22:31:05','101科室',1),(33,'wangDoctor','王为民','zanghao','臧浩','2022-04-23 22:32:26','101科室',0),(36,'zhangDoctor','张国良','liMin','李敏','2022-04-30 22:45:25','103科室',0),(38,'liDoctor','李丽丽','limin','李敏','2022-04-22 20:49:58','102科室',0),(44,'liDoctor','李丽丽','xiaohei','小黑','2022-04-30 17:25:58','102科室',1),(48,'wangDoctor','王为民','xiaohei','小黑','2022-05-10 18:59:49','101科室',0),(49,'caoDoctor','曹操','xiaohei','小黑','2022-05-21 20:14:22','105科室',-1),(50,'zhaoDoctor','赵风','xiaohei','小黑','2022-05-21 20:15:08','110科室',1),(51,'liDoctor','李丽丽','zanghao','臧浩','2022-05-14 21:32:55','102科室',0),(52,'liDoctor','李丽丽','jinjunhui','靳军辉','2022-05-28 21:39:05','102科室',0),(53,'liDoctor','李丽丽','liuke','刘珂','2022-05-14 16:29:56','102科室',0),(54,'liDoctor','李丽丽','qinming','秦明','2022-05-28 16:49:24','102科室',0);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`username`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`name`,`password`) values (1,'houshun','侯顺','123'),(2,'jinjunhui','靳军辉','111'),(3,'leizhengya','雷正亚','111'),(4,'lijinyang','李金阳','777'),(5,'limin','李敏','123'),(6,'liuke','刘珂','123'),(16,'liuZhaoHui','刘照辉','123'),(12,'qinMing','秦明','123'),(7,'xiaohei','小黑','123'),(8,'xiaohou','小侯','123'),(11,'zanghao','臧浩','123'),(9,'zhangsan','张三','123'),(10,'zhaofen','赵芬','666'),(18,'苏萌','苏萌','123'),(17,'韩春明','韩春明','123');

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '居民姓名',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `num` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话号码',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '住址',
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '个人说明',
  PRIMARY KEY (`id`),
  KEY `fk_userInfo_user` (`username`),
  CONSTRAINT `fk_userInfo_user` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`username`,`name`,`sex`,`birthday`,`num`,`address`,`description`) values (1,'xiaohei','小黑','男','2001-03-10','18436981318','黑家村','喜欢健身!'),(2,'leizhengya','雷正亚','0','2000-11-15','18466517759','小雷村','喜欢做瑜伽！！！'),(3,'jinjunhui','靳军辉','男','2000-01-25','18439411318','大靳楼村','NO NO NO！！！'),(4,'xiaohou','小侯','男','2018-01-02','123456789','张庄','喜欢跑步'),(5,'zhangsan','张三','男','2010-12-06','18439561318','王庄','喜欢计算机'),(6,'limin','李敏','女','2000-01-25','18439411318','王庄','喜欢计算机'),(7,'zhaofen','赵芬','女','1996-05-14','15361287749','赵庄','喜欢服装设计'),(8,'lijinyang','李金阳','女','2000-02-17','15836975548','李家村','喜欢摄影'),(9,'houshun','侯顺','男','1999-08-16','18436951147','侯家寨','喜欢PS'),(10,'liuke','刘珂','女','1998-06-15','18422597786','刘寨','喜欢演讲'),(12,'zanghao','臧浩','男','1998-08-03','18496572358','安徽省','NO'),(13,'qinMing','秦明','男','1996-12-12','15036987523','秦庄','1234'),(14,'liuZhaoHui','刘照辉','男','2000-01-11','15789631475','登封市','No'),(15,'韩春明','韩春明','男','2022-05-18','18536971238','小清河','爱收藏'),(16,'苏萌','苏萌','女','2022-05-21','18439411318',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

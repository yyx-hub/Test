/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : hrm

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-04-08 15:08:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `dept_inf`
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES ('1', '技术部', '技术部');
INSERT INTO `dept_inf` VALUES ('2', '运营部', '运营部');
INSERT INTO `dept_inf` VALUES ('3', '吃饭部', '吃大米饭');
INSERT INTO `dept_inf` VALUES ('4', '学习部', '学个屁的习');
INSERT INTO `dept_inf` VALUES ('5', '总公办', '总公办');
INSERT INTO `dept_inf` VALUES ('6', '搞笑部', '笑死个人啦');
INSERT INTO `dept_inf` VALUES ('7', '手动阀', '啊发射点发');
INSERT INTO `dept_inf` VALUES ('8', '但是v', '哦i了');
INSERT INTO `dept_inf` VALUES ('12', '地方干部', '微软');
INSERT INTO `dept_inf` VALUES ('13', '的发发发发撒打发', '发士大夫');

-- ----------------------------
-- Table structure for `document_inf`
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL,
  `filename` varchar(300) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DOCUMENT_USER` (`USER_ID`),
  CONSTRAINT `document_inf_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document_inf
-- ----------------------------
INSERT INTO `document_inf` VALUES ('2', '发士', '更好', '是的哇', '2020-03-27 12:15:17', '1');
INSERT INTO `document_inf` VALUES ('3', '肉体和', '说的给', '毛概', '2020-03-27 12:15:27', '1');
INSERT INTO `document_inf` VALUES ('4', '撒打发', '哈哈机', '接口', '2020-03-27 12:15:37', '1');
INSERT INTO `document_inf` VALUES ('5', '带闹闹', '没地方', '曹张新村发', '2020-03-27 12:15:49', '1');
INSERT INTO `document_inf` VALUES ('6', '制定法规', '枯竭', '学生', '2020-03-27 12:16:01', '1');
INSERT INTO `document_inf` VALUES ('7', '这小子', '挂号费', '没把你', '2020-03-27 12:16:13', '1');
INSERT INTO `document_inf` VALUES ('8', '哈哈哈哈', '1585459940608-19fe0005d88295e618ff.jpg', '获得丰厚', '2020-03-29 13:32:20', '1');
INSERT INTO `document_inf` VALUES ('9', '洛洛落', '1585462112123-63ac0b6aa3304722bc36a2c060662f78.jpeg', '红红火火恍恍惚惚得到', '2020-03-29 14:08:32', '1');
INSERT INTO `document_inf` VALUES ('10', '拥有余', '1585462143673-4439_1.jpg', '不不不', '2020-03-29 14:09:03', '1');
INSERT INTO `document_inf` VALUES ('11', '钱钱钱', '1585886739930-01300001236578131599037501662.jpg', '发的都是大师傅似的', '2020-04-03 12:05:39', '1');

-- ----------------------------
-- Table structure for `employee_inf`
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `CARD_ID` varchar(18) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `POST_CODE` varchar(50) DEFAULT NULL,
  `TEL` varchar(16) DEFAULT NULL,
  `PHONE` varchar(11) NOT NULL,
  `QQ_NUM` varchar(10) DEFAULT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `SEX` int(11) NOT NULL DEFAULT '1',
  `PARTY` varchar(10) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `RACE` varchar(100) DEFAULT NULL,
  `EDUCATION` varchar(10) DEFAULT NULL,
  `SPECIALITY` varchar(20) DEFAULT NULL,
  `HOBBY` varchar(100) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  CONSTRAINT `employee_inf_ibfk_1` FOREIGN KEY (`DEPT_ID`) REFERENCES `dept_inf` (`ID`),
  CONSTRAINT `employee_inf_ibfk_2` FOREIGN KEY (`JOB_ID`) REFERENCES `job_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES ('1', '1', '8', '爱丽丝啊', '411524199910403337', '广州天河', '510000', '020-77777777', '13902001111', '36750066', '251425887@qq.com', '1', '党员', '1980-01-01 00:00:00', '满', '本科', '美声', '唱歌', '四大天王', '2016-03-14 11:35:18');
INSERT INTO `employee_inf` VALUES ('2', '2', '1', '杰克', '22623', '43234', '42427424', '42242', '4247242', '42424', '251425887@qq.com', '2', null, null, null, null, null, null, null, '2016-03-14 11:35:18');
INSERT INTO `employee_inf` VALUES ('3', '1', '7', 'bba', '432801197711251038', '广州', '510000', '020-99999999', '13907351532', '36750064', '36750064@qq.com', '2', '党员', '2020-03-12 00:00:00', '汉', '本科', '计算机', '爬山', '无', '2016-07-14 09:54:52');
INSERT INTO `employee_inf` VALUES ('4', '1', '8', '噶山豆', '23412341234', '好地方', '1433432', '423421425324', '4234234', '4564567578', '345234524@qq.com', '1', null, null, null, null, null, null, null, '2020-03-21 16:07:10');

-- ----------------------------
-- Table structure for `job_inf`
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_inf
-- ----------------------------
INSERT INTO `job_inf` VALUES ('1', '职员他', '职员');
INSERT INTO `job_inf` VALUES ('2', 'Java开发工程师', 'Java开发工程师');
INSERT INTO `job_inf` VALUES ('3', 'Java中级开发工程师', 'Java中级开发工程师');
INSERT INTO `job_inf` VALUES ('4', 'Java高级开发工程师', 'Java高级开发工程师');
INSERT INTO `job_inf` VALUES ('5', '系统管理员', '系统管理员');
INSERT INTO `job_inf` VALUES ('6', '架构师', '架构师');
INSERT INTO `job_inf` VALUES ('7', '主管', '主管');
INSERT INTO `job_inf` VALUES ('8', '经理', '经理');
INSERT INTO `job_inf` VALUES ('10', '芬撒旦', '说的');
INSERT INTO `job_inf` VALUES ('11', '撒旦v', '突然就那天');
INSERT INTO `job_inf` VALUES ('12', '人非人', '挂红让他');
INSERT INTO `job_inf` VALUES ('13', '违法', '为');
INSERT INTO `job_inf` VALUES ('14', '口欧美', '哭解开纽扣');
INSERT INTO `job_inf` VALUES ('15', '内部空间', '哦就好了');
INSERT INTO `job_inf` VALUES ('16', '市场部', '市场部');
INSERT INTO `job_inf` VALUES ('17', '的分公司的', '正式的工作');
INSERT INTO `job_inf` VALUES ('20', '啊给', '给');

-- ----------------------------
-- Table structure for `notice_inf`
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL,
  `CONTENT` text NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_NOTICE_USER` (`USER_ID`),
  CONSTRAINT `notice_inf_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice_inf
-- ----------------------------
INSERT INTO `notice_inf` VALUES ('1', '公告1', '疫情严重，在家呆着', '2020-02-29 14:54:34', '3');
INSERT INTO `notice_inf` VALUES ('2', '啊斯', '阿斯蒂芬', '2020-03-23 20:45:11', null);
INSERT INTO `notice_inf` VALUES ('3', '收到v', '阿萨v顺丰大概', '2020-03-23 20:45:17', null);
INSERT INTO `notice_inf` VALUES ('4', '说的啊斯', '郭德纲回复', '2020-03-23 20:45:24', null);
INSERT INTO `notice_inf` VALUES ('5', '发v第三方', '尽快更换', '2020-03-23 20:45:31', null);
INSERT INTO `notice_inf` VALUES ('6', '那个家伙吗', '中心城中心十大擦速度擦速度擦速度擦擦', '2020-03-23 20:45:39', null);
INSERT INTO `notice_inf` VALUES ('8', '打赏的撒', '反对士大夫但是', '2020-03-24 16:56:45', '1');
INSERT INTO `notice_inf` VALUES ('9', 'r\'y\'r\'t\'y\'n', 'ryrtyn', '2020-03-24 16:57:55', '1');
INSERT INTO `notice_inf` VALUES ('10', '回到法国', '合同已经', '2020-03-24 16:58:03', '1');
INSERT INTO `notice_inf` VALUES ('11', '加入天涯', '购买计划冷酷一', '2020-03-24 16:58:21', '1');

-- ----------------------------
-- Table structure for `user_inf`
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) NOT NULL,
  `PASSWORD` varchar(16) NOT NULL,
  `STATUS` int(11) NOT NULL DEFAULT '1',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES ('1', 'admin', '123456', '2', '2016-03-12 09:34:28', '超级管理员');
INSERT INTO `user_inf` VALUES ('2', 'aaaaaa', '111111', '1', '2020-02-28 17:49:15', '张三');
INSERT INTO `user_inf` VALUES ('3', 'bbbb', '222222', '1', '2020-02-28 17:49:36', '张三1');
INSERT INTO `user_inf` VALUES ('4', 'cccc', '333333', '1', '2020-02-28 17:49:52', '张大三');
INSERT INTO `user_inf` VALUES ('5', 'lisi', '123456', '1', '2020-03-03 16:03:06', '李四');
INSERT INTO `user_inf` VALUES ('6', '我', '13413', '2', '2020-03-21 17:54:42', '威威');

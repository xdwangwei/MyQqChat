/*
Navicat MySQL Data Transfer

Source Server         : 连接mysql
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : chat

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-07-08 17:53:00
*/
DROP DATABASE IF EXISTS chat;
CREATE DATABASE chat;
USE chat;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_friends`
-- ----------------------------
DROP TABLE IF EXISTS `t_friends`;
CREATE TABLE `t_friends` (
  `num` int(3) NOT NULL AUTO_INCREMENT,
  `mqq` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fqq` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_friends
-- ----------------------------
INSERT INTO `t_friends` VALUES ('1', '10084', '10083', '张三');
INSERT INTO `t_friends` VALUES ('2', '10083', '10084', '李四');
INSERT INTO `t_friends` VALUES ('3', '10083', '10085', '王五');
INSERT INTO `t_friends` VALUES ('4', '10084', '10085', '王五');
INSERT INTO `t_friends` VALUES ('5', '10083', '10086', '赵六');
INSERT INTO `t_friends` VALUES ('6', '10083', '10087', '小七');
INSERT INTO `t_friends` VALUES ('7', '10083', '10088', '小八');
INSERT INTO `t_friends` VALUES ('8', '10084', '10086', '赵六');
INSERT INTO `t_friends` VALUES ('9', '10084', '10087', '小七');
INSERT INTO `t_friends` VALUES ('10', '10084', '10088', '小八');
INSERT INTO `t_friends` VALUES ('11', '10085', '10083', '张三');
INSERT INTO `t_friends` VALUES ('12', '10085', '10084', '李四');
INSERT INTO `t_friends` VALUES ('13', '10085', '10086', '赵六');
INSERT INTO `t_friends` VALUES ('14', '10085', '10087', '小七');
INSERT INTO `t_friends` VALUES ('15', '10085', '10088', '小八');
INSERT INTO `t_friends` VALUES ('16', '10086', '10083', '张三');
INSERT INTO `t_friends` VALUES ('17', '10086', '10084', '李四');
INSERT INTO `t_friends` VALUES ('18', '10086', '10085', '王五');
INSERT INTO `t_friends` VALUES ('19', '10086', '10087', '小七');
INSERT INTO `t_friends` VALUES ('20', '10086', '10088', '小八');
INSERT INTO `t_friends` VALUES ('21', '10087', '10083', '张三');
INSERT INTO `t_friends` VALUES ('22', '10087', '10084', '李四');
INSERT INTO `t_friends` VALUES ('23', '10087', '10085', '王五');
INSERT INTO `t_friends` VALUES ('24', '10087', '10086', '赵六');
INSERT INTO `t_friends` VALUES ('25', '10087', '10088', '小八');
INSERT INTO `t_friends` VALUES ('26', '10088', '10083', '张三');
INSERT INTO `t_friends` VALUES ('27', '10088', '10084', '李四');
INSERT INTO `t_friends` VALUES ('28', '10088', '10085', '王五');
INSERT INTO `t_friends` VALUES ('29', '10088', '10086', '赵六');
INSERT INTO `t_friends` VALUES ('30', '10088', '10087', '小七');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(10) NOT NULL,
  `uname` varchar(10) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('10083', '张三', '333333');
INSERT INTO `t_user` VALUES ('10084', '李四', '444444');
INSERT INTO `t_user` VALUES ('10085', '王五', '555555');
INSERT INTO `t_user` VALUES ('10086', '赵六', '666666');
INSERT INTO `t_user` VALUES ('10087', '小七', '777777');
INSERT INTO `t_user` VALUES ('10088', '小八', '888888');

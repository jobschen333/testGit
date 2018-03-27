/*
 Navicat Premium Data Transfer

 Source Server         : tzhzu
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : tzhzu

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 03/23/2018 13:55:42 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `advertisement_table`
-- ----------------------------
DROP TABLE IF EXISTS `advertisement_table`;
CREATE TABLE `advertisement_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `surfacePlotAdress` varchar(255) NOT NULL COMMENT '封面图地址',
  `webUrl` varchar(255) DEFAULT NULL COMMENT '网页链接',
  `wapUrl` varchar(255) DEFAULT NULL COMMENT 'wap链接',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `sts` varchar(50) NOT NULL COMMENT '假删除标志',
  `surfacePlotName` varchar(255) NOT NULL COMMENT '封面图名臣',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

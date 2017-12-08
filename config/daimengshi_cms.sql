/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50542
 Source Host           : localhost:3306
 Source Schema         : daimengshi_cms

 Target Server Type    : MySQL
 Target Server Version : 50542
 File Encoding         : 65001

 Date: 08/12/2017 14:34:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dms_article
-- ----------------------------
DROP TABLE IF EXISTS `dms_article`;
CREATE TABLE `dms_article` (
  `id` varchar(36) NOT NULL COMMENT 'ID',
  `article_title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `article_content` varchar(255) DEFAULT NULL COMMENT '文章内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of dms_article
-- ----------------------------
BEGIN;
INSERT INTO `dms_article` VALUES ('1\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0', '22', '22', NULL);
COMMIT;

-- ----------------------------
-- Table structure for dms_menu
-- ----------------------------
DROP TABLE IF EXISTS `dms_menu`;
CREATE TABLE `dms_menu` (
  `id` varchar(36) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `super_id` varchar(36) DEFAULT '' COMMENT '父级菜单 ID',
  `type_id` varchar(36) DEFAULT NULL COMMENT '菜单类型ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 菜单表';

-- ----------------------------
-- Records of dms_menu
-- ----------------------------
BEGIN;
INSERT INTO `dms_menu` VALUES ('', '去', NULL, NULL, '', NULL);
INSERT INTO `dms_menu` VALUES ('73623e69fecd4565a5ddb785b45769a4', '仪表盘', '/admin', '2017-12-06 18:10:40', '', '1');
INSERT INTO `dms_menu` VALUES ('854a4a0a155144ed94162efd8b0e3d14', '菜单管理', '/admin/menu', '2017-12-06 17:49:53', '', '1');
INSERT INTO `dms_menu` VALUES ('AAS', '是', NULL, '2016-12-07 18:04:03', '', NULL);
INSERT INTO `dms_menu` VALUES ('ASDA', 'A', 'A', '2015-12-07 18:04:03', '', NULL);
INSERT INTO `dms_menu` VALUES ('ASDFSADF', 'Trey', NULL, '2011-12-06 17:49:53', '', NULL);
INSERT INTO `dms_menu` VALUES ('ASDFSADFSADF', '梵蒂冈好', NULL, '2012-12-06 17:49:53', '', NULL);
INSERT INTO `dms_menu` VALUES ('ASDFSDFSADF', '大购物', NULL, '2013-12-06 17:49:53', '', NULL);
INSERT INTO `dms_menu` VALUES ('AWD', 'S', 'S', '2016-12-07 18:04:03', '', NULL);
INSERT INTO `dms_menu` VALUES ('QQWEQWE', '企鹅', NULL, '2017-12-06 17:49:53', '', NULL);
INSERT INTO `dms_menu` VALUES ('RFEGDFSG', '个', NULL, '2012-12-06 17:49:53', '', NULL);
INSERT INTO `dms_menu` VALUES ('ZXCZXC', '发', NULL, '2017-12-06 17:49:53', '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for dms_menu_type
-- ----------------------------
DROP TABLE IF EXISTS `dms_menu_type`;
CREATE TABLE `dms_menu_type` (
  `id` varchar(36) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单类型名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 菜单类型表';

-- ----------------------------
-- Records of dms_menu_type
-- ----------------------------
BEGIN;
INSERT INTO `dms_menu_type` VALUES ('1', '左边菜单', '2017-12-06 18:06:32');
COMMIT;

-- ----------------------------
-- Table structure for dms_permission
-- ----------------------------
DROP TABLE IF EXISTS `dms_permission`;
CREATE TABLE `dms_permission` (
  `id` varchar(36) NOT NULL,
  `mid` varchar(36) DEFAULT NULL COMMENT '菜单 ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of dms_permission
-- ----------------------------
BEGIN;
INSERT INTO `dms_permission` VALUES ('1', '/admin', '2017-12-06 17:21:38');
COMMIT;

-- ----------------------------
-- Table structure for dms_role
-- ----------------------------
DROP TABLE IF EXISTS `dms_role`;
CREATE TABLE `dms_role` (
  `id` varchar(36) NOT NULL,
  `name` varchar(36) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(36) DEFAULT NULL COMMENT '角色类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `describe` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of dms_role
-- ----------------------------
BEGIN;
INSERT INTO `dms_role` VALUES ('1000', '超级管理员', '1', '2017-12-06 17:15:48', '最高权限的管理员');
COMMIT;

-- ----------------------------
-- Table structure for dms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `dms_role_permission`;
CREATE TABLE `dms_role_permission` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '主键',
  `rid` varchar(36) DEFAULT NULL COMMENT '角色ID',
  `pid` varchar(36) DEFAULT NULL COMMENT '权限ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of dms_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `dms_role_permission` VALUES ('1', '1000', '1', '2017-12-06 17:22:01');
COMMIT;

-- ----------------------------
-- Table structure for dms_user
-- ----------------------------
DROP TABLE IF EXISTS `dms_user`;
CREATE TABLE `dms_user` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(16) DEFAULT NULL COMMENT '密码',
  `accounts` varchar(36) DEFAULT NULL COMMENT '帐号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of dms_user
-- ----------------------------
BEGIN;
INSERT INTO `dms_user` VALUES ('001', 'zhoufeng', 111, '2017-12-06 16:54:02', '18507838388', '123456', 'zhoufengjob');
COMMIT;

-- ----------------------------
-- Table structure for dms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `dms_user_role`;
CREATE TABLE `dms_user_role` (
  `uid` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `rid` varchar(36) DEFAULT NULL COMMENT '角色ID',
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of dms_user_role
-- ----------------------------
BEGIN;
INSERT INTO `dms_user_role` VALUES ('cb113f27f8e54ab195aaf6c3d06c387d', NULL, '', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

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

 Date: 16/12/2017 00:06:56
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
  `uid` varchar(36) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of dms_article
-- ----------------------------
BEGIN;
INSERT INTO `dms_article` VALUES ('1\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0', '22', '22', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for dms_config
-- ----------------------------
DROP TABLE IF EXISTS `dms_config`;
CREATE TABLE `dms_config` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT '' COMMENT '配置名称',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `desc` varchar(1000) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT=' 配置表';

-- ----------------------------
-- Records of dms_config
-- ----------------------------
BEGIN;
INSERT INTO `dms_config` VALUES (1, 'webAppName', '呆萌狮', '2017-12-13 10:35:48', '站点名称');
INSERT INTO `dms_config` VALUES (2, 'webAppUrl', 'http://127.0.0.1', '2017-12-13 10:35:46', '站点链接地址');
COMMIT;

-- ----------------------------
-- Table structure for dms_menu
-- ----------------------------
DROP TABLE IF EXISTS `dms_menu`;
CREATE TABLE `dms_menu` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `super_id` int(36) DEFAULT '0' COMMENT '父级菜单 ID',
  `type_id` varchar(36) DEFAULT NULL COMMENT '菜单类型ID',
  `is_open` varchar(4) DEFAULT NULL COMMENT '是否开启(on:开启,off关闭) 默认on开启',
  `desc` varchar(1000) DEFAULT NULL COMMENT '备注说明',
  `serial_num` int(20) DEFAULT '0' COMMENT '序号,用于排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT=' 菜单表';

-- ----------------------------
-- Records of dms_menu
-- ----------------------------
BEGIN;
INSERT INTO `dms_menu` VALUES (1, '菜单管理', '/admin/menu', '2017-12-09 01:48:50', 2, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (2, '系统管理', '', '2017-12-09 03:52:33', 0, '1', 'on', '', 2);
INSERT INTO `dms_menu` VALUES (3, '用户管理', '/admin/user', '2017-12-09 04:02:46', 2, '1', 'on', '用户管理', 0);
INSERT INTO `dms_menu` VALUES (4, '文章管理', '', '2017-12-11 17:12:08', 5, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (5, '内容管理', '', '2017-12-11 17:10:19', 0, '1', 'on', '', 1111);
INSERT INTO `dms_menu` VALUES (6, '仪表盘', '/admin/main', '2017-12-11 16:03:04', 2, '1', 'on', '后台初始化页面', 0);
INSERT INTO `dms_menu` VALUES (7, '管理员管理', '', '2017-12-14 10:20:31', 0, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (8, '管理员', '', '2017-12-14 10:21:08', 7, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (9, '角色管理', '/admin/role', '2017-12-14 10:22:05', 7, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (12, '会员管理', '', '2017-12-14 11:13:58', 0, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (13, '会员列表', '/admin/user', '2017-12-14 11:14:17', 12, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (15, '等级管理', '', '2017-12-14 11:19:36', 12, '1', 'off', '', 0);
INSERT INTO `dms_menu` VALUES (16, '权限管理', '', '2017-12-16 00:01:41', 7, '1', 'on', '', 0);
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
INSERT INTO `dms_menu_type` VALUES ('2', '顶部菜单', '2017-12-09 01:08:55');
COMMIT;

-- ----------------------------
-- Table structure for dms_permission
-- ----------------------------
DROP TABLE IF EXISTS `dms_permission`;
CREATE TABLE `dms_permission` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `key` varchar(36) DEFAULT NULL COMMENT '权限标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of dms_permission
-- ----------------------------
BEGIN;
INSERT INTO `dms_permission` VALUES (1, 'admin:update', '2017-12-14 23:23:12');
INSERT INTO `dms_permission` VALUES (2, 'admin:create', '2017-12-06 17:21:38');
INSERT INTO `dms_permission` VALUES (3, 'admin:delete', '2017-12-14 23:23:32');
INSERT INTO `dms_permission` VALUES (4, 'admin:view', '2017-12-14 23:23:42');
INSERT INTO `dms_permission` VALUES (5, 'user:view', '2017-12-14 23:31:39');
COMMIT;

-- ----------------------------
-- Table structure for dms_role
-- ----------------------------
DROP TABLE IF EXISTS `dms_role`;
CREATE TABLE `dms_role` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(36) DEFAULT NULL COMMENT '角色类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `describe` varchar(200) DEFAULT NULL COMMENT '描述',
  `key` varchar(36) DEFAULT NULL COMMENT '角色标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of dms_role
-- ----------------------------
BEGIN;
INSERT INTO `dms_role` VALUES (1, '超级管理员', '1', '2017-12-06 17:15:48', '最高权限的管理员', 'admin');
INSERT INTO `dms_role` VALUES (2, '会员', '1', '2017-12-15 23:14:16', '普通会员', 'user');
COMMIT;

-- ----------------------------
-- Table structure for dms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `dms_role_permission`;
CREATE TABLE `dms_role_permission` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` int(36) DEFAULT NULL COMMENT '角色ID',
  `pid` int(36) DEFAULT NULL COMMENT '权限ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of dms_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `dms_role_permission` VALUES (1, 1, 1, '2017-12-06 17:22:01');
INSERT INTO `dms_role_permission` VALUES (2, 1, 2, '2017-12-14 23:26:15');
INSERT INTO `dms_role_permission` VALUES (3, 1, 3, '2017-12-14 23:26:11');
INSERT INTO `dms_role_permission` VALUES (4, 1, 4, '2017-12-14 23:26:07');
INSERT INTO `dms_role_permission` VALUES (5, 1, 5, '2017-12-15 00:57:19');
COMMIT;

-- ----------------------------
-- Table structure for dms_session
-- ----------------------------
DROP TABLE IF EXISTS `dms_session`;
CREATE TABLE `dms_session` (
  `id` varchar(36) NOT NULL COMMENT 'ID',
  `session` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `desc` varchar(1000) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 会话表';

-- ----------------------------
-- Records of dms_session
-- ----------------------------
BEGIN;
INSERT INTO `dms_session` VALUES ('c05e9cec-50a2-4caa-b70b-35eb0f7cb69b', '{\"attributeKeys\":[],\"valid\":true}', '2017-12-15 15:45:50', NULL);
INSERT INTO `dms_session` VALUES ('ca792f53-2fab-4b9a-90f7-a3738c009d98', '{\"attributeKeys\":[],\"valid\":true}', '2017-12-15 15:45:51', NULL);
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
  `account` varchar(36) DEFAULT NULL COMMENT '帐号',
  `nike_name` varchar(36) DEFAULT NULL COMMENT '昵称',
  `point` int(36) DEFAULT NULL COMMENT '积分',
  `email` varchar(36) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of dms_user
-- ----------------------------
BEGIN;
INSERT INTO `dms_user` VALUES ('1001', 'zhoufeng', 111, '2017-12-06 16:54:02', '18507838388', 'admin', 'admin', '周洛熙', 1000, 'zhoufengjob@sina.com');
COMMIT;

-- ----------------------------
-- Table structure for dms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `dms_user_role`;
CREATE TABLE `dms_user_role` (
  `uid` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `rid` int(36) DEFAULT NULL COMMENT '角色ID',
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of dms_user_role
-- ----------------------------
BEGIN;
INSERT INTO `dms_user_role` VALUES ('1001', 1, 1, '2017-12-14 23:27:23');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

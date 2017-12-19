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

 Date: 19/12/2017 11:10:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dms_article
-- ----------------------------
DROP TABLE IF EXISTS `dms_article`;
CREATE TABLE `dms_article` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `article_title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `article_content` varchar(255) DEFAULT NULL COMMENT '文章内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `uid` varchar(36) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of dms_article
-- ----------------------------
BEGIN;
INSERT INTO `dms_article` VALUES (1, '22', '22', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for dms_config
-- ----------------------------
DROP TABLE IF EXISTS `dms_config`;
CREATE TABLE `dms_config` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT=' 菜单表';

-- ----------------------------
-- Records of dms_menu
-- ----------------------------
BEGIN;
INSERT INTO `dms_menu` VALUES (1, '菜单管理', '/admin/menu', '2017-12-09 01:48:50', 2, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (2, '系统管理', NULL, '2017-12-09 03:52:33', 0, '1', 'on', '', 2);
INSERT INTO `dms_menu` VALUES (3, '用户管理', '/admin/user', '2017-12-09 04:02:46', 2, '1', 'on', '用户管理', 0);
INSERT INTO `dms_menu` VALUES (6, '仪表盘', '/admin/main', '2017-12-11 16:03:04', 2, '1', 'on', '后台初始化页面', 0);
INSERT INTO `dms_menu` VALUES (7, '管理员管理', NULL, '2017-12-14 10:20:31', 0, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (8, '管理员', '/admin/master', '2017-12-14 10:21:08', 7, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (9, '角色管理', '/admin/role', '2017-12-14 10:22:05', 7, '1', 'on', '', 0);
INSERT INTO `dms_menu` VALUES (16, '权限管理', '/admin/permission', '2017-12-16 00:01:41', 7, '1', 'on', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for dms_menu_type
-- ----------------------------
DROP TABLE IF EXISTS `dms_menu_type`;
CREATE TABLE `dms_menu_type` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单类型名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT=' 菜单类型表';

-- ----------------------------
-- Records of dms_menu_type
-- ----------------------------
BEGIN;
INSERT INTO `dms_menu_type` VALUES (1, '左边菜单', '2017-12-06 18:06:32');
INSERT INTO `dms_menu_type` VALUES (2, '顶部菜单', '2017-12-09 01:08:55');
COMMIT;

-- ----------------------------
-- Table structure for dms_permission
-- ----------------------------
DROP TABLE IF EXISTS `dms_permission`;
CREATE TABLE `dms_permission` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `key` varchar(36) DEFAULT NULL COMMENT '权限标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(36) DEFAULT NULL COMMENT '权限名称',
  `desc` varchar(255) DEFAULT NULL COMMENT '备注说明',
  `is_open` varchar(4) DEFAULT 'on' COMMENT '是否开启(on:开启,off关闭) 默认on开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of dms_permission
-- ----------------------------
BEGIN;
INSERT INTO `dms_permission` VALUES (1, 'admin:update', '2017-12-14 23:23:12', '管理员-更新', '', 'on');
INSERT INTO `dms_permission` VALUES (2, 'admin:create', '2017-12-06 17:21:38', '管理员-创建', '', 'on');
INSERT INTO `dms_permission` VALUES (3, 'admin:delete', '2017-12-14 23:23:32', '管理员-删除', '', 'on');
INSERT INTO `dms_permission` VALUES (4, 'admin:view', '2017-12-14 23:23:42', '管理员-查询', '', 'on');
INSERT INTO `dms_permission` VALUES (5, 'user:view', '2017-12-14 23:31:39', '用户-查询', '', 'on');
INSERT INTO `dms_permission` VALUES (11, 'user:update', '2017-12-19 11:07:09', '用户-更新', '', 'on');
INSERT INTO `dms_permission` VALUES (12, 'user:delete', '2017-12-19 11:07:50', '用户-删除', '', 'on');
INSERT INTO `dms_permission` VALUES (13, 'user:create', '2017-12-19 11:08:14', '用户-创建', '', 'on');
COMMIT;

-- ----------------------------
-- Table structure for dms_role
-- ----------------------------
DROP TABLE IF EXISTS `dms_role`;
CREATE TABLE `dms_role` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `key` varchar(36) DEFAULT NULL COMMENT '角色标识',
  `is_open` varchar(4) DEFAULT 'on' COMMENT '是否开启(on:开启,off关闭) 默认on开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of dms_role
-- ----------------------------
BEGIN;
INSERT INTO `dms_role` VALUES (1, '超级管理员', '2017-12-06 17:15:48', '最高权限的管理员22333', 'admin', 'on');
INSERT INTO `dms_role` VALUES (2, '普通用户', '2017-12-15 23:14:16', '普通会员1', 'user', 'on');
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
  `is_open` varchar(4) DEFAULT 'on' COMMENT '是否开启(on:开启,off关闭) 默认on开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of dms_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `dms_role_permission` VALUES (19, 5, 1, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (20, 5, 2, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (21, 5, 3, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (22, 5, 4, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (23, 5, 5, '2017-12-19 11:03:04', 'on');
INSERT INTO `dms_role_permission` VALUES (24, 6, 5, '2017-12-19 11:05:29', 'on');
INSERT INTO `dms_role_permission` VALUES (25, 2, 5, '2017-12-19 11:08:33', 'on');
INSERT INTO `dms_role_permission` VALUES (26, 2, 11, '2017-12-19 11:08:33', 'on');
INSERT INTO `dms_role_permission` VALUES (27, 2, 12, '2017-12-19 11:08:33', 'on');
INSERT INTO `dms_role_permission` VALUES (28, 2, 13, '2017-12-19 11:08:33', 'on');
INSERT INTO `dms_role_permission` VALUES (29, 1, 1, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (30, 1, 2, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (31, 1, 3, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (32, 1, 4, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (33, 1, 5, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (34, 1, 11, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (35, 1, 12, '2017-12-19 11:08:41', 'on');
INSERT INTO `dms_role_permission` VALUES (36, 1, 13, '2017-12-19 11:08:41', 'on');
COMMIT;

-- ----------------------------
-- Table structure for dms_user
-- ----------------------------
DROP TABLE IF EXISTS `dms_user`;
CREATE TABLE `dms_user` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `name` varchar(36) DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(16) DEFAULT NULL COMMENT '密码',
  `nike_name` varchar(36) DEFAULT NULL COMMENT '昵称',
  `point` int(36) unsigned DEFAULT '0' COMMENT '积分',
  `email` varchar(36) DEFAULT NULL COMMENT '邮箱',
  `is_open` varchar(4) DEFAULT 'on' COMMENT '是否启用',
  `desc` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of dms_user
-- ----------------------------
BEGIN;
INSERT INTO `dms_user` VALUES ('5ff5b3f5d94c44b3a2366b6e21cfcbfe', 'admin', '2017-12-16 17:08:58', '123123', 'admin', '周洛熙', 0, 'zhoufengjob@sina.com', 'on', '超级管理员');
INSERT INTO `dms_user` VALUES ('9ba98abd6bbf4f3f8776257cf369af6c', 'DDDDD', '2017-12-19 00:05:12', '123', 'DDD', 'DDD', 111, 'DDD', 'on', '');
INSERT INTO `dms_user` VALUES ('c2abb2efa8d24c28b91b392b1dabd6b5', 'SSSS', '2017-12-19 00:05:36', '12', 'SSS', 'SS', 12, 'SS', 'on', '');
INSERT INTO `dms_user` VALUES ('f569091266db4240ab0b28bbb5dba291', 'aaaa', '2017-12-16 18:21:01', '321321', 'ssa', 'aaaa', 123, 'asdas', 'off', '');
COMMIT;

-- ----------------------------
-- Table structure for dms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `dms_user_role`;
CREATE TABLE `dms_user_role` (
  `id` int(36) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` int(36) DEFAULT NULL COMMENT '角色ID',
  `uid` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of dms_user_role
-- ----------------------------
BEGIN;
INSERT INTO `dms_user_role` VALUES (2, 1, '5ff5b3f5d94c44b3a2366b6e21cfcbfe', '2017-12-16 17:09:18');
INSERT INTO `dms_user_role` VALUES (4, 1, 'f569091266db4240ab0b28bbb5dba291', '2017-12-19 00:00:39');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

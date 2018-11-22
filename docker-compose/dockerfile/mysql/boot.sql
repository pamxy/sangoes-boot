/*
 Navicat Premium Data Transfer

 Source Server         : sangoes@localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 22/11/2018 09:35:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL COMMENT '客户端id',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '客户端对应secret',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '资源ids',
  `scope` varchar(256) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '授权',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT 'redirect url',
  `authorities` varchar(256) DEFAULT NULL COMMENT 'authorities',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token_validity',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'access_token_validity',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT 'additional_information',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT 'autoapprove',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='授权表';

-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `menu_id` bigint(32) DEFAULT NULL COMMENT '菜单主键',
  `auth_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `auth_code` varchar(1000) DEFAULT NULL COMMENT '权限编码',
  `action` varchar(1000) DEFAULT NULL COMMENT '权限地址',
  `method` varchar(255) DEFAULT NULL COMMENT '请求方法',
  `status` smallint(1) DEFAULT '1' COMMENT '1启用 0禁用',
  `des` varchar(1000) DEFAULT NULL COMMENT '菜单描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
BEGIN;
INSERT INTO `sys_auth` VALUES (1061206366072623105, '2018-11-10 18:38:08', '2018-11-17 20:25:22', NULL, NULL, NULL, NULL, 1, 1060781327489757186, '添加用户', 'admin:user:add', '/api/admin/user/add', 'post', 1, '添加用户');
INSERT INTO `sys_auth` VALUES (1061464714533773314, '2018-11-11 11:44:43', '2018-11-17 20:25:24', NULL, NULL, NULL, NULL, 1, 1060781327489757186, '用户分页查询', 'admin:user:page', '/api/admin/user/page', 'get', 1, '用户分页查询');
INSERT INTO `sys_auth` VALUES (1061465433659777025, '2018-11-11 11:47:35', '2018-11-17 20:25:26', NULL, NULL, NULL, NULL, 1, 1060781599209353218, '添加角色', 'admin:role:add', '/api/admin/role/add', 'post', 1, '添加角色');
INSERT INTO `sys_auth` VALUES (1061465784349728770, '2018-11-11 11:48:58', '2018-11-17 20:25:29', NULL, NULL, NULL, NULL, 1, 1060781599209353218, '分页查询角色', 'admin:role:page', '/api/admin/role/page', 'get', 1, '分页查询角色');
INSERT INTO `sys_auth` VALUES (1061466365038534657, '2018-11-11 11:51:17', '2018-11-17 20:25:31', NULL, NULL, NULL, NULL, 1, 1060903467811659778, '添加菜单', 'admin:menu:add', '/api/admin/menu/add', 'post', 1, '添加菜单');
INSERT INTO `sys_auth` VALUES (1063778112642555905, '2018-11-17 20:57:20', '2018-11-17 20:57:20', NULL, NULL, NULL, NULL, 1, 1060903467811659778, '添加权限', 'admin:auth:add', '/api/admin/auth/add', 'post', 1, '添加权限');
INSERT INTO `sys_auth` VALUES (1063778317861462018, '2018-11-17 20:58:09', '2018-11-17 20:58:09', NULL, NULL, NULL, NULL, 1, 1060903467811659778, '权限分页', 'admin:auth:page', '/api/admin/auth/page', 'get', 1, '权限分页');
INSERT INTO `sys_auth` VALUES (1063778625048092673, '2018-11-17 20:59:22', '2018-11-17 20:59:22', NULL, NULL, NULL, NULL, 1, 1060903467811659778, '获取菜单列表', 'admin:menu:list', '/api/admin/menu/list', 'get', 1, '获取菜单列表');
INSERT INTO `sys_auth` VALUES (1063779805576896514, '2018-11-17 21:04:04', '2018-11-17 21:04:04', NULL, NULL, NULL, NULL, 1, 1060903467811659778, '获取菜单树形结果', 'admin:menu:tree', '/api/admin/menu/tree', 'get', 1, '获取菜单树形结果');
INSERT INTO `sys_auth` VALUES (1063780413876805633, '2018-11-17 21:06:29', '2018-11-17 21:06:29', NULL, NULL, NULL, NULL, 1, 1060781599209353218, '查询绑定权限', 'admin:role:bind:info', '/api/admin/role/bind/auth/info/**', 'get', 1, '查询绑定权限');
INSERT INTO `sys_auth` VALUES (1063781168838942722, '2018-11-17 21:09:29', '2018-11-17 21:09:29', NULL, NULL, NULL, NULL, 1, 1060781599209353218, '绑定菜单权限', 'admin:role:bind:menu', '/api/admin/role/bind/menu', 'post', 1, '绑定菜单权限');
INSERT INTO `sys_auth` VALUES (1063781694515257346, '2018-11-17 21:11:34', '2018-11-17 21:11:34', NULL, NULL, NULL, NULL, 1, 1060781599209353218, '查询绑定菜单', 'admin:role:bind:menu:info', '/api/admin/role/bind/menu/info/*', 'get', 1, '查询绑定菜单');
INSERT INTO `sys_auth` VALUES (1063782150784229378, '2018-11-17 21:13:23', '2018-11-17 21:13:23', NULL, NULL, NULL, NULL, 1, 1060781327489757186, '绑定用户', 'admin:user:bind:role', '/api/admin/user/bind/role', 'post', 1, '绑定用户');
INSERT INTO `sys_auth` VALUES (1063782508084404225, '2018-11-17 21:14:48', '2018-11-17 21:14:48', NULL, NULL, NULL, NULL, 1, 1060781327489757186, '查询绑定角色', 'admin:user:bind:role:info', '/api/admin/user/bind/role/info/*', 'get', 1, '查询绑定角色');
INSERT INTO `sys_auth` VALUES (1063782794035273729, '2018-11-17 21:15:56', '2018-11-17 21:15:56', NULL, NULL, NULL, NULL, 1, 1060781327489757186, '手机号码注册', 'admin:user:signup', '/api/admin/user/signup', 'post', 1, '手机号码注册');
INSERT INTO `sys_auth` VALUES (1064117361699004418, '2018-11-18 19:25:24', '2018-11-18 19:25:24', NULL, NULL, NULL, NULL, 1, 1060903467811659778, '获取当前用户的菜单树形结果', 'admin:menu:user:tree', '/api/admin/menu/user/tree', 'get', 1, '获取当前用户的菜单树形结果');
INSERT INTO `sys_auth` VALUES (1064340058714058754, '2018-11-19 10:10:19', '2018-11-19 10:10:19', NULL, NULL, NULL, NULL, 1, 1060781327489757186, '获取当前用户信息', 'admin:user:info', '/api/admin/user/info', 'get', 1, '获取当前用户信息');
INSERT INTO `sys_auth` VALUES (1064395542594797569, '2018-11-19 13:50:47', '2018-11-19 13:50:47', NULL, NULL, NULL, NULL, 1, 1060781327489757186, '用户上传头像', 'admin:user:upload:avatar', '/api/admin/user/upload/avatar', 'post', 1, '用户上传头像');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `parent_id` bigint(32) DEFAULT '-1' COMMENT '父主键 默认-1',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  `url` varchar(2000) DEFAULT NULL COMMENT '权限菜单请求路径',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `status` smallint(1) DEFAULT '1' COMMENT '1启用 0禁用',
  `des` varchar(1000) DEFAULT NULL COMMENT '菜单描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1060780925302140929, '2018-11-09 14:27:35', '2018-11-17 15:27:16', NULL, NULL, NULL, NULL, 1, -1, '管理中心', 'mgtCenter', '', 'setting', 1, '用户管理 文档管理 角色管理 菜单管理 字典管理 日志管理 部门管理 等');
INSERT INTO `sys_menu` VALUES (1060781327489757186, '2018-11-09 14:29:11', '2018-11-17 15:27:25', NULL, NULL, NULL, NULL, 1, 1060780925302140929, '用户管理', 'userMgt', '/mgt/user', NULL, 1, '用户增删改查 分配角色');
INSERT INTO `sys_menu` VALUES (1060781599209353218, '2018-11-09 14:30:16', '2018-11-17 15:27:32', NULL, NULL, NULL, NULL, 1, 1060780925302140929, '角色管理', 'roleMgt', '/mgt/role', NULL, 1, '角色增删改查');
INSERT INTO `sys_menu` VALUES (1060903467811659778, '2018-11-09 22:34:32', '2018-11-17 15:27:41', NULL, NULL, NULL, NULL, 1, 1060780925302140929, '菜单管理', 'menuMgt', '/mgt/menu', NULL, 1, '菜单管理');
INSERT INTO `sys_menu` VALUES (1061157804223815681, '2018-11-10 15:25:10', '2018-11-10 15:25:10', NULL, NULL, NULL, NULL, 1, -1, '运维中心', 'opersCenter', NULL, 'dashboard', 1, '运维管理 注册中心');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) DEFAULT NULL COMMENT '角色编码',
  `des` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `status` smallint(1) DEFAULT '1' COMMENT '1启用 0禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1060546311132381185, '2018-11-08 22:55:19', '2018-11-08 22:55:19', NULL, NULL, NULL, NULL, 1, '管理员', 'admin', '管理员最高权限', 1);
INSERT INTO `sys_role` VALUES (1060546917704237057, '2018-11-08 22:57:43', '2018-11-08 22:57:43', NULL, NULL, NULL, NULL, 1, '开发', 'dev', '开发人员具有开发权限', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色主键',
  `auth_id` bigint(32) DEFAULT NULL COMMENT '权限主键',
  `menu_id` bigint(32) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限中间表';

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_auth` VALUES (1063987397850935298, '2018-11-18 10:48:58', '2018-11-18 10:48:58', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1061465433659777025, 1060781599209353218);
INSERT INTO `sys_role_auth` VALUES (1063987397855129602, '2018-11-18 10:48:58', '2018-11-18 10:48:58', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1061465784349728770, 1060781599209353218);
INSERT INTO `sys_role_auth` VALUES (1063987397863518209, '2018-11-18 10:48:58', '2018-11-18 10:48:58', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063780413876805633, 1060781599209353218);
INSERT INTO `sys_role_auth` VALUES (1063987397867712514, '2018-11-18 10:48:58', '2018-11-18 10:48:58', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063781168838942722, 1060781599209353218);
INSERT INTO `sys_role_auth` VALUES (1063987397871906818, '2018-11-18 10:48:58', '2018-11-18 10:48:58', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063781694515257346, 1060781599209353218);
INSERT INTO `sys_role_auth` VALUES (1064117424751976449, '2018-11-18 19:25:39', '2018-11-18 19:25:39', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1061466365038534657, 1060903467811659778);
INSERT INTO `sys_role_auth` VALUES (1064117424756170753, '2018-11-18 19:25:39', '2018-11-18 19:25:39', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063778112642555905, 1060903467811659778);
INSERT INTO `sys_role_auth` VALUES (1064117424764559361, '2018-11-18 19:25:39', '2018-11-18 19:25:39', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063778317861462018, 1060903467811659778);
INSERT INTO `sys_role_auth` VALUES (1064117424772947969, '2018-11-18 19:25:39', '2018-11-18 19:25:39', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063778625048092673, 1060903467811659778);
INSERT INTO `sys_role_auth` VALUES (1064117424781336578, '2018-11-18 19:25:39', '2018-11-18 19:25:39', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063779805576896514, 1060903467811659778);
INSERT INTO `sys_role_auth` VALUES (1064117424789725185, '2018-11-18 19:25:39', '2018-11-18 19:25:39', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1064117361699004418, 1060903467811659778);
INSERT INTO `sys_role_auth` VALUES (1064395598995603457, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1061206366072623105, 1060781327489757186);
INSERT INTO `sys_role_auth` VALUES (1064395599003992066, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1061464714533773314, 1060781327489757186);
INSERT INTO `sys_role_auth` VALUES (1064395599016574977, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063782150784229378, 1060781327489757186);
INSERT INTO `sys_role_auth` VALUES (1064395599024963586, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063782508084404225, 1060781327489757186);
INSERT INTO `sys_role_auth` VALUES (1064395599037546498, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1063782794035273729, 1060781327489757186);
INSERT INTO `sys_role_auth` VALUES (1064395599050129409, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1064340058714058754, 1060781327489757186);
INSERT INTO `sys_role_auth` VALUES (1064395599058518017, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1064395542594797569, 1060781327489757186);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色主键',
  `menu_id` bigint(32) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单中间表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1064395598932688898, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1060780925302140929);
INSERT INTO `sys_role_menu` VALUES (1064395598949466114, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1060781327489757186);
INSERT INTO `sys_role_menu` VALUES (1064395598957854721, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1060781599209353218);
INSERT INTO `sys_role_menu` VALUES (1064395598970437634, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1060903467811659778);
INSERT INTO `sys_role_menu` VALUES (1064395598978826242, '2018-11-19 13:51:00', '2018-11-19 13:51:00', NULL, NULL, NULL, NULL, 1, 1060546311132381185, 1061157804223815681);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `code` bigint(32) DEFAULT NULL COMMENT '编码',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `open_id` varchar(200) DEFAULT NULL COMMENT '开放id',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(300) DEFAULT NULL COMMENT '加密密码',
  `salt` varchar(300) DEFAULT NULL COMMENT '加密盐',
  `signup_type` smallint(3) DEFAULT NULL COMMENT '注册类型 101PC 102手机',
  `login_type` smallint(3) DEFAULT NULL COMMENT '登录类型 201PC登录 202手机登录 203为邮箱',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `avatar` varchar(2000) DEFAULT NULL COMMENT '头像',
  `status` smallint(3) DEFAULT '300' COMMENT '状态 300可以登录 301禁止登陆',
  `gender` smallint(3) DEFAULT NULL COMMENT '性别 110男 111女 112未知',
  `weight` int(3) DEFAULT NULL COMMENT '体重 单位kg',
  `height` int(3) DEFAULT NULL COMMENT '身高 单位厘米',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1058938369329131521, '2018-11-04 12:25:56', '2018-11-04 12:25:56', NULL, NULL, 'jerrychir', 1058938369329131521, NULL, 1, 'jerrychir', '6ab4oyey', '15351229035', NULL, NULL, '$2a$10$SjWrel8Brcii9w.00ZDmOOUCr9atngMOA12NqjgmtjRLjhf.AmGAu', NULL, 101, 201, NULL, 'https://sangoes.oss-cn-hangzhou.aliyuncs.com/boot/2018-11-19/b4926fe9431845009d42c9ecb44f34a8-1542610473598210557085083934882.jpg', 300, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1060442286315544577, '2018-11-08 16:01:57', '2018-11-08 16:02:22', NULL, NULL, NULL, NULL, NULL, 1, 'sangoes', '叁果', '15351229036', NULL, NULL, '$2a$10$uLvw84jhpHw3O7P1O6uw2O5Iy5H3y1y6FBe4KZBoP1Q.P2LTV3ON6', NULL, 103, NULL, NULL, NULL, 300, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1060444297027190786, '2018-11-08 16:09:57', '2018-11-08 16:09:57', NULL, NULL, NULL, NULL, NULL, 1, 'kjk40nt5', '周扬', '15351229037', NULL, NULL, '$2a$10$EIYKPy/v.uCfnJgTYH4adO95f1Avph/A3cTmXoTlbvtqlWNgzh4g.', NULL, 103, NULL, NULL, NULL, 300, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1064416178692055041, '2018-11-19 15:12:47', '2018-11-19 15:12:47', NULL, 1058938369329131521, NULL, NULL, NULL, 1, 'qby6wxybz', '王晚', '15351229039', NULL, NULL, '$2a$10$nmZltd9ic5Bzbfx5H9KX0.MwEyPQr2tP2aF5WgTlYsPbMvNTFWebO', NULL, 103, NULL, NULL, NULL, 300, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1064416727286046721, '2018-11-19 15:14:58', '2018-11-19 15:14:58', NULL, 1058938369329131521, NULL, NULL, NULL, 1, '6ftecxi6e', '赵坤', '15351229045', NULL, NULL, '$2a$10$EGzs/sJJMD7m2M8QsrVGXejg8dbltQOZwvxJHikTWBvD9HOIMKmdS', NULL, 103, NULL, NULL, NULL, 300, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户主键',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1061929260926918658, '2018-11-12 18:30:40', '2018-11-12 18:30:40', NULL, NULL, NULL, NULL, 1, 1060442286315544577, 1060546917704237057);
INSERT INTO `sys_user_role` VALUES (1064161571659599874, '2018-11-18 22:21:04', '2018-11-18 22:21:04', NULL, NULL, NULL, NULL, 1, 1058938369329131521, 1060546311132381185);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

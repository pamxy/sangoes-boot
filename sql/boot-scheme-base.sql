/*
 Navicat Premium Data Transfer

 Source Server         : sangoes@localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 30/01/2019 21:20:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `title` varchar(512) DEFAULT NULL COMMENT '文章标题',
  `content` longtext COMMENT '文章内容',
  `mode` varchar(50) DEFAULT 'html' COMMENT '文章编辑模式 html markdown',
  `summary` text COMMENT '摘要',
  `thumbnail` varchar(512) DEFAULT NULL COMMENT '缩率图',
  `status` smallint(1) DEFAULT '0' COMMENT '状态 0启用 1禁用',
  `meta_keywords` varchar(512) DEFAULT NULL COMMENT 'seo关键词',
  `meta_desc` varchar(512) DEFAULT NULL COMMENT 'seo内容描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='cms文章';

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `title` varchar(512) DEFAULT NULL COMMENT '分类标题',
  `content` longtext COMMENT '内容描述',
  `summary` text COMMENT '摘要',
  `meta_keywords` varchar(512) DEFAULT NULL COMMENT 'seo关键词',
  `meta_desc` varchar(512) DEFAULT NULL COMMENT 'seo内容描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Cms文章分类';

-- ----------------------------
-- Table structure for cms_menu
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu`;
CREATE TABLE `cms_menu` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `parent_id` bigint(32) DEFAULT '-1' COMMENT '父主键 默认-1',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='cms菜单';

-- ----------------------------
-- Table structure for depart
-- ----------------------------
DROP TABLE IF EXISTS `depart`;
CREATE TABLE `depart` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `parent_id` bigint(32) DEFAULT '-1' COMMENT '父主键 默认-1',
  `depart_name` varchar(200) DEFAULT NULL COMMENT '部门名称',
  `telephone` int(20) DEFAULT NULL COMMENT '座机电话',
  `leader_id` bigint(32) DEFAULT NULL COMMENT '负责人主键',
  `address` varchar(500) DEFAULT NULL COMMENT '地址',
  `des` varchar(1000) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `parent_id` bigint(32) DEFAULT '-1' COMMENT '父主键 默认-1',
  `dict_key` varchar(200) DEFAULT NULL COMMENT '字典编码',
  `dict_value` varchar(200) DEFAULT NULL COMMENT '字典名称',
  `des` varchar(1000) DEFAULT NULL COMMENT '字典描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件管理/附件管理';

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `client_id` varchar(256) DEFAULT NULL COMMENT '客户端ID',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '客户端密钥',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '资源ID',
  `scope` varchar(256) DEFAULT NULL COMMENT '授权域',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '授权模式',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '回调地址',
  `authorities` varchar(256) DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '失效时间',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '刷新时间',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '扩展信息',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '是否自动放行',
  PRIMARY KEY (`id`) USING BTREE
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
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
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
-- Table structure for sys_log_0
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_0`;
CREATE TABLE `sys_log_0` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `title` varchar(255) DEFAULT NULL COMMENT '日志标题',
  `uri` varchar(255) DEFAULT NULL COMMENT '请求uri',
  `url` varchar(255) DEFAULT NULL COMMENT '请求url',
  `remote` varchar(255) DEFAULT NULL COMMENT '请求IP地址',
  `method` varchar(50) DEFAULT NULL COMMENT '请求方法',
  `params` text COMMENT '请求参数',
  `exception` text COMMENT '异常信息',
  `elapsed` varchar(50) DEFAULT NULL COMMENT '请求耗时',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `auth_token` varchar(2048) DEFAULT NULL COMMENT '用户授权token',
  `status` smallint(4) DEFAULT NULL COMMENT '返回状态码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志';

-- ----------------------------
-- Table structure for sys_log_1
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_1`;
CREATE TABLE `sys_log_1` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `title` varchar(255) DEFAULT NULL COMMENT '日志标题',
  `uri` varchar(255) DEFAULT NULL COMMENT '请求uri',
  `url` varchar(255) DEFAULT NULL COMMENT '请求url',
  `remote` varchar(255) DEFAULT NULL COMMENT '请求IP地址',
  `method` varchar(50) DEFAULT NULL COMMENT '请求方法',
  `params` text COMMENT '请求参数',
  `exception` text COMMENT '异常信息',
  `elapsed` varchar(50) DEFAULT NULL COMMENT '请求耗时',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `auth_token` varchar(2048) DEFAULT NULL COMMENT '用户授权token',
  `status` smallint(4) DEFAULT NULL COMMENT '返回状态码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日志';

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
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
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
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) DEFAULT NULL COMMENT '角色编码',
  `des` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `status` smallint(1) DEFAULT '1' COMMENT '1启用 0禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

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
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色主键',
  `auth_id` bigint(32) DEFAULT NULL COMMENT '权限主键',
  `menu_id` bigint(32) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限中间表';

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
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色主键',
  `menu_id` bigint(32) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单中间表';

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
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
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
  `integration` int(11) DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

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
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户主键',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_depart
-- ----------------------------
DROP TABLE IF EXISTS `user_depart`;
CREATE TABLE `user_depart` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `crt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `upd_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建者用户名',
  `creator_id` bigint(32) DEFAULT NULL COMMENT '创建者主键',
  `updator` varchar(50) DEFAULT NULL COMMENT '更新者用户名',
  `updator_id` bigint(32) DEFAULT NULL COMMENT '更新者主键',
  `deleted` smallint(3) DEFAULT '1' COMMENT '删除 1未删除 0删除',
  `sort` bigint(10) DEFAULT NULL COMMENT '排序',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户主键',
  `depart_id` bigint(32) DEFAULT NULL COMMENT '部门主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户部门中间表';

SET FOREIGN_KEY_CHECKS = 1;

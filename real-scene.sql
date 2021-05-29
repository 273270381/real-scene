/*
 Navicat Premium Data Transfer

 Source Server         : mySql172.16.14.5
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 172.16.14.5:3306
 Source Schema         : real-scene

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 29/05/2021 15:43:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1951);
INSERT INTO `hibernate_sequence` VALUES (1951);
INSERT INTO `hibernate_sequence` VALUES (1951);
INSERT INTO `hibernate_sequence` VALUES (1951);
INSERT INTO `hibernate_sequence` VALUES (1951);
INSERT INTO `hibernate_sequence` VALUES (1951);
INSERT INTO `hibernate_sequence` VALUES (1951);
INSERT INTO `hibernate_sequence` VALUES (1951);

-- ----------------------------
-- Table structure for t_sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dept`;
CREATE TABLE `t_sys_dept`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint(0) NULL DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  `pid` bigint(0) NULL DEFAULT NULL,
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `simplename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `version` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1907 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_dept
-- ----------------------------
INSERT INTO `t_sys_dept` VALUES (1, NULL, NULL, NULL, NULL, '总公司', 1, 0, '[0],', '总公司', '', NULL);
INSERT INTO `t_sys_dept` VALUES (2, NULL, NULL, NULL, NULL, '开发部', 2, 1, '[0],[1],', '开发部', '', NULL);
INSERT INTO `t_sys_dept` VALUES (3, NULL, NULL, NULL, '2020-08-22 14:27:35', '运营部', 3, 6, '[0],[1],[3],[6],', '运营部', '', NULL);
INSERT INTO `t_sys_dept` VALUES (4, NULL, NULL, NULL, NULL, '战略部', 4, 1, '[0],[1],', '战略部', '', NULL);
INSERT INTO `t_sys_dept` VALUES (5, NULL, '2020-06-30 10:00:37', NULL, '2020-07-22 11:39:01', '市场部', 5, 1, '[0],[1],', '市场部', '', NULL);
INSERT INTO `t_sys_dept` VALUES (6, NULL, '2020-06-30 10:01:18', NULL, '2020-07-22 14:53:45', '行政部', 6, 3, '[0],[1],[3],', '行政部', '', NULL);
INSERT INTO `t_sys_dept` VALUES (7, NULL, '2020-08-06 15:41:26', NULL, '2020-08-06 15:41:26', 'aaaa', 110000, 0, '[0],', 'aaa', '', NULL);
INSERT INTO `t_sys_dept` VALUES (994, NULL, '2020-07-22 14:14:10', NULL, '2020-07-22 14:14:10', '测试部门', 1, 0, '[0],', '测试', NULL, NULL);
INSERT INTO `t_sys_dept` VALUES (1007, NULL, '2020-08-14 10:39:04', NULL, '2020-09-23 15:00:01', 'asdfasdf', 6, 1007, '[0],[1007],', 'bbbb', '', NULL);
INSERT INTO `t_sys_dept` VALUES (1540, NULL, '2020-08-20 16:46:31', NULL, '2020-10-09 15:53:36', 'ddd', 1, 1540, '[0],[1540],', 'ddd', '', NULL);
INSERT INTO `t_sys_dept` VALUES (1597, NULL, '2020-08-22 14:47:20', NULL, '2020-08-22 15:04:58', '财务部', 1, 5, '[0],[1],[5],', '财务部', '', NULL);
INSERT INTO `t_sys_dept` VALUES (1695, NULL, '2020-08-27 09:13:49', NULL, '2020-10-09 16:10:04', 'ccc', 1, 1695, '[0],[1695],', 'cccc', '', NULL);
INSERT INTO `t_sys_dept` VALUES (1907, NULL, '2020-09-23 09:48:28', NULL, '2020-09-23 09:48:28', 'cccq', 1, 0, '[0],', 'ccccq', '', NULL);

-- ----------------------------
-- Table structure for t_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dict`;
CREATE TABLE `t_sys_dict`  (
  `id` bigint(0) NOT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint(0) NULL DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` bigint(0) NULL DEFAULT NULL,
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_dict
-- ----------------------------
INSERT INTO `t_sys_dict` VALUES (61, NULL, '2020-07-02 09:22:32', NULL, '2020-07-02 09:22:32', '状态', '0', 0, NULL);
INSERT INTO `t_sys_dict` VALUES (62, NULL, '2020-07-02 09:22:32', NULL, '2020-07-02 09:22:32', '可用', '1', 61, NULL);
INSERT INTO `t_sys_dict` VALUES (63, NULL, '2020-07-02 09:22:33', NULL, '2020-07-02 09:22:33', '冻结', '2', 61, NULL);
INSERT INTO `t_sys_dict` VALUES (64, NULL, '2020-07-02 09:22:33', NULL, '2020-07-02 09:22:33', '删除', '3', 61, NULL);
INSERT INTO `t_sys_dict` VALUES (74, NULL, '2020-07-02 15:22:16', NULL, '2020-07-02 15:22:16', '公司', '0', 0, NULL);
INSERT INTO `t_sys_dict` VALUES (75, NULL, '2020-07-02 15:22:17', NULL, '2020-07-02 15:22:17', '如是地球', '1', 74, NULL);
INSERT INTO `t_sys_dict` VALUES (76, NULL, '2020-07-02 15:22:17', NULL, '2020-07-02 15:22:17', '如实地球研究院', '2', 74, NULL);
INSERT INTO `t_sys_dict` VALUES (85, NULL, '2020-07-07 11:26:05', NULL, '2020-07-07 11:26:05', '井盖类型', '0', 0, NULL);
INSERT INTO `t_sys_dict` VALUES (86, NULL, '2020-07-07 11:26:05', NULL, '2020-07-07 11:26:05', '非防盗式', '1', 85, NULL);
INSERT INTO `t_sys_dict` VALUES (87, NULL, '2020-07-07 11:26:05', NULL, '2020-07-07 11:26:05', '防盗式', '2', 85, NULL);
INSERT INTO `t_sys_dict` VALUES (1011, NULL, '2020-08-14 16:24:21', NULL, '2020-08-14 16:24:21', '材质类型', '0', 0, NULL);
INSERT INTO `t_sys_dict` VALUES (1012, NULL, '2020-08-14 16:24:21', NULL, '2020-08-14 16:24:21', '球墨铸铁', '1', 1011, NULL);
INSERT INTO `t_sys_dict` VALUES (1013, NULL, '2020-08-14 16:24:21', NULL, '2020-08-14 16:24:21', '铸铁', '2', 1011, NULL);
INSERT INTO `t_sys_dict` VALUES (1014, NULL, '2020-08-14 16:24:21', NULL, '2020-08-14 16:24:21', '光纤', '3', 1011, NULL);
INSERT INTO `t_sys_dict` VALUES (1015, NULL, '2020-08-14 16:24:21', NULL, '2020-08-14 16:24:21', '铜', '4', 1011, NULL);
INSERT INTO `t_sys_dict` VALUES (1016, NULL, '2020-08-14 16:24:22', NULL, '2020-08-14 16:24:22', 'PE', '5', 1011, NULL);
INSERT INTO `t_sys_dict` VALUES (1017, NULL, '2020-08-14 16:24:22', NULL, '2020-08-14 16:24:22', '铜/光', '6', 1011, NULL);
INSERT INTO `t_sys_dict` VALUES (1018, NULL, '2020-08-14 16:24:22', NULL, '2020-08-14 16:24:22', '砼', '7', 1011, NULL);
INSERT INTO `t_sys_dict` VALUES (1019, NULL, '2020-08-14 16:24:22', NULL, '2020-08-14 16:24:22', 'PVC', '8', 1011, NULL);
INSERT INTO `t_sys_dict` VALUES (1024, NULL, '2020-07-27 09:45:13', NULL, '2020-07-27 09:45:13', '埋设类型', '0', 0, NULL);
INSERT INTO `t_sys_dict` VALUES (1025, NULL, '2020-07-27 09:45:13', NULL, '2020-07-27 09:45:13', '管埋', '1', 1024, NULL);
INSERT INTO `t_sys_dict` VALUES (1026, NULL, '2020-07-27 09:45:13', NULL, '2020-07-27 09:45:13', '直埋', '2', 1024, NULL);
INSERT INTO `t_sys_dict` VALUES (1027, NULL, '2020-07-27 09:45:14', NULL, '2020-07-27 09:45:14', '矩形管沟', '3', 1024, NULL);
INSERT INTO `t_sys_dict` VALUES (1043, NULL, '2020-07-27 10:25:52', NULL, '2020-07-27 10:25:52', '管线类型', '0', 0, NULL);
INSERT INTO `t_sys_dict` VALUES (1044, NULL, '2020-07-27 10:25:52', NULL, '2020-07-27 10:25:52', '城市电信', '1', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1045, NULL, '2020-07-27 10:25:52', NULL, '2020-07-27 10:25:52', '电信', '2', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1046, NULL, '2020-07-27 10:25:52', NULL, '2020-07-27 10:25:52', '联通', '3', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1047, NULL, '2020-07-27 10:25:53', NULL, '2020-07-27 10:25:53', '电力通讯', '4', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1048, NULL, '2020-07-27 10:25:53', NULL, '2020-07-27 10:25:53', '供电', '5', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1049, NULL, '2020-07-27 10:25:53', NULL, '2020-07-27 10:25:53', '合流', '6', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1050, NULL, '2020-07-27 10:25:53', NULL, '2020-07-27 10:25:53', '给水', '7', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1051, NULL, '2020-07-27 10:25:53', NULL, '2020-07-27 10:25:53', '监控', '8', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1052, NULL, '2020-07-27 10:25:54', NULL, '2020-07-27 10:25:54', '路灯', '9', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1053, NULL, '2020-07-27 10:25:54', NULL, '2020-07-27 10:25:54', '移动', '10', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1054, NULL, '2020-07-27 10:25:54', NULL, '2020-07-27 10:25:54', '热力', '11', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1055, NULL, '2020-07-27 10:25:54', NULL, '2020-07-27 10:25:54', '天然气', '12', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1056, NULL, '2020-07-27 10:25:54', NULL, '2020-07-27 10:25:54', '电视', '13', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1057, NULL, '2020-07-27 10:25:55', NULL, '2020-07-27 10:25:55', '污水', '14', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1058, NULL, '2020-07-27 10:25:55', NULL, '2020-07-27 10:25:55', '交通信号', '15', 1043, NULL);
INSERT INTO `t_sys_dict` VALUES (1059, NULL, '2020-07-27 10:25:55', NULL, '2020-07-27 10:25:55', '雨水', '16', 1043, NULL);

-- ----------------------------
-- Table structure for t_sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_login_log`;
CREATE TABLE `t_sys_login_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1950 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_login_log
-- ----------------------------
INSERT INTO `t_sys_login_log` VALUES (913, '2020-08-13 13:53:29', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (914, '2020-08-13 13:53:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (915, '2020-08-13 13:54:05', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (916, '2020-08-13 13:54:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (917, '2020-08-13 13:56:27', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (918, '2020-08-13 13:56:43', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (919, '2020-08-13 13:59:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (927, '2020-08-13 14:03:08', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (928, '2020-08-13 14:03:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (957, '2020-08-13 14:25:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (958, '2020-08-13 14:25:57', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (998, '2020-08-13 17:04:00', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (999, '2020-08-13 17:04:06', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1000, '2020-08-13 17:04:10', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1001, '2020-08-13 17:04:53', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1002, '2020-08-13 17:05:02', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1003, '2020-08-14 08:45:41', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1004, '2020-08-14 08:54:03', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1005, '2020-08-14 09:14:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1006, '2020-08-14 10:23:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1009, '2020-08-14 11:34:28', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1010, '2020-08-14 15:49:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1021, '2020-08-14 16:25:52', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1022, '2020-08-14 16:25:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1023, '2020-08-14 16:26:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1024, '2020-08-14 16:26:15', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1025, '2020-08-14 16:41:44', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1026, '2020-08-14 16:47:57', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1027, '2020-08-14 16:51:43', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1028, '2020-08-14 16:51:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1029, '2020-08-14 16:53:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1030, '2020-08-14 16:54:18', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1031, '2020-08-14 17:02:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1032, '2020-08-14 17:05:48', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1033, '2020-08-14 17:06:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1034, '2020-08-14 17:06:18', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1035, '2020-08-14 17:08:36', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1036, '2020-08-14 17:13:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1037, '2020-08-14 17:14:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1038, '2020-08-14 17:14:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1039, '2020-08-14 17:14:26', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1040, '2020-08-14 17:14:32', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1041, '2020-08-14 17:14:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1042, '2020-08-14 17:14:47', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1043, '2020-08-14 17:14:56', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1044, '2020-08-14 17:15:43', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1045, '2020-08-14 17:15:56', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1046, '2020-08-14 17:16:06', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1047, '2020-08-14 17:16:14', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1048, '2020-08-14 17:16:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1049, '2020-08-14 17:16:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1050, '2020-08-14 17:16:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1051, '2020-08-14 17:16:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1052, '2020-08-14 17:17:09', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1053, '2020-08-14 17:17:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1054, '2020-08-14 17:18:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1055, '2020-08-14 17:21:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1056, '2020-08-14 17:21:56', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1057, '2020-08-14 17:27:57', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1058, '2020-08-14 17:28:44', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1059, '2020-08-15 08:41:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1060, '2020-08-15 08:50:54', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1061, '2020-08-15 08:55:19', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1062, '2020-08-15 09:06:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1063, '2020-08-15 09:16:47', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1064, '2020-08-15 13:52:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1065, '2020-08-15 13:52:19', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1066, '2020-08-15 14:09:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1067, '2020-08-15 14:10:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1068, '2020-08-15 14:16:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1069, '2020-08-15 14:16:31', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1070, '2020-08-15 14:27:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1071, '2020-08-15 14:27:29', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1072, '2020-08-15 15:01:43', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1073, '2020-08-15 15:02:10', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1074, '2020-08-15 15:02:41', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1075, '2020-08-15 15:02:55', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1076, '2020-08-15 15:03:10', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1077, '2020-08-15 15:04:05', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1078, '2020-08-15 15:05:20', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1079, '2020-08-15 15:19:09', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1080, '2020-08-15 15:20:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1081, '2020-08-15 15:23:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1082, '2020-08-15 15:23:58', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1083, '2020-08-15 15:33:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1084, '2020-08-15 15:36:04', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1085, '2020-08-15 15:40:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1086, '2020-08-15 15:42:59', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1087, '2020-08-15 15:57:43', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1088, '2020-08-15 15:59:08', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1089, '2020-08-15 16:09:49', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1090, '2020-08-15 16:52:07', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1091, '2020-08-17 08:43:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1092, '2020-08-17 08:44:01', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1093, '2020-08-17 09:06:08', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1094, '2020-08-17 09:08:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1095, '2020-08-17 09:19:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1096, '2020-08-17 09:26:48', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1097, '2020-08-17 09:44:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1098, '2020-08-17 09:56:30', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1099, '2020-08-17 10:36:36', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1100, '2020-08-17 10:36:38', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1101, '2020-08-17 10:36:44', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1102, '2020-08-17 10:36:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1103, '2020-08-17 10:36:52', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1104, '2020-08-17 10:36:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1105, '2020-08-17 10:37:05', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1106, '2020-08-17 10:37:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1107, '2020-08-17 10:37:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1108, '2020-08-17 10:38:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1109, '2020-08-17 10:38:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1110, '2020-08-17 10:38:18', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1111, '2020-08-17 10:38:29', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1112, '2020-08-17 11:07:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1113, '2020-08-17 11:31:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1114, '2020-08-17 11:35:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1115, '2020-08-17 11:39:19', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1116, '2020-08-17 11:46:04', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1117, '2020-08-17 11:49:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1118, '2020-08-17 11:50:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1119, '2020-08-17 11:51:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1120, '2020-08-17 11:51:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1121, '2020-08-17 11:53:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1122, '2020-08-17 11:53:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1123, '2020-08-17 11:54:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1124, '2020-08-17 11:54:47', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1125, '2020-08-17 13:30:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1126, '2020-08-17 13:31:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1127, '2020-08-17 13:35:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1128, '2020-08-17 13:35:26', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1129, '2020-08-17 13:35:44', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1130, '2020-08-17 13:36:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1131, '2020-08-17 13:36:45', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1132, '2020-08-17 13:37:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1133, '2020-08-17 13:41:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1134, '2020-08-17 13:41:47', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1135, '2020-08-17 13:42:25', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1136, '2020-08-17 13:42:36', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1137, '2020-08-17 13:42:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1138, '2020-08-17 13:43:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1139, '2020-08-17 13:43:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1140, '2020-08-17 13:43:33', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1141, '2020-08-17 13:43:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1142, '2020-08-17 13:43:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1143, '2020-08-17 13:44:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1144, '2020-08-17 13:44:14', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1145, '2020-08-17 13:44:27', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1146, '2020-08-17 13:44:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1147, '2020-08-17 13:45:06', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1148, '2020-08-17 13:47:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1149, '2020-08-17 13:49:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1150, '2020-08-17 13:52:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1151, '2020-08-17 13:52:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1152, '2020-08-17 13:53:01', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1153, '2020-08-17 13:53:15', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1154, '2020-08-17 13:53:38', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1155, '2020-08-17 13:53:46', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1156, '2020-08-17 13:54:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1157, '2020-08-17 13:58:33', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1158, '2020-08-17 13:58:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1159, '2020-08-17 14:11:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1160, '2020-08-17 14:11:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1161, '2020-08-17 14:14:52', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1162, '2020-08-17 14:15:03', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1163, '2020-08-17 14:15:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1164, '2020-08-17 14:15:14', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1165, '2020-08-17 14:16:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1166, '2020-08-17 14:16:11', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1167, '2020-08-17 14:16:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1168, '2020-08-17 14:17:56', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1169, '2020-08-17 14:18:29', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1170, '2020-08-17 14:18:33', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1171, '2020-08-17 14:21:52', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1172, '2020-08-17 14:22:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1173, '2020-08-17 14:22:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1174, '2020-08-17 14:22:32', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1175, '2020-08-17 14:22:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1176, '2020-08-17 14:24:33', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1177, '2020-08-17 14:25:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1178, '2020-08-17 14:25:29', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1179, '2020-08-17 14:26:09', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1180, '2020-08-17 14:26:14', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1181, '2020-08-17 14:30:58', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1182, '2020-08-17 14:31:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1183, '2020-08-17 14:31:04', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1184, '2020-08-17 14:31:49', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1185, '2020-08-17 14:31:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1186, '2020-08-17 14:32:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1187, '2020-08-17 14:32:19', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1188, '2020-08-17 14:32:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1189, '2020-08-17 14:32:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1190, '2020-08-17 14:33:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1191, '2020-08-17 14:33:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1192, '2020-08-17 14:39:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1193, '2020-08-17 14:39:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1194, '2020-08-17 14:41:46', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1195, '2020-08-17 14:42:52', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1196, '2020-08-17 14:43:25', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1197, '2020-08-17 14:43:32', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1198, '2020-08-17 14:43:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1199, '2020-08-17 15:01:46', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1200, '2020-08-17 15:01:46', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1201, '2020-08-17 15:01:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1202, '2020-08-17 15:02:02', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1203, '2020-08-17 15:02:16', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1204, '2020-08-17 15:02:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1205, '2020-08-17 15:03:16', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1206, '2020-08-17 15:03:19', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1207, '2020-08-17 15:04:15', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1208, '2020-08-17 15:05:03', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1209, '2020-08-17 15:08:20', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1210, '2020-08-17 15:08:29', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1211, '2020-08-17 15:09:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1212, '2020-08-17 15:09:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1213, '2020-08-17 15:12:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1214, '2020-08-17 15:12:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1215, '2020-08-17 15:17:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1216, '2020-08-17 15:38:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1217, '2020-08-17 15:49:18', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1218, '2020-08-17 15:49:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1219, '2020-08-17 15:51:09', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1220, '2020-08-17 15:51:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1221, '2020-08-17 15:52:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1222, '2020-08-17 15:52:29', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1223, '2020-08-17 15:54:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1224, '2020-08-17 15:54:32', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1225, '2020-08-17 15:54:49', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1226, '2020-08-17 15:54:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1227, '2020-08-17 15:55:08', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1228, '2020-08-17 15:55:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1229, '2020-08-17 15:56:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1230, '2020-08-17 15:56:19', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1231, '2020-08-17 15:56:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1232, '2020-08-17 15:57:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1233, '2020-08-17 15:57:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1234, '2020-08-17 15:58:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1235, '2020-08-17 15:58:26', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1236, '2020-08-17 15:58:30', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1237, '2020-08-17 16:23:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1238, '2020-08-17 16:26:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1239, '2020-08-17 16:26:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1240, '2020-08-17 16:26:58', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1241, '2020-08-17 16:27:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1242, '2020-08-17 16:28:03', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1243, '2020-08-17 16:33:08', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1244, '2020-08-17 16:34:09', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1245, '2020-08-17 16:36:36', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1246, '2020-08-17 16:41:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1247, '2020-08-17 16:44:04', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1248, '2020-08-17 16:47:11', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1249, '2020-08-17 16:51:24', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1250, '2020-08-17 16:53:11', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1251, '2020-08-17 16:53:19', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1252, '2020-08-17 16:53:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1253, '2020-08-17 16:54:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1254, '2020-08-17 16:54:45', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1255, '2020-08-17 16:55:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1256, '2020-08-17 16:55:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1257, '2020-08-17 16:55:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1258, '2020-08-17 16:55:14', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1259, '2020-08-17 16:55:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1260, '2020-08-17 16:55:18', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1261, '2020-08-17 16:55:20', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1262, '2020-08-17 16:55:31', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1263, '2020-08-17 16:55:32', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1264, '2020-08-17 16:55:33', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1265, '2020-08-17 16:55:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1266, '2020-08-17 16:55:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1267, '2020-08-17 16:55:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1268, '2020-08-17 16:55:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1269, '2020-08-17 16:55:44', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1270, '2020-08-17 16:55:44', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1271, '2020-08-17 16:55:45', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1272, '2020-08-17 16:55:45', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1273, '2020-08-17 16:55:46', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1274, '2020-08-17 16:55:47', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1275, '2020-08-17 16:55:48', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1278, '2020-08-17 17:03:18', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1279, '2020-08-17 17:03:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1280, '2020-08-17 17:03:26', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1281, '2020-08-17 17:03:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1282, '2020-08-17 17:03:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1283, '2020-08-17 17:04:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1284, '2020-08-17 17:04:36', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1285, '2020-08-17 17:04:38', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1286, '2020-08-17 17:04:38', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1287, '2020-08-17 17:04:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1288, '2020-08-17 17:04:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1289, '2020-08-17 17:04:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1290, '2020-08-17 17:04:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1291, '2020-08-17 17:04:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1292, '2020-08-17 17:04:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1293, '2020-08-17 17:04:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1294, '2020-08-17 17:04:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1295, '2020-08-17 17:04:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1296, '2020-08-17 17:07:49', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1297, '2020-08-17 17:08:08', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1298, '2020-08-17 17:08:44', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1299, '2020-08-17 17:08:58', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1300, '2020-08-17 17:09:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1301, '2020-08-17 17:10:19', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1302, '2020-08-17 17:15:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1303, '2020-08-17 17:20:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1304, '2020-08-18 08:34:38', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1305, '2020-08-18 08:38:30', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1306, '2020-08-18 08:48:31', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1307, '2020-08-18 09:11:11', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1308, '2020-08-18 09:11:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1309, '2020-08-18 09:27:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1310, '2020-08-18 09:27:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1311, '2020-08-18 09:29:52', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1312, '2020-08-18 11:21:45', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1313, '2020-08-18 13:50:56', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1314, '2020-08-18 13:56:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1315, '2020-08-18 13:56:25', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1316, '2020-08-18 14:33:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1317, '2020-08-18 14:42:20', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1318, '2020-08-18 14:46:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1319, '2020-08-18 14:47:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1320, '2020-08-18 14:48:03', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1321, '2020-08-18 14:48:11', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1322, '2020-08-18 14:48:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1323, '2020-08-18 14:48:33', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1324, '2020-08-18 14:48:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1325, '2020-08-18 14:49:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1326, '2020-08-18 14:49:48', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1327, '2020-08-18 15:24:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1328, '2020-08-18 15:46:03', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1329, '2020-08-18 15:53:56', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1330, '2020-08-18 16:19:28', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1331, '2020-08-18 16:20:36', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1332, '2020-08-18 16:20:48', '172.16.14.8', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1333, '2020-08-18 16:33:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1334, '2020-08-18 16:51:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1335, '2020-08-18 17:19:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1336, '2020-08-19 09:05:01', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1337, '2020-08-19 09:21:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1338, '2020-08-19 09:25:51', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1339, '2020-08-19 10:24:26', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1340, '2020-08-19 10:29:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1341, '2020-08-19 11:01:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1342, '2020-08-19 11:03:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1344, '2020-08-19 11:03:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1346, '2020-08-19 11:04:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1348, '2020-08-19 11:09:38', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1350, '2020-08-19 11:09:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1352, '2020-08-19 11:17:04', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1353, '2020-08-19 11:17:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1354, '2020-08-19 11:25:46', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1355, '2020-08-19 11:27:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1356, '2020-08-19 11:27:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1357, '2020-08-19 11:34:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1359, '2020-08-19 11:44:57', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1360, '2020-08-19 12:33:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1364, '2020-08-19 15:17:45', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1368, '2020-08-19 15:22:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1374, '2020-08-19 15:23:45', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1375, '2020-08-19 15:23:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1376, '2020-08-19 15:35:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1377, '2020-08-19 15:48:38', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1378, '2020-08-19 16:54:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1379, '2020-08-19 17:00:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1383, '2020-08-19 17:14:30', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1384, '2020-08-20 08:45:20', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1385, '2020-08-20 08:45:31', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1386, '2020-08-20 08:45:37', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1387, '2020-08-20 08:45:49', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1388, '2020-08-20 09:39:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1389, '2020-08-20 11:39:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1390, '2020-08-20 13:56:47', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1391, '2020-08-20 15:18:46', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1392, '2020-08-20 15:18:49', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1393, '2020-08-20 15:18:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1394, '2020-08-20 15:18:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1395, '2020-08-20 15:18:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1396, '2020-08-20 15:18:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1397, '2020-08-20 15:18:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1398, '2020-08-20 15:18:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1399, '2020-08-20 15:18:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1400, '2020-08-20 15:18:51', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1401, '2020-08-20 15:18:54', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1402, '2020-08-20 15:19:01', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1403, '2020-08-20 15:19:20', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1404, '2020-08-20 15:19:21', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1405, '2020-08-20 15:19:21', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1406, '2020-08-20 15:19:21', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1407, '2020-08-20 15:19:21', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1408, '2020-08-20 15:19:21', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1409, '2020-08-20 15:19:22', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1410, '2020-08-20 15:19:22', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1411, '2020-08-20 15:19:22', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1412, '2020-08-20 15:19:22', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1413, '2020-08-20 15:19:35', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1414, '2020-08-20 15:19:36', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1415, '2020-08-20 15:19:38', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1416, '2020-08-20 15:19:38', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1417, '2020-08-20 15:19:39', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1418, '2020-08-20 15:19:39', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1419, '2020-08-20 15:19:40', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1420, '2020-08-20 15:19:40', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1421, '2020-08-20 15:19:41', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1422, '2020-08-20 15:19:41', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1423, '2020-08-20 15:19:42', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1424, '2020-08-20 15:19:42', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1425, '2020-08-20 15:19:42', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1426, '2020-08-20 15:19:43', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1427, '2020-08-20 15:19:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1428, '2020-08-20 15:21:27', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1429, '2020-08-20 15:22:03', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1430, '2020-08-20 15:23:07', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1431, '2020-08-20 15:23:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1432, '2020-08-20 15:23:43', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1433, '2020-08-20 15:25:35', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1434, '2020-08-20 15:26:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1435, '2020-08-20 15:27:14', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1436, '2020-08-20 15:29:14', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1437, '2020-08-20 15:29:43', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1438, '2020-08-20 15:30:41', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1439, '2020-08-20 15:30:48', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1440, '2020-08-20 15:30:52', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1441, '2020-08-20 15:30:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1442, '2020-08-20 15:30:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1443, '2020-08-20 15:30:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1444, '2020-08-20 15:30:57', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1445, '2020-08-20 15:30:58', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1446, '2020-08-20 15:30:58', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1447, '2020-08-20 15:30:59', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1448, '2020-08-20 15:30:59', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1449, '2020-08-20 15:31:46', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1450, '2020-08-20 15:31:52', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1451, '2020-08-20 15:32:10', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1452, '2020-08-20 15:32:38', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1453, '2020-08-20 15:32:46', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1454, '2020-08-20 15:36:14', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1455, '2020-08-20 15:36:27', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1456, '2020-08-20 15:37:15', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1457, '2020-08-20 15:37:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1458, '2020-08-20 15:37:44', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1459, '2020-08-20 15:37:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1460, '2020-08-20 15:37:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1461, '2020-08-20 15:37:51', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1462, '2020-08-20 15:37:52', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1463, '2020-08-20 15:37:52', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1464, '2020-08-20 15:39:44', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1465, '2020-08-20 15:39:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1466, '2020-08-20 15:40:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1467, '2020-08-20 15:40:48', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1468, '2020-08-20 15:40:59', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1469, '2020-08-20 15:41:01', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1470, '2020-08-20 15:41:01', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1471, '2020-08-20 15:41:02', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1472, '2020-08-20 15:41:03', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1473, '2020-08-20 15:41:03', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1474, '2020-08-20 15:41:03', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1475, '2020-08-20 15:41:04', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1476, '2020-08-20 15:41:04', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1477, '2020-08-20 15:41:04', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1478, '2020-08-20 15:41:05', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1479, '2020-08-20 15:41:05', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1480, '2020-08-20 15:41:05', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1481, '2020-08-20 15:41:05', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1482, '2020-08-20 15:41:05', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1483, '2020-08-20 15:41:06', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1484, '2020-08-20 15:41:06', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1485, '2020-08-20 15:41:06', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1486, '2020-08-20 15:41:06', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1487, '2020-08-20 15:41:06', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1488, '2020-08-20 15:41:06', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1489, '2020-08-20 15:41:07', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1490, '2020-08-20 15:41:07', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1491, '2020-08-20 15:41:07', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1492, '2020-08-20 15:41:07', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1493, '2020-08-20 15:41:08', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1494, '2020-08-20 15:41:36', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1495, '2020-08-20 15:43:02', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1496, '2020-08-20 15:45:31', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1497, '2020-08-20 15:45:31', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1498, '2020-08-20 15:46:09', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1499, '2020-08-20 15:46:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1500, '2020-08-20 15:46:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1501, '2020-08-20 15:52:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1502, '2020-08-20 15:52:58', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1503, '2020-08-20 15:53:04', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1504, '2020-08-20 15:53:12', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1505, '2020-08-20 15:53:42', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1506, '2020-08-20 15:56:40', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1507, '2020-08-20 16:01:44', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1508, '2020-08-20 16:01:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1509, '2020-08-20 16:04:05', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1510, '2020-08-20 16:05:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1511, '2020-08-20 16:05:44', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1512, '2020-08-20 16:09:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1513, '2020-08-20 16:09:14', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1514, '2020-08-20 16:09:18', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1515, '2020-08-20 16:09:19', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1516, '2020-08-20 16:09:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1517, '2020-08-20 16:09:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1518, '2020-08-20 16:10:05', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1519, '2020-08-20 16:10:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1520, '2020-08-20 16:11:49', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1521, '2020-08-20 16:11:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1522, '2020-08-20 16:14:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1523, '2020-08-20 16:14:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1524, '2020-08-20 16:14:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1525, '2020-08-20 16:14:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1526, '2020-08-20 16:15:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1527, '2020-08-20 16:17:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1528, '2020-08-20 16:17:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1529, '2020-08-20 16:23:31', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1530, '2020-08-20 16:23:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1531, '2020-08-20 16:25:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1532, '2020-08-20 16:26:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1533, '2020-08-20 16:26:18', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1534, '2020-08-20 16:27:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1535, '2020-08-20 16:27:31', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1536, '2020-08-20 16:33:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1539, '2020-08-20 16:46:09', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1542, '2020-08-20 17:27:27', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1543, '2020-08-20 17:27:42', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1545, '2020-08-21 08:35:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1546, '2020-08-21 08:35:50', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1547, '2020-08-21 09:38:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1548, '2020-08-21 09:51:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1549, '2020-08-21 09:56:20', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1550, '2020-08-21 09:56:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1551, '2020-08-21 09:56:53', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1552, '2020-08-21 09:57:03', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1553, '2020-08-21 09:57:18', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1554, '2020-08-21 09:57:30', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1555, '2020-08-21 09:58:29', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1556, '2020-08-21 09:58:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1557, '2020-08-21 10:05:32', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1558, '2020-08-21 10:29:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1559, '2020-08-21 10:30:03', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1560, '2020-08-21 10:30:06', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1561, '2020-08-21 10:30:25', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1562, '2020-08-21 11:05:31', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1563, '2020-08-21 11:11:31', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1564, '2020-08-21 11:11:48', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1565, '2020-08-21 14:06:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1566, '2020-08-21 14:08:17', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1567, '2020-08-21 14:08:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1568, '2020-08-21 15:46:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1569, '2020-08-21 16:10:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1570, '2020-08-21 16:16:52', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1571, '2020-08-21 16:17:07', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1572, '2020-08-22 08:40:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1573, '2020-08-22 08:50:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1574, '2020-08-22 08:50:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1575, '2020-08-22 08:51:08', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1576, '2020-08-22 08:51:55', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1577, '2020-08-22 08:53:19', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1578, '2020-08-22 08:53:55', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1579, '2020-08-22 08:55:06', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1580, '2020-08-22 08:55:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1581, '2020-08-22 09:04:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1582, '2020-08-22 09:38:15', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1583, '2020-08-22 10:01:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1584, '2020-08-22 10:13:01', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1590, '2020-08-22 11:16:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1591, '2020-08-22 14:09:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1592, '2020-08-22 14:10:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1593, '2020-08-22 14:17:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1596, '2020-08-22 14:47:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1600, '2020-08-22 15:11:04', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1601, '2020-08-24 09:12:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1602, '2020-08-24 10:40:08', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1603, '2020-08-24 10:46:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1604, '2020-08-24 11:06:14', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1605, '2020-08-24 14:32:52', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1606, '2020-08-24 14:41:46', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1607, '2020-08-24 15:02:59', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1608, '2020-08-24 15:06:34', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1609, '2020-08-24 15:14:15', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1610, '2020-08-24 15:33:16', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1611, '2020-08-24 15:36:14', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1612, '2020-08-24 15:37:52', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1613, '2020-08-24 17:20:30', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1614, '2020-08-25 08:38:28', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1615, '2020-08-25 08:39:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1616, '2020-08-25 08:39:20', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1617, '2020-08-25 08:42:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1618, '2020-08-25 08:43:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1619, '2020-08-25 08:43:59', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1620, '2020-08-25 08:44:20', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1621, '2020-08-25 08:51:54', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1622, '2020-08-25 08:52:47', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1623, '2020-08-25 08:53:41', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1624, '2020-08-25 08:54:33', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1625, '2020-08-25 08:55:03', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1626, '2020-08-25 08:58:57', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1627, '2020-08-25 08:59:14', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1628, '2020-08-25 09:10:20', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1629, '2020-08-25 09:10:33', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1630, '2020-08-25 09:11:34', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1631, '2020-08-25 09:20:00', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1632, '2020-08-25 10:32:27', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1633, '2020-08-25 10:42:48', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1634, '2020-08-25 10:43:44', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1635, '2020-08-25 10:53:45', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1636, '2020-08-25 10:58:13', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1637, '2020-08-25 11:05:16', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1638, '2020-08-25 11:05:34', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1639, '2020-08-25 11:08:11', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1640, '2020-08-25 13:26:32', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1641, '2020-08-25 14:09:22', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1642, '2020-08-25 14:18:10', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1643, '2020-08-25 14:30:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1644, '2020-08-25 17:00:46', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1645, '2020-08-25 17:16:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1646, '2020-08-25 17:18:54', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1647, '2020-08-25 17:19:11', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1648, '2020-08-25 17:20:40', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1649, '2020-08-25 17:20:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1650, '2020-08-25 17:28:14', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1651, '2020-08-25 17:28:17', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1652, '2020-08-25 17:28:28', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1653, '2020-08-25 17:28:39', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1654, '2020-08-25 17:28:49', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1655, '2020-08-26 08:33:07', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1656, '2020-08-26 08:33:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1657, '2020-08-26 08:33:21', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1658, '2020-08-26 08:59:25', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1659, '2020-08-26 09:40:48', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1660, '2020-08-26 10:18:49', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1662, '2020-08-26 10:22:26', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1666, '2020-08-26 10:25:11', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1668, '2020-08-26 10:25:45', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1673, '2020-08-26 10:29:24', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1676, '2020-08-26 11:25:38', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1677, '2020-08-26 11:38:27', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1678, '2020-08-26 13:45:43', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1681, '2020-08-26 14:08:20', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1682, '2020-08-26 15:09:39', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1683, '2020-08-26 15:48:15', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1684, '2020-08-26 16:45:18', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1686, '2020-08-27 08:58:02', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1697, '2020-08-27 09:33:36', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1698, '2020-08-27 14:13:10', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1699, '2020-08-27 14:30:19', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1700, '2020-08-27 14:59:53', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1701, '2020-08-27 17:03:06', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1702, '2020-08-27 17:27:23', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1703, '2020-08-27 17:31:04', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1704, '2020-08-28 08:44:19', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1705, '2020-08-28 09:03:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1706, '2020-08-28 14:09:20', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1707, '2020-08-28 14:09:29', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1708, '2020-08-28 14:46:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1709, '2020-08-28 15:10:58', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1710, '2020-08-28 15:16:33', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1711, '2020-08-28 17:01:10', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1712, '2020-08-28 17:05:09', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1713, '2020-08-29 09:04:09', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1714, '2020-08-29 09:04:09', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1715, '2020-08-29 09:04:13', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1716, '2020-08-29 09:04:23', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1717, '2020-08-29 09:23:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1718, '2020-08-29 10:30:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1719, '2020-08-31 09:51:39', '172.16.14.8', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1720, '2020-08-31 10:36:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1721, '2020-08-31 15:33:47', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1722, '2020-08-31 18:49:07', '172.16.18.1', '登录日志', NULL, '成功', 869);
INSERT INTO `t_sys_login_log` VALUES (1725, '2020-09-01 08:43:01', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1726, '2020-09-01 08:43:30', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1727, '2020-09-01 09:47:51', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1728, '2020-09-01 09:48:00', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1729, '2020-09-01 10:20:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1730, '2020-09-01 10:49:55', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1731, '2020-09-01 13:38:47', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1732, '2020-09-01 14:04:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1733, '2020-09-01 15:36:48', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1734, '2020-09-02 08:37:57', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1735, '2020-09-02 08:50:20', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1736, '2020-09-02 10:46:11', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1737, '2020-09-02 10:52:22', '172.16.14.7', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1738, '2020-09-02 10:56:26', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1739, '2020-09-02 10:58:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1740, '2020-09-02 10:59:25', '172.16.14.7', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1741, '2020-09-02 11:02:20', '172.16.14.7', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1742, '2020-09-02 11:26:16', '172.16.18.1', '登录日志', NULL, '成功', 869);
INSERT INTO `t_sys_login_log` VALUES (1743, '2020-09-02 13:31:21', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1744, '2020-09-02 14:08:53', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1745, '2020-09-02 14:11:51', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1746, '2020-09-02 15:14:08', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1747, '2020-09-02 16:09:52', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1748, '2020-09-02 17:31:25', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1749, '2020-09-03 08:34:38', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1750, '2020-09-03 10:39:12', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1751, '2020-09-04 09:45:15', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1752, '2020-09-04 09:50:08', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1753, '2020-09-04 10:00:39', '0:0:0:0:0:0:0:1', '登录日志', NULL, '成功', 869);
INSERT INTO `t_sys_login_log` VALUES (1769, '2020-09-04 13:31:01', '0:0:0:0:0:0:0:1', '登录日志', NULL, '成功', 869);
INSERT INTO `t_sys_login_log` VALUES (1770, '2020-09-04 14:52:23', '127.0.0.1', '登录日志', NULL, '成功', 869);
INSERT INTO `t_sys_login_log` VALUES (1771, '2020-09-04 15:42:29', '0:0:0:0:0:0:0:1', '登录日志', NULL, '成功', 869);
INSERT INTO `t_sys_login_log` VALUES (1772, '2020-09-04 19:09:13', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1773, '2020-09-05 08:44:51', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1774, '2020-09-07 11:26:58', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1775, '2020-09-07 11:48:48', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1776, '2020-09-08 09:36:42', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1777, '2020-09-09 11:00:07', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1778, '2020-09-09 13:44:28', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1779, '2020-09-10 09:01:23', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1780, '2020-09-10 10:37:41', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1781, '2020-09-10 10:51:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1782, '2020-09-10 11:26:08', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1783, '2020-09-10 11:29:58', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1784, '2020-09-10 11:31:36', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1785, '2020-09-10 11:34:49', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1786, '2020-09-10 11:34:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1787, '2020-09-10 11:34:57', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1788, '2020-09-10 11:34:58', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1789, '2020-09-10 11:35:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1790, '2020-09-10 11:36:27', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1791, '2020-09-10 11:36:32', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1792, '2020-09-10 11:36:43', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1793, '2020-09-10 11:36:59', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1794, '2020-09-10 11:37:10', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1795, '2020-09-10 11:38:27', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1796, '2020-09-10 11:39:22', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1797, '2020-09-10 11:42:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1798, '2020-09-10 11:42:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1799, '2020-09-10 11:43:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1800, '2020-09-10 11:44:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1801, '2020-09-10 11:44:33', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1802, '2020-09-10 11:44:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1803, '2020-09-10 12:29:25', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1804, '2020-09-10 14:18:34', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1805, '2020-09-10 14:19:31', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1806, '2020-09-10 14:20:47', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1807, '2020-09-10 14:22:54', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1808, '2020-09-10 14:24:27', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1809, '2020-09-10 14:27:29', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1810, '2020-09-10 14:28:04', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1811, '2020-09-10 14:28:15', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1812, '2020-09-10 15:00:20', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1813, '2020-09-10 15:00:40', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1814, '2020-09-10 15:03:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1815, '2020-09-10 15:03:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1816, '2020-09-10 15:04:30', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1817, '2020-09-10 15:11:14', '172.16.14.8', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1818, '2020-09-10 15:12:37', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1819, '2020-09-10 15:13:03', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1820, '2020-09-10 15:13:24', '172.16.14.7', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1821, '2020-09-10 15:17:23', '172.16.14.7', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1822, '2020-09-10 15:18:52', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1823, '2020-09-10 15:19:15', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1824, '2020-09-10 15:19:40', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1825, '2020-09-10 15:21:55', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1826, '2020-09-10 15:27:22', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1827, '2020-09-10 15:28:43', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1828, '2020-09-10 15:29:02', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1829, '2020-09-10 15:40:14', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1830, '2020-09-10 15:40:42', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1831, '2020-09-10 15:41:42', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1832, '2020-09-10 15:43:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1833, '2020-09-10 15:44:24', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1834, '2020-09-10 15:45:20', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1835, '2020-09-10 15:47:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1836, '2020-09-10 15:50:53', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1837, '2020-09-10 15:51:23', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1838, '2020-09-10 15:51:27', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1839, '2020-09-10 15:52:44', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1840, '2020-09-10 15:54:01', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1841, '2020-09-10 15:54:15', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1842, '2020-09-10 15:54:45', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1843, '2020-09-10 15:55:28', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1844, '2020-09-10 15:55:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1845, '2020-09-10 15:55:58', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1846, '2020-09-10 15:56:13', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1847, '2020-09-10 15:56:57', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1848, '2020-09-10 15:57:00', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1849, '2020-09-10 16:00:04', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1850, '2020-09-10 16:00:04', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1851, '2020-09-10 16:10:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1852, '2020-09-10 16:16:27', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1853, '2020-09-10 17:01:47', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1854, '2020-09-10 17:06:30', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1855, '2020-09-10 17:07:42', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1856, '2020-09-10 17:29:43', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1857, '2020-09-10 17:31:49', '172.16.14.8', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1858, '2020-09-11 09:02:00', '172.16.14.8', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1859, '2020-09-11 11:13:51', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1860, '2020-09-11 11:15:05', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1861, '2020-09-11 11:23:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1862, '2020-09-11 11:26:27', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1863, '2020-09-11 11:26:38', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1864, '2020-09-11 11:26:40', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1865, '2020-09-11 11:26:40', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1866, '2020-09-11 11:26:47', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1867, '2020-09-11 11:26:48', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1868, '2020-09-11 11:26:48', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1869, '2020-09-11 11:26:49', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1870, '2020-09-11 11:26:50', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1871, '2020-09-11 11:26:50', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1872, '2020-09-11 11:27:05', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1873, '2020-09-11 11:29:28', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1874, '2020-09-11 14:26:38', '172.16.14.8', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1875, '2020-09-11 15:09:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1876, '2020-09-11 15:14:29', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1877, '2020-09-11 15:14:30', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1878, '2020-09-11 15:14:31', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1879, '2020-09-11 15:14:31', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1880, '2020-09-11 15:14:38', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1881, '2020-09-11 15:14:38', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1882, '2020-09-11 15:14:39', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1883, '2020-09-11 15:14:40', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1884, '2020-09-11 15:14:40', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1885, '2020-09-11 15:14:41', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1886, '2020-09-11 15:14:41', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1887, '2020-09-11 15:14:42', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1888, '2020-09-11 15:14:47', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1889, '2020-09-11 15:14:48', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1890, '2020-09-11 15:14:49', '172.16.14.11', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1891, '2020-09-11 16:33:05', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1892, '2020-09-12 11:41:42', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1893, '2020-09-14 11:46:52', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1894, '2020-09-14 11:46:53', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1895, '2020-09-14 11:47:08', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1896, '2020-09-14 17:12:26', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1897, '2020-09-15 15:40:15', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1898, '2020-09-17 13:38:49', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1899, '2020-09-18 11:33:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1900, '2020-09-21 14:02:59', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1901, '2020-09-21 16:38:20', '172.16.14.2', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1902, '2020-09-22 08:39:12', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1903, '2020-09-22 09:00:39', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1904, '2020-09-22 11:13:32', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1905, '2020-09-22 15:15:50', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1906, '2020-09-23 09:40:32', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1909, '2020-09-23 14:58:47', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1911, '2020-09-24 11:19:25', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1912, '2020-09-24 11:31:25', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1913, '2020-09-24 11:43:58', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1914, '2020-09-24 13:47:39', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1915, '2020-09-24 13:58:07', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1916, '2020-09-24 14:00:48', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1917, '2020-09-24 14:01:42', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1918, '2020-09-24 14:03:07', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1919, '2020-09-24 14:14:22', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1920, '2020-09-24 14:22:27', '172.16.14.7', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1921, '2020-09-24 19:42:32', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1922, '2020-09-25 09:40:05', '172.16.14.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1923, '2020-09-25 15:39:29', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1924, '2020-09-29 17:22:21', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1925, '2020-09-29 17:27:54', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1926, '2020-10-09 10:36:09', '172.16.14.7', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1927, '2020-10-09 15:52:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1930, '2020-10-09 17:19:02', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1931, '2020-10-09 17:19:27', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1932, '2020-10-09 17:22:21', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1933, '2020-10-09 17:23:38', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1934, '2020-10-09 17:24:03', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1935, '2020-10-09 17:24:35', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1936, '2020-10-10 14:27:20', '172.16.24.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1937, '2020-10-10 15:33:13', '172.16.14.3', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1938, '2020-11-02 14:51:50', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1939, '2020-11-02 17:09:34', '127.0.0.1', '登录日志', NULL, '成功', 2);
INSERT INTO `t_sys_login_log` VALUES (1940, '2020-11-03 08:47:41', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1941, '2020-11-03 08:56:37', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1942, '2020-11-03 09:09:59', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1943, '2020-11-03 10:30:30', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1944, '2020-11-05 15:45:01', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1945, '2020-11-10 13:57:40', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1946, '2020-12-01 11:12:16', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1947, '2020-12-01 15:29:48', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1948, '2020-12-01 15:44:08', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1949, '2020-12-25 13:28:18', '127.0.0.1', '登录日志', NULL, '成功', 1);
INSERT INTO `t_sys_login_log` VALUES (1950, '2020-12-30 16:46:27', '127.0.0.1', '登录日志', NULL, '成功', 1);

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `id` bigint(0) NOT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint(0) NULL DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面组件',
  `hidden` tinyint(0) NULL DEFAULT NULL COMMENT '是否隐藏',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `ismenu` int(0) NOT NULL COMMENT '是否是菜单1:菜单,0:按钮',
  `isopen` int(0) NULL DEFAULT NULL COMMENT '是否默认打开1:是,0:否',
  `levels` int(0) NOT NULL COMMENT '级别',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `num` int(0) NOT NULL COMMENT '顺序',
  `pcode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父菜单编号',
  `pcodes` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '递归父级菜单编号',
  `status` int(0) NOT NULL COMMENT '状态1:启用,0:禁用',
  `tips` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '鼠标悬停提示信息',
  `url` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接标识',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_s37unj3gh67ujhk83lqva8i1t`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES (1, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'system', 'layout', 0, 'system', 1, 1, 1, '系统管理', 1, '0', '[0],', 1, NULL, '/system');
INSERT INTO `t_sys_menu` VALUES (2, 1, '2019-07-31 22:04:30', 1, '2019-03-11 22:25:38', 'modelData', 'layout', 0, 'documentation', 1, NULL, 1, '模型数据管理', 3, '0', '[0],', 1, NULL, '/modelDataMgr');
INSERT INTO `t_sys_menu` VALUES (4, 1, '2019-07-31 22:04:30', 1, '2019-04-16 18:59:15', 'mgr', 'views/system/user/index', 0, 'user', 1, NULL, 2, '用户管理', 1, 'system', '[0],[system],', 1, NULL, '/mgr');
INSERT INTO `t_sys_menu` VALUES (5, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'mgrAdd', NULL, 0, NULL, 0, NULL, 3, '添加用户', 1, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/add');
INSERT INTO `t_sys_menu` VALUES (6, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'mgrEdit', NULL, 0, NULL, 0, NULL, 3, '修改用户', 2, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/edit');
INSERT INTO `t_sys_menu` VALUES (7, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'mgrDelete', NULL, 0, NULL, 0, 0, 3, '删除用户', 3, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/delete');
INSERT INTO `t_sys_menu` VALUES (8, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'mgrReset', NULL, 0, NULL, 0, 0, 3, '重置密码', 4, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/reset');
INSERT INTO `t_sys_menu` VALUES (9, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'mgrFreeze', NULL, 0, NULL, 0, 0, 3, '冻结用户', 5, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/freeze');
INSERT INTO `t_sys_menu` VALUES (10, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'mgrUnfreeze', NULL, 0, NULL, 0, 0, 3, '解除冻结用户', 6, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/unfreeze');
INSERT INTO `t_sys_menu` VALUES (11, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'mgrSetRole', NULL, 0, NULL, 0, 0, 3, '分配角色', 7, 'mgr', '[0],[system],[mgr],', 1, NULL, '/mgr/setRole');
INSERT INTO `t_sys_menu` VALUES (12, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'role', 'views/system/role/index', 0, 'peoples', 1, 0, 2, '角色管理', 2, 'system', '[0],[system],', 1, NULL, '/role');
INSERT INTO `t_sys_menu` VALUES (13, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'roleAdd', NULL, 0, NULL, 0, 0, 3, '添加角色', 1, 'role', '[0],[system],[role],', 1, NULL, '/role/add');
INSERT INTO `t_sys_menu` VALUES (14, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'roleEdit', NULL, 0, NULL, 0, 0, 3, '修改角色', 2, 'role', '[0],[system],[role],', 1, NULL, '/role/edit');
INSERT INTO `t_sys_menu` VALUES (15, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'roleDelete', NULL, 0, NULL, 0, 0, 3, '删除角色', 3, 'role', '[0],[system],[role],', 1, NULL, '/role/remove');
INSERT INTO `t_sys_menu` VALUES (16, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'roleSetAuthority', NULL, 0, NULL, 0, 0, 3, '配置权限', 4, 'role', '[0],[system],[role],', 1, NULL, '/role/setAuthority');
INSERT INTO `t_sys_menu` VALUES (17, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'menu', 'views/system/menu/index', 0, 'tree-table', 1, 0, 2, '菜单管理', 4, 'system', '[0],[system],', 1, NULL, '/menu');
INSERT INTO `t_sys_menu` VALUES (18, 1, '2019-07-31 22:04:30', NULL, '2020-07-21 16:04:50', 'menuAdd', '', 0, '', 0, NULL, 3, '添加菜单', 2, 'menu', '[0],[system],[menu],', 1, NULL, '/menu/add');
INSERT INTO `t_sys_menu` VALUES (19, 1, '2019-07-31 22:04:30', NULL, '2020-07-21 16:05:05', 'menuEdit', '', 0, '', 0, NULL, 3, '修改菜单', 3, 'menu', '[0],[system],[menu],', 1, NULL, '/menu/edit');
INSERT INTO `t_sys_menu` VALUES (20, 1, '2019-07-31 22:04:30', NULL, '2020-07-21 16:05:20', 'menuDelete', '', 0, '', 0, NULL, 3, '删除菜单', 4, 'menu', '[0],[system],[menu],', 1, NULL, '/menu/remove');
INSERT INTO `t_sys_menu` VALUES (21, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'dept', 'views/system/dept/index', 0, 'tree', 1, NULL, 2, '部门管理', 3, 'system', '[0],[system],', 1, NULL, '/dept');
INSERT INTO `t_sys_menu` VALUES (22, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'dict', 'views/system/dict/index', 0, 'dict', 1, NULL, 2, '字典管理', 4, 'system', '[0],[system],', 1, '', '/dict');
INSERT INTO `t_sys_menu` VALUES (23, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'deptEdit', NULL, 0, NULL, 0, NULL, 3, '修改部门', 1, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/update');
INSERT INTO `t_sys_menu` VALUES (24, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'deptDelete', NULL, 0, NULL, 0, NULL, 3, '删除部门', 1, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/delete');
INSERT INTO `t_sys_menu` VALUES (25, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'dictAdd', '', 0, '', 0, NULL, 3, '添加字典', 1, 'dict', '[0],[system],[dict],', 1, '', '/dict/add');
INSERT INTO `t_sys_menu` VALUES (26, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'dictEdit', '', 0, '', 0, NULL, 3, '修改字典', 1, 'dict', '[0],[system],[dict],', 1, '', '/dict/update');
INSERT INTO `t_sys_menu` VALUES (27, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'dictDelete', '', 0, '', 0, NULL, 3, '删除字典', 1, 'dict', '[0],[system],[dict],', 1, '', '/dict/delete');
INSERT INTO `t_sys_menu` VALUES (28, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'deptList', NULL, 0, NULL, 0, NULL, 3, '部门列表', 5, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/list');
INSERT INTO `t_sys_menu` VALUES (29, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'deptDetail', NULL, 0, NULL, 0, NULL, 3, '部门详情', 6, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/detail');
INSERT INTO `t_sys_menu` VALUES (32, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'deptAdd', NULL, 0, NULL, 0, NULL, 3, '添加部门', 1, 'dept', '[0],[system],[dept],', 1, NULL, '/dept/add');
INSERT INTO `t_sys_menu` VALUES (41, 1, '2019-03-11 22:29:54', 1, '2019-03-11 22:29:54', 'pipeline', 'views/modeldata/pipeline/index', 0, 'channel', 1, NULL, 2, '管线管理', 1, 'modelData', '[0],[modelData],', 1, NULL, '/pipeline');
INSERT INTO `t_sys_menu` VALUES (43, 1, '2019-03-11 22:30:52', 1, '2019-03-11 22:30:52', 'pipenode', 'views/modeldata/pipenode/index', 0, 'banner', 1, NULL, 2, '管点管理', 3, 'modelData', '[0],[modelData],', 1, NULL, '/pipenode');
INSERT INTO `t_sys_menu` VALUES (66, 1, '2019-07-31 21:51:33', 1, '2019-07-31 21:51:33', 'pipenodeEdit', NULL, 0, NULL, 0, NULL, 3, '编辑管点', 1, 'pipenode', '[0],[modelData],[pipenode],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` VALUES (67, 1, '2019-07-31 21:51:33', 1, '2019-07-31 21:51:33', 'pipenodeDelete', NULL, 0, NULL, 0, NULL, 3, '删除管点', 2, 'pipenode', '[0],[modelData],[pipenode],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` VALUES (68, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'pipelineEdit', NULL, 0, NULL, 0, NULL, 3, '编辑管线', 1, 'pipeline', '[0],[modelData],[pipeline],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` VALUES (69, 1, '2019-07-31 22:04:30', 1, '2019-07-31 22:04:30', 'pipelineDelete', NULL, 0, NULL, 0, NULL, 3, '删除管线', 2, 'pipeline', '[0],[modelData],[pipeline],', 1, NULL, NULL);
INSERT INTO `t_sys_menu` VALUES (70, 1, '2020-07-06 13:46:14', 1, '2020-07-06 13:46:20', 'log', 'views/operation/log/index', 0, 'log', 1, NULL, 2, '业务日志', 6, 'system', '[0],[system],', 1, NULL, '/log');
INSERT INTO `t_sys_menu` VALUES (88, NULL, '2020-07-08 09:45:32', NULL, '2020-07-08 10:26:20', 'company', 'views/modeldata/company/index', 0, 'company', 1, NULL, 2, '企业管理', 3, 'modelData', '[0],[modelData],', 1, NULL, '/company');
INSERT INTO `t_sys_menu` VALUES (89, NULL, '2020-07-08 09:46:41', NULL, '2020-07-08 09:46:41', 'companyEdit', NULL, 0, NULL, 0, NULL, 3, '编辑企业', 1, 'company', '[0],[modelData],[company],', 1, NULL, '/company/edit');
INSERT INTO `t_sys_menu` VALUES (90, NULL, '2020-07-08 09:47:14', NULL, '2020-07-08 09:47:14', 'companyDelete', NULL, 0, NULL, 0, NULL, 3, '删除企业', 2, 'company', '[0],[modelData],[company],', 1, NULL, '/company/delete');
INSERT INTO `t_sys_menu` VALUES (91, 1, '2020-07-09 13:46:14', 1, '2020-07-09 13:46:20', 'loginLog', 'views/operation/loginLog/index', 0, 'logininfor', 1, NULL, 2, '登陆日志', 7, 'system', '[0],[system],', 1, NULL, '/loginLog');
INSERT INTO `t_sys_menu` VALUES (92, 1, '2020-07-10 22:04:30', 1, '2020-07-10 22:04:30', 'loginLogClear', NULL, 0, NULL, 0, NULL, 3, '清空日志', 1, 'loginLog', '[0],[system],[loginLog],', 1, NULL, '/loginLog/delLoginLog');
INSERT INTO `t_sys_menu` VALUES (93, 1, '2020-07-10 22:04:30', 1, '2020-07-10 22:04:30', 'loginLogList', NULL, 0, NULL, 0, NULL, 3, '日志列表', 2, 'loginLog', '[0],[system],[loginLog],', 1, NULL, '/loginLog/list');
INSERT INTO `t_sys_menu` VALUES (936, NULL, '2020-07-21 16:04:05', NULL, '2020-07-21 16:04:05', 'menuList', NULL, 0, NULL, 0, NULL, 3, '菜单列表', 1, 'menu', '[0],[system],[menu],', 1, NULL, '/menu/list');

-- ----------------------------
-- Table structure for t_sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_operation_log`;
CREATE TABLE `t_sys_operation_log`  (
  `id` bigint(0) NOT NULL,
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详细信息',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_operation_log
-- ----------------------------
INSERT INTO `t_sys_operation_log` VALUES (69, 'com.suchness.realscene.api.controller.PipeLineController', '2020-08-08 16:48:37', '修改管线', '业务日志', '修改管线: 10345', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (70, 'com.suchness.realscene.api.controller.PipeLineController', '2020-08-08 16:48:43', '修改管线', '业务日志', '修改管线: 10345', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (71, 'com.suchness.realscene.api.controller.PipeLineController', '2020-08-08 16:58:41', '修改管线', '业务日志', 'null=null;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (86, 'com.suchness.realscene.api.controller.system.UserController', '2020-08-10 15:28:53', '冻结/解冻账号', '业务日志', '账号=aaaa', 'changeStatus', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (87, 'com.suchness.realscene.api.controller.system.UserController', '2020-08-10 15:28:56', '冻结/解冻账号', '业务日志', '账号=aaaa', 'changeStatus', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (143, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 09:04:44', '编辑角色', '业务日志', '角色名称=吴长昊;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (183, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 11:41:22', '编辑角色', '业务日志', '角色名称=吴长昊;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (186, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 13:31:06', '编辑角色', '业务日志', '角色名称=吴长昊;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (187, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 13:34:51', '编辑角色', '业务日志', '角色名称=吴长昊;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (192, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 13:58:30', '编辑角色', '业务日志', '角色名称=网站管理员;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (193, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 13:58:43', '编辑角色', '业务日志', '角色名称=网站管理员;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (197, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 14:02:03', '编辑角色', '业务日志', '角色名称=test;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (198, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 14:02:16', '编辑角色', '业务日志', '角色名称=test;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (199, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 14:02:20', '删除角色', '业务日志', 'null=null', 'remove', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (218, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 14:56:42', '编辑角色', '业务日志', '角色名称=222222222222222222222222222222222222222222222222222222;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (230, 'com.suchness.realscene.api.controller.RoleController', '2020-08-11 15:30:45', '删除角色', '业务日志', 'null=null', 'remove', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1008, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-14 10:39:04', '编辑部门', '业务日志', '部门简称=bbbb;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1020, 'com.suchness.realscene.api.controller.system.DictController', '2020-08-14 16:24:22', '修改字典', '业务日志', '字典名称=材质类型;;;', 'update', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1343, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 11:03:34', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1345, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 11:04:00', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1347, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 11:04:42', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1349, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 11:09:48', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1351, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 11:10:06', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1358, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 11:34:13', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1362, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 12:33:36', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1363, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 12:33:50', '删除部门', '业务日志', '部门名称=1361', 'remove', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1366, 'com.suchness.realscene.api.controller.RoleController', '2020-08-19 15:18:07', '编辑角色', '业务日志', '角色名称=吴长昊;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1367, 'com.suchness.realscene.api.controller.RoleController', '2020-08-19 15:18:13', '删除角色', '业务日志', 'null=null', 'remove', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1369, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 15:23:03', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1370, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 15:23:06', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1371, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 15:23:09', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1372, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 15:23:21', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1373, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 15:23:32', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1380, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 17:08:46', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1381, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 17:08:50', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1382, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-19 17:08:55', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1537, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-20 16:39:02', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1538, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-20 16:40:20', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1541, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-20 16:48:21', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1544, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-20 17:29:03', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1585, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-22 10:13:38', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1586, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-22 10:13:47', '编辑部门', '业务日志', '部门简称=aaaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1588, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-22 10:13:50', '编辑部门', '业务日志', '部门简称=aaaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1589, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-22 10:13:55', '删除部门', '业务日志', '部门名称=1587', 'remove', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1594, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-22 14:25:57', '编辑部门', '业务日志', '部门简称=运营部;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1595, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-22 14:27:35', '编辑部门', '业务日志', '部门简称=运营部;;;字段名称:上级名称,旧值:总公司,新值:行政部', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1598, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-22 14:47:21', '编辑部门', '业务日志', '部门简称=财务部;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1599, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-22 15:04:58', '编辑部门', '业务日志', '部门简称=财务部;;;字段名称:上级名称,旧值:战略部,新值:市场部', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1661, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:18:58', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1663, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:22:35', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1664, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:22:50', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1665, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:25:07', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1667, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:25:19', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1669, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:25:51', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1670, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:25:58', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1671, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:26:22', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1672, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:28:00', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1674, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:39:28', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1675, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 10:58:27', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1679, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 13:45:55', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1680, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 13:46:03', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1685, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-26 17:00:35', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1687, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-27 09:04:19', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1688, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-27 09:05:39', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1689, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-27 09:09:05', '编辑部门', '业务日志', '部门简称=aaa;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1696, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-08-27 09:13:49', '编辑部门', '业务日志', '部门简称=cccc;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1723, 'com.suchness.realscene.api.controller.system.UserController', '2020-08-31 18:51:22', '冻结/解冻账号', '业务日志', '账号=admin', 'changeStatus', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1724, 'com.suchness.realscene.api.controller.system.UserController', '2020-08-31 18:51:23', '冻结/解冻账号', '业务日志', '账号=admin', 'changeStatus', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1754, 'com.suchness.realscene.api.controller.system.UserController', '2020-09-04 10:01:46', '编辑账号', '业务日志', '名字=测试12;;;', 'save', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1755, 'com.suchness.realscene.api.controller.system.UserController', '2020-09-04 10:01:53', '编辑账号', '业务日志', '名字=测试12;;;', 'save', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1756, 'com.suchness.realscene.api.controller.system.UserController', '2020-09-04 10:02:17', '设置账号角色', '业务日志', '账号=aaaa', 'setRole', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1757, 'com.suchness.realscene.api.controller.system.UserController', '2020-09-04 10:02:39', '设置账号角色', '业务日志', '账号=test2', 'setRole', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1759, 'com.suchness.realscene.api.controller.system.RoleController', '2020-09-04 10:04:52', '编辑角色', '业务日志', '角色名称=qqqq;;;', 'save', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1760, 'com.suchness.realscene.api.controller.system.RoleController', '2020-09-04 10:05:17', '删除角色', '业务日志', 'null=null', 'remove', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1761, 'com.suchness.realscene.api.controller.system.RoleController', '2020-09-04 10:06:28', '编辑角色', '业务日志', '角色名称=吴长昊;;;', 'save', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1762, 'com.suchness.realscene.api.controller.system.RoleController', '2020-09-04 10:06:46', '编辑角色', '业务日志', '角色名称=吴长昊;;;', 'save', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1765, 'com.suchness.realscene.api.controller.system.DictController', '2020-09-04 10:08:30', '添加字典', '业务日志', '字典名称=的;;;', 'add', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1766, 'com.suchness.realscene.api.controller.system.DictController', '2020-09-04 10:08:40', '删除字典', '业务日志', '主键id=1763', 'delete', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1767, 'com.suchness.realscene.api.controller.system.UserController', '2020-09-04 10:10:37', '冻结/解冻账号', '业务日志', '账号=admin', 'changeStatus', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1768, 'com.suchness.realscene.api.controller.system.UserController', '2020-09-04 10:10:39', '冻结/解冻账号', '业务日志', '账号=admin', 'changeStatus', '成功', 869);
INSERT INTO `t_sys_operation_log` VALUES (1908, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-09-23 09:48:28', '编辑部门', '业务日志', '部门简称=ccccq;;;', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1910, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-09-23 15:00:01', '编辑部门', '业务日志', '部门简称=bbbb;;;字段名称:上级名称,旧值:,新值:asdfasdf', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1928, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-10-09 15:53:37', '编辑部门', '业务日志', '部门简称=ddd;;;字段名称:上级名称,旧值:,新值:ddd', 'save', '成功', 1);
INSERT INTO `t_sys_operation_log` VALUES (1929, 'com.suchness.realscene.api.controller.system.DeptContoller', '2020-10-09 16:10:04', '编辑部门', '业务日志', '部门简称=cccc;;;字段名称:上级名称,旧值:,新值:ccc', 'save', '成功', 1);

-- ----------------------------
-- Table structure for t_sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_relation`;
CREATE TABLE `t_sys_relation`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(0) NULL DEFAULT NULL,
  `roleid` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4628 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单角色关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_relation
-- ----------------------------
INSERT INTO `t_sys_relation` VALUES (131, 1, 1);
INSERT INTO `t_sys_relation` VALUES (132, 4, 1);
INSERT INTO `t_sys_relation` VALUES (133, 5, 1);
INSERT INTO `t_sys_relation` VALUES (134, 6, 1);
INSERT INTO `t_sys_relation` VALUES (135, 7, 1);
INSERT INTO `t_sys_relation` VALUES (136, 8, 1);
INSERT INTO `t_sys_relation` VALUES (137, 9, 1);
INSERT INTO `t_sys_relation` VALUES (138, 10, 1);
INSERT INTO `t_sys_relation` VALUES (139, 11, 1);
INSERT INTO `t_sys_relation` VALUES (140, 12, 1);
INSERT INTO `t_sys_relation` VALUES (141, 13, 1);
INSERT INTO `t_sys_relation` VALUES (142, 14, 1);
INSERT INTO `t_sys_relation` VALUES (143, 15, 1);
INSERT INTO `t_sys_relation` VALUES (144, 16, 1);
INSERT INTO `t_sys_relation` VALUES (145, 17, 1);
INSERT INTO `t_sys_relation` VALUES (146, 18, 1);
INSERT INTO `t_sys_relation` VALUES (147, 19, 1);
INSERT INTO `t_sys_relation` VALUES (148, 20, 1);
INSERT INTO `t_sys_relation` VALUES (149, 21, 1);
INSERT INTO `t_sys_relation` VALUES (150, 23, 1);
INSERT INTO `t_sys_relation` VALUES (151, 24, 1);
INSERT INTO `t_sys_relation` VALUES (152, 28, 1);
INSERT INTO `t_sys_relation` VALUES (153, 29, 1);
INSERT INTO `t_sys_relation` VALUES (154, 32, 1);
INSERT INTO `t_sys_relation` VALUES (155, 22, 1);
INSERT INTO `t_sys_relation` VALUES (156, 25, 1);
INSERT INTO `t_sys_relation` VALUES (157, 26, 1);
INSERT INTO `t_sys_relation` VALUES (158, 27, 1);
INSERT INTO `t_sys_relation` VALUES (159, 70, 1);
INSERT INTO `t_sys_relation` VALUES (160, 2, 1);
INSERT INTO `t_sys_relation` VALUES (161, 41, 1);
INSERT INTO `t_sys_relation` VALUES (162, 68, 1);
INSERT INTO `t_sys_relation` VALUES (163, 69, 1);
INSERT INTO `t_sys_relation` VALUES (164, 43, 1);
INSERT INTO `t_sys_relation` VALUES (165, 66, 1);
INSERT INTO `t_sys_relation` VALUES (166, 67, 1);
INSERT INTO `t_sys_relation` VALUES (167, 88, 1);
INSERT INTO `t_sys_relation` VALUES (168, 89, 1);
INSERT INTO `t_sys_relation` VALUES (169, 90, 1);
INSERT INTO `t_sys_relation` VALUES (681, 91, 1);
INSERT INTO `t_sys_relation` VALUES (682, 92, 1);
INSERT INTO `t_sys_relation` VALUES (683, 93, 1);
INSERT INTO `t_sys_relation` VALUES (937, 1, 2);
INSERT INTO `t_sys_relation` VALUES (938, 4, 2);
INSERT INTO `t_sys_relation` VALUES (939, 5, 2);
INSERT INTO `t_sys_relation` VALUES (940, 6, 2);
INSERT INTO `t_sys_relation` VALUES (941, 7, 2);
INSERT INTO `t_sys_relation` VALUES (942, 8, 2);
INSERT INTO `t_sys_relation` VALUES (943, 9, 2);
INSERT INTO `t_sys_relation` VALUES (944, 10, 2);
INSERT INTO `t_sys_relation` VALUES (945, 11, 2);
INSERT INTO `t_sys_relation` VALUES (946, 12, 2);
INSERT INTO `t_sys_relation` VALUES (947, 13, 2);
INSERT INTO `t_sys_relation` VALUES (948, 14, 2);
INSERT INTO `t_sys_relation` VALUES (949, 15, 2);
INSERT INTO `t_sys_relation` VALUES (950, 16, 2);
INSERT INTO `t_sys_relation` VALUES (951, 17, 2);
INSERT INTO `t_sys_relation` VALUES (952, 936, 2);
INSERT INTO `t_sys_relation` VALUES (953, 21, 2);
INSERT INTO `t_sys_relation` VALUES (954, 23, 2);
INSERT INTO `t_sys_relation` VALUES (955, 24, 2);
INSERT INTO `t_sys_relation` VALUES (956, 28, 2);
INSERT INTO `t_sys_relation` VALUES (957, 29, 2);
INSERT INTO `t_sys_relation` VALUES (958, 32, 2);
INSERT INTO `t_sys_relation` VALUES (959, 22, 2);
INSERT INTO `t_sys_relation` VALUES (960, 25, 2);
INSERT INTO `t_sys_relation` VALUES (961, 26, 2);
INSERT INTO `t_sys_relation` VALUES (962, 27, 2);
INSERT INTO `t_sys_relation` VALUES (963, 70, 2);
INSERT INTO `t_sys_relation` VALUES (964, 91, 2);
INSERT INTO `t_sys_relation` VALUES (965, 92, 2);
INSERT INTO `t_sys_relation` VALUES (966, 93, 2);
INSERT INTO `t_sys_relation` VALUES (967, 2, 2);
INSERT INTO `t_sys_relation` VALUES (968, 41, 2);
INSERT INTO `t_sys_relation` VALUES (969, 68, 2);
INSERT INTO `t_sys_relation` VALUES (970, 69, 2);
INSERT INTO `t_sys_relation` VALUES (971, 43, 2);
INSERT INTO `t_sys_relation` VALUES (972, 66, 2);
INSERT INTO `t_sys_relation` VALUES (973, 67, 2);
INSERT INTO `t_sys_relation` VALUES (974, 88, 2);
INSERT INTO `t_sys_relation` VALUES (975, 89, 2);
INSERT INTO `t_sys_relation` VALUES (976, 90, 2);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint(0) NOT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint(0) NULL DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `deptid` bigint(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  `pid` bigint(0) NULL DEFAULT NULL,
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `version` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, NULL, NULL, NULL, '2020-07-21 14:55:20', 1, '超级管理员', 1, 0, 'administrator', NULL);
INSERT INTO `t_sys_role` VALUES (2, NULL, NULL, NULL, '2020-08-11 13:58:43', 6, '网站管理员', 1, 1005, 'developer', NULL);
INSERT INTO `t_sys_role` VALUES (1005, NULL, '2020-07-23 10:31:31', NULL, '2020-09-04 10:06:46', 1, '吴长昊', 2, 1, 'wch', NULL);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint(0) NOT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint(0) NULL DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL,
  `deptid` bigint(0) NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'email',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `roleid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id列表，以逗号分隔',
  `salt` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码盐',
  `sex` int(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `version` int(0) NULL DEFAULT NULL,
  `address` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, NULL, '2016-01-29 08:49:53', 1, '2020-09-04 10:10:39', 'admin', NULL, '2017-05-05 00:00:00', 2, 'eniluzt@qq.com', '超级管理员', 'b5a51391f271f062867e5984e2fcffee', '15021222222', '1', '8pgby', 2, 1, 25, NULL);
INSERT INTO `t_sys_user` VALUES (2, NULL, '2018-09-13 17:21:02', 1, '2020-08-25 11:05:30', 'developer', NULL, '2017-12-31 00:00:00', 4, 'eniluzt@qq.com', '网站管理员', 'fac36d5616fe9ebd460691264b28ee27', '15022222222', '2,', 'vscp9', 1, 1, NULL, NULL);
INSERT INTO `t_sys_user` VALUES (50, NULL, '2020-08-08 14:21:24', NULL, '2020-09-04 10:02:17', 'aaaa', NULL, NULL, 7, '', 'aaa', '53c4c542635c1fefc17ef391642a6010', '18965315987', '1005,function(t){if(this.length){var e=this.indexOf(t);return e>-1?this.splice(e,1):void 0}},', 'im6c2', 1, 1, NULL, NULL);
INSERT INTO `t_sys_user` VALUES (869, NULL, '2020-07-21 13:44:12', NULL, '2020-09-04 10:01:53', 'test', NULL, '2020-07-21 00:00:00', 994, '55335@qq.com', '测试12', '5a602d98f87bb1aa71b1090d5a9e3a46', '15896321236', '2,', 'tkl9r', 1, 1, NULL, NULL);
INSERT INTO `t_sys_user` VALUES (871, NULL, '2020-07-21 13:51:56', NULL, '2020-07-21 14:43:07', 'test1', NULL, NULL, 3, 'asdfadsf@qq.com', '测试2', '1d7423f187b1fcbe585b5b08ab5e5201', '18963216987', '2,', 'cebkn', 1, 3, NULL, NULL);
INSERT INTO `t_sys_user` VALUES (980, NULL, '2020-07-21 16:47:22', NULL, '2020-09-04 10:02:39', 'test2', NULL, NULL, 1, 'asdf@qq.com', '测试股', '900335bf6fd7b6698efd7c5bf684654d', '1896365989', '1,function(t){if(this.length){var e=this.indexOf(t);return e>-1?this.splice(e,1):void 0}},', 'tnhub', 1, 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

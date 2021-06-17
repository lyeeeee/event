/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : optical_fiber_system

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 03/06/2021 21:40:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attribute_relation_complex_event
-- ----------------------------
DROP TABLE IF EXISTS `attribute_relation_complex_event`;
CREATE TABLE `attribute_relation_complex_event`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attribute_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attribute_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `complex_event_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `meta_event_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `attribute_relation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `and_or_not` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `meta_attribute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attribute_relation_complex_event
-- ----------------------------
INSERT INTO `attribute_relation_complex_event` VALUES (5, '71属性', '71', '1', NULL, '1', '2', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (11, NULL, NULL, '5', '3', '0', NULL, NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (19, '6618123', '1', '12', NULL, '1', '1', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (16, NULL, NULL, '1', '5', '0', NULL, NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (9, '测试71', '777', '2', NULL, '1', '4', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (10, NULL, NULL, '5', '4', '0', NULL, NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (15, NULL, NULL, '1', '3', '0', NULL, NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (14, '1111', '111', '1', NULL, '1', '1', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (20, '目标1', '10', '12', NULL, '2', '4', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (18, NULL, NULL, '1', '2', '0', NULL, NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (21, '属性1', '0', '12', NULL, '1', '2', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (23, '目标2', '50', '12', NULL, '2', '0', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (27, 'subsites', '5', '15', NULL, '0', '4', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (25, '流量', '100', '14', NULL, '2', '3', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (26, '车速', '20', '14', NULL, '1', '1', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (30, '234234', '1', '12', NULL, '2', '2', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (29, 'equipment', '50', '15', NULL, '0', '1', NULL, NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (33, '01', '5', '15', NULL, '2', '1', '2', NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (34, '00', '10', '15', NULL, '2', '4', '1', NULL);
INSERT INTO `attribute_relation_complex_event` VALUES (37, '6618', '11', '15', NULL, '1', '2', NULL, NULL);

-- ----------------------------
-- Table structure for attribute_relation_equipment
-- ----------------------------
DROP TABLE IF EXISTS `attribute_relation_equipment`;
CREATE TABLE `attribute_relation_equipment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attribute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `equipment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `graph_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `iot_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subsites` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subsystem` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attribute_relation_equipment
-- ----------------------------
INSERT INTO `attribute_relation_equipment` VALUES (1, 'http://www.w3.org/ns/sosa/光频_环内拍频信号A1.2', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.2', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_环内拍频信号A1.2');
INSERT INTO `attribute_relation_equipment` VALUES (2, 'http://www.w3.org/ns/sosa/光频_电源状态A1.2', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.2', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_电源状态A1.2');
INSERT INTO `attribute_relation_equipment` VALUES (3, 'http://www.w3.org/ns/sosa/光频_输入功率A1.2', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.2', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输入功率A1.2');
INSERT INTO `attribute_relation_equipment` VALUES (4, 'http://www.w3.org/ns/sosa/光频_输出功率A1.2', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.2', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输出功率A1.2');
INSERT INTO `attribute_relation_equipment` VALUES (5, 'http://www.w3.org/ns/sosa/光频_链路锁定状态A1.2', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.2', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_链路锁定状态A1.2');
INSERT INTO `attribute_relation_equipment` VALUES (6, 'http://www.w3.org/ns/sosa/光频_环内拍频信号A1.3', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.3', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_环内拍频信号A1.3');
INSERT INTO `attribute_relation_equipment` VALUES (7, 'http://www.w3.org/ns/sosa/光频_电源状态A1.3', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.3', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_电源状态A1.3');
INSERT INTO `attribute_relation_equipment` VALUES (8, 'http://www.w3.org/ns/sosa/光频_输入功率A1.3', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.3', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输入功率A1.3');
INSERT INTO `attribute_relation_equipment` VALUES (9, 'http://www.w3.org/ns/sosa/光频_输出功率A1.3', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.3', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输出功率A1.3');
INSERT INTO `attribute_relation_equipment` VALUES (10, 'http://www.w3.org/ns/sosa/光频_链路锁定状态A1.3', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.3', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_链路锁定状态A1.3');
INSERT INTO `attribute_relation_equipment` VALUES (11, 'http://www.w3.org/ns/sosa/光频_环内拍频信号A1.5', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.5', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_环内拍频信号A1.5');
INSERT INTO `attribute_relation_equipment` VALUES (12, 'http://www.w3.org/ns/sosa/光频_电源状态A1.5', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.5', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_电源状态A1.5');
INSERT INTO `attribute_relation_equipment` VALUES (13, 'http://www.w3.org/ns/sosa/光频_输入功率A1.5', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.5', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输入功率A1.5');
INSERT INTO `attribute_relation_equipment` VALUES (14, 'http://www.w3.org/ns/sosa/光频_输出功率A1.5', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.5', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输出功率A1.5');
INSERT INTO `attribute_relation_equipment` VALUES (15, 'http://www.w3.org/ns/sosa/光频_链路锁定状态A1.5', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.5', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_链路锁定状态A1.5');
INSERT INTO `attribute_relation_equipment` VALUES (16, 'http://www.w3.org/ns/sosa/光频_环内拍频信号A1.6', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.6', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_环内拍频信号A1.6');
INSERT INTO `attribute_relation_equipment` VALUES (17, 'http://www.w3.org/ns/sosa/光频_电源状态A1.6', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.6', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_电源状态A1.6');
INSERT INTO `attribute_relation_equipment` VALUES (18, 'http://www.w3.org/ns/sosa/光频_输入功率A1.6', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.6', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输入功率A1.6');
INSERT INTO `attribute_relation_equipment` VALUES (19, 'http://www.w3.org/ns/sosa/光频_输出功率A1.6', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.6', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输出功率A1.6');
INSERT INTO `attribute_relation_equipment` VALUES (20, 'http://www.w3.org/ns/sosa/光频_链路锁定状态A1.6', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.6', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_链路锁定状态A1.6');
INSERT INTO `attribute_relation_equipment` VALUES (21, 'http://www.w3.org/ns/sosa/光频_光源锁定状态A1.1', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_光源锁定状态A1.1');
INSERT INTO `attribute_relation_equipment` VALUES (22, 'http://www.w3.org/ns/sosa/光频_环境温度A1.1', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_环境温度A1.1');
INSERT INTO `attribute_relation_equipment` VALUES (23, 'http://www.w3.org/ns/sosa/光频_电源状态A1.1', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_电源状态A1.1');
INSERT INTO `attribute_relation_equipment` VALUES (24, 'http://www.w3.org/ns/sosa/光频_离子泵电流A1.1', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_离子泵电流A1.1');
INSERT INTO `attribute_relation_equipment` VALUES (25, 'http://www.w3.org/ns/sosa/光频_输出功率A1.1', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输出功率A1.1');
INSERT INTO `attribute_relation_equipment` VALUES (26, 'http://www.w3.org/ns/sosa/光频_透射峰电压A1.1', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_透射峰电压A1.1');
INSERT INTO `attribute_relation_equipment` VALUES (27, 'http://www.w3.org/ns/sosa/光频_光频传递指标A1.4', 'http://www.w3.org/ns/sosa/拍频比对设备A1.4', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_光频传递指标A1.4');
INSERT INTO `attribute_relation_equipment` VALUES (28, 'http://www.w3.org/ns/sosa/光频_相位噪声A1.4', 'http://www.w3.org/ns/sosa/拍频比对设备A1.4', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_相位噪声A1.4');
INSERT INTO `attribute_relation_equipment` VALUES (29, 'http://www.w3.org/ns/sosa/光频_频率稳定度A1.4', 'http://www.w3.org/ns/sosa/拍频比对设备A1.4', 'http://6625', '授时系统', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_频率稳定度A1.4');

-- ----------------------------
-- Table structure for attribute_relation_meta_event
-- ----------------------------
DROP TABLE IF EXISTS `attribute_relation_meta_event`;
CREATE TABLE `attribute_relation_meta_event`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attribute` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `equipment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `iot_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `meta_event_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subsites` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subsystem` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `graph_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attribute_relation_meta_event
-- ----------------------------
INSERT INTO `attribute_relation_meta_event` VALUES (3, 'http://www.w3.org/ns/sosa/光频_电源状态A1.6', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.6', '授时系统', '4', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_链路锁定状态A1.6', 'http://6625');
INSERT INTO `attribute_relation_meta_event` VALUES (4, 'http://www.w3.org/ns/sosa/光频_电源状态A1.2', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.2', '授时系统', '4', 'http://www.w3.org/ns/sosa/一级站西安', 'http://www.w3.org/ns/sosa/光频_输入功率A1.2', 'http://6625');

-- ----------------------------
-- Table structure for complex_event
-- ----------------------------
DROP TABLE IF EXISTS `complex_event`;
CREATE TABLE `complex_event`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insert_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `synopsis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `meta_event_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `meta_event_range` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complex_event
-- ----------------------------
INSERT INTO `complex_event` VALUES (13, '2019-07-10 14:19:10', '710复杂事件', '710复杂事件710', '子站', 'http://www.w3.org/ns/sosa/一级站西安');
INSERT INTO `complex_event` VALUES (14, '2019-07-10 15:03:01', '拥堵测算', '测算拥堵情况', '子系统', 'http://www.w3.org/ns/sosa/光频_频率稳定度A1.4');
INSERT INTO `complex_event` VALUES (12, '2019-07-10 14:18:23', '710测试1', '重中之重1', '设备', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1');
INSERT INTO `complex_event` VALUES (15, '2019-07-11 09:41:11', '711', '7411111', NULL, NULL);

-- ----------------------------
-- Table structure for databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog`  (
  `ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FILENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DATEEXECUTED` datetime(0) NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `MD5SUM` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `COMMENTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `TAG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LIQUIBASE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CONTEXTS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LABELS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of databasechangelog
-- ----------------------------
INSERT INTO `databasechangelog` VALUES ('00000000000001', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', '2019-05-13 13:49:22', 1, 'EXECUTED', '7:4d85236a8667651805fef548ed3c68ea', 'createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...', '', NULL, '3.5.4', NULL, NULL, '7726561705');

-- ----------------------------
-- Table structure for databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock`  (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
  `LOCKEDBY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of databasechangeloglock
-- ----------------------------
INSERT INTO `databasechangeloglock` VALUES (1, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for jhi_authority
-- ----------------------------
DROP TABLE IF EXISTS `jhi_authority`;
CREATE TABLE `jhi_authority`  (
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jhi_authority
-- ----------------------------
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN');
INSERT INTO `jhi_authority` VALUES ('ROLE_USER');

-- ----------------------------
-- Table structure for jhi_persistent_audit_event
-- ----------------------------
DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
CREATE TABLE `jhi_persistent_audit_event`  (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `event_date` timestamp(0) NULL DEFAULT NULL,
  `event_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`) USING BTREE,
  INDEX `idx_persistent_audit_event`(`principal`, `event_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jhi_persistent_audit_event
-- ----------------------------
INSERT INTO `jhi_persistent_audit_event` VALUES (1, 'admin', '2019-05-13 06:19:08', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (2, 'admin', '2019-05-13 06:43:34', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (3, 'admin', '2019-05-13 06:47:30', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (4, 'admin', '2019-05-13 06:47:30', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (5, 'admin', '2019-05-13 06:49:25', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (6, 'admin', '2019-05-13 07:46:44', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (7, 'admin', '2019-05-13 07:49:24', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (8, 'admin1', '2019-05-13 07:50:57', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (9, 'admin1', '2019-05-13 07:50:57', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (10, 'admin', '2019-05-13 07:51:40', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (11, 'admin', '2019-05-13 08:13:40', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (12, 'admin', '2019-05-13 08:16:08', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (13, 'admin', '2019-05-13 08:22:01', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (14, 'admin', '2019-05-13 08:22:01', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (15, 'admin', '2019-05-13 08:22:06', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (16, 'admin', '2019-05-13 09:27:29', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (17, 'admin', '2019-05-13 10:01:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (18, 'admin', '2019-05-14 02:10:09', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (19, 'admin', '2019-05-14 02:10:35', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (20, 'admin', '2019-05-14 02:14:06', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (21, 'admin', '2019-05-14 02:35:44', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (22, 'admin', '2019-05-14 02:41:24', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (23, 'admin', '2019-05-14 02:41:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (24, 'admin', '2019-05-14 02:42:13', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (25, 'admin', '2019-05-14 02:43:36', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (26, 'admin', '2019-05-14 06:39:59', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (27, 'admin', '2019-05-16 06:21:38', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (28, 'admin', '2019-05-21 01:51:44', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (29, 'admin', '2019-05-21 05:50:19', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (30, 'admin', '2019-05-21 10:42:00', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (31, 'admin', '2019-05-22 02:39:37', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (32, 'admin', '2019-05-22 06:43:11', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (33, 'admin', '2019-05-24 01:45:38', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (34, 'admin', '2019-05-27 01:26:48', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (35, 'admin', '2019-05-27 01:35:09', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (36, 'admin', '2019-05-27 02:52:04', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (37, 'admin', '2019-05-30 06:34:46', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (38, 'admin', '2019-06-03 01:58:20', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (39, 'user', '2019-06-03 09:59:04', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (40, 'admin', '2019-06-03 09:59:16', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (41, '123123123', '2019-06-03 10:01:39', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (42, '123123123', '2019-06-03 10:01:39', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (43, '123123123', '2019-06-03 10:01:44', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (44, '123123123', '2019-06-03 10:01:44', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (45, 'admin', '2019-06-03 10:01:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (46, 'admin', '2019-06-03 10:31:30', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (47, 'admin', '2019-06-05 01:34:56', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (48, 'admin', '2019-06-05 02:49:59', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (49, 'admin', '2019-06-05 07:21:35', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (50, 'admin', '2019-06-10 00:59:38', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (51, 'admin', '2019-06-10 07:17:31', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (52, 'admin', '2019-06-12 01:43:12', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (53, 'admin', '2019-06-13 01:46:44', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (54, 'admin', '2019-06-13 01:46:44', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (55, 'admin', '2019-06-13 01:46:46', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (56, 'admin', '2019-06-17 06:10:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (57, 'admin', '2019-06-18 06:16:54', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (58, 'admin', '2019-06-18 07:12:11', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (59, 'admin', '2019-06-20 06:36:56', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (60, 'admin', '2019-06-25 01:37:19', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (61, 'admin', '2019-06-25 06:02:56', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (62, 'admin', '2019-06-26 06:20:03', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (63, 'admin', '2019-07-01 01:55:01', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (64, 'admin', '2019-07-02 06:26:12', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (65, 'admin', '2019-07-03 06:28:05', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (66, 'admin', '2019-07-04 06:28:54', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (67, 'admin', '2019-07-10 06:08:01', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (68, 'admin', '2019-07-12 06:34:10', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (69, 'admin', '2019-07-15 05:38:16', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (70, 'admin', '2019-07-15 06:16:35', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (71, 'admin', '2019-07-16 02:59:11', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (72, 'admin', '2019-07-16 06:36:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (73, 'admin', '2019-09-09 03:20:49', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (74, 'admin', '2019-09-11 01:37:39', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (75, 'admin', '2019-09-16 01:47:00', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (76, 'admin', '2019-09-18 01:33:27', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (77, 'admin', '2019-10-10 08:47:49', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (78, 'admin', '2019-10-15 11:55:31', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (79, 'admin', '2019-10-21 01:42:34', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (80, 'admin', '2019-10-25 01:43:05', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (81, 'admin', '2019-10-28 02:47:42', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (82, 'admin', '2019-11-01 07:56:23', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (83, 'admin', '2019-11-02 08:14:23', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (84, 'admin', '2019-11-04 02:34:47', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (85, 'admin', '2019-11-14 02:21:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (86, 'admin', '2019-12-26 09:18:00', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (87, 'admin', '2019-12-26 09:18:00', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (88, 'admin', '2019-12-26 09:18:02', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (89, 'admin', '2019-12-26 09:18:02', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (90, 'admin', '2019-12-26 09:18:05', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (91, 'admin', '2020-03-03 13:24:16', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (92, 'admin', '2020-03-05 13:03:20', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (93, 'admin', '2020-03-07 02:27:52', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (94, 'admin', '2020-03-08 02:28:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (95, 'admin', '2020-03-09 05:45:49', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (96, 'admin', '2020-03-10 07:30:53', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (97, 'admin', '2020-03-11 07:33:07', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (98, 'admin', '2020-03-14 08:25:41', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (99, 'admin', '2020-03-19 01:52:00', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (100, 'admin', '2020-03-21 02:48:08', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (101, 'admin', '2020-03-22 04:25:01', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (102, 'admin', '2020-03-23 09:54:55', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (103, 'admin', '2020-03-24 10:24:06', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (104, 'admin', '2020-03-28 14:19:04', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (105, 'admin', '2020-03-30 02:07:17', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (106, 'admin', '2020-03-31 03:16:04', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (107, 'admin', '2020-03-31 03:16:05', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (108, 'admin', '2020-04-04 02:46:36', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (109, 'admin', '2020-04-06 02:20:47', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (110, 'admin', '2020-04-07 03:00:36', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (111, 'admin', '2020-04-08 03:01:38', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (112, 'admin', '2020-04-10 10:08:02', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (113, 'admin', '2020-04-11 11:33:02', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (114, 'admin', '2020-04-15 06:14:57', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (115, 'admin', '2020-04-17 01:59:33', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (116, 'admin', '2020-04-20 05:33:07', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (117, 'admin', '2020-04-21 06:53:38', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (118, 'admin', '2020-04-22 07:37:38', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (119, 'admin', '2020-04-25 08:30:32', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (120, 'admin', '2020-04-27 02:39:05', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (121, 'admin', '2020-04-28 03:29:22', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (122, 'admin', '2020-04-29 06:00:11', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (123, 'admin', '2020-04-30 06:41:28', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (124, 'admin', '2020-05-02 02:50:46', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (125, 'admin', '2020-05-06 04:16:04', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (126, 'admin', '2020-05-08 03:04:20', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (127, 'admin', '2020-05-11 02:09:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (128, 'admin', '2020-05-13 06:30:39', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (129, 'admin', '2020-05-15 02:01:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (130, 'admin', '2020-05-16 14:41:18', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (131, 'admin', '2020-05-18 08:17:47', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (132, 'admin', '2020-05-20 03:36:14', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (133, 'admin', '2020-05-20 13:06:40', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (134, 'admin', '2020-05-22 14:54:02', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (135, 'admin', '2020-05-24 12:52:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (136, 'admin', '2020-06-03 07:53:22', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (137, 'admin', '2020-06-04 02:28:43', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (138, 'admin', '2020-06-09 15:18:34', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (139, 'admin', '2020-06-13 14:14:56', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (140, 'admin', '2020-06-21 14:50:36', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (141, 'admin', '2020-09-21 02:23:21', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (142, 'admin', '2020-10-12 09:05:30', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (143, 'admin', '2020-10-26 07:22:07', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (144, 'admin', '2020-10-27 07:31:53', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (145, 'admin', '2020-11-01 09:07:52', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (146, 'admin', '2020-11-02 09:13:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (147, 'admin', '2020-11-04 06:54:13', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (148, 'admin', '2020-11-09 13:38:30', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (149, 'admin', '2020-11-11 02:58:02', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (150, 'admin', '2020-11-17 07:18:45', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (151, 'admin', '2020-11-18 07:33:53', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (152, 'admin', '2020-11-22 03:35:00', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (153, 'admin', '2020-12-13 12:59:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (154, 'admin', '2020-12-16 06:57:42', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (155, 'admin', '2020-12-23 02:07:45', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (156, 'admin', '2021-01-14 11:53:15', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (157, 'admin', '2021-01-16 09:26:03', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (158, 'admin', '2021-01-17 09:28:27', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (159, 'admin', '2021-01-18 09:34:12', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (160, 'admin', '2021-01-20 06:17:26', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (161, 'admin', '2021-01-26 12:32:44', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (162, 'admin', '2021-01-29 09:24:54', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (163, 'admin', '2021-02-13 01:13:56', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (164, 'admin', '2021-02-15 08:03:41', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (165, 'admin', '2021-02-18 05:50:53', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (166, 'admin', '2021-02-21 03:27:54', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (167, 'admin', '2021-03-01 06:23:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (168, 'admin', '2021-03-08 06:51:54', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (169, 'admin', '2021-04-04 03:32:01', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (170, 'admin', '2021-04-04 03:32:02', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (171, 'admin', '2021-04-04 03:32:10', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (172, 'admin', '2021-04-05 14:51:50', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (173, 'admin', '2021-04-12 15:39:32', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (174, 'admin', '2021-04-18 02:13:55', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (175, 'admin', '2021-04-21 12:54:52', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (176, 'admin', '2021-04-21 12:59:04', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (177, 'admin', '2021-04-21 13:03:34', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (178, 'admin', '2021-04-21 13:05:18', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (179, 'admin', '2021-04-21 13:05:47', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (180, 'admin', '2021-04-21 13:07:42', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (181, 'admin', '2021-04-21 13:14:59', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (182, 'admin', '2021-04-21 13:19:08', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (183, 'admin', '2021-04-21 13:20:43', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (184, 'liyi', '2021-04-21 13:26:50', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (185, 'liyi', '2021-04-21 13:26:50', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (186, 'liyi', '2021-04-21 13:28:56', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (187, 'liyi', '2021-04-21 13:28:56', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (188, 'liyi', '2021-04-21 13:29:05', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (189, 'liyi', '2021-04-21 13:29:05', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (190, 'admin', '2021-04-21 13:29:19', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (191, 'admin', '2021-04-21 13:29:47', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (192, 'admin', '2021-04-21 13:31:37', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (193, 'admin', '2021-04-29 14:05:32', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (194, 'admin', '2021-04-29 14:06:16', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (195, 'admin', '2021-04-29 14:09:48', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (196, 'admin', '2021-04-29 14:13:53', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (197, 'admin', '2021-05-05 14:45:09', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (198, 'admin', '2021-05-05 14:48:34', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (199, 'admin', '2021-05-12 08:16:32', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (200, 'admin', '2021-05-18 13:29:03', 'AUTHENTICATION_SUCCESS');

-- ----------------------------
-- Table structure for jhi_persistent_audit_evt_data
-- ----------------------------
DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
CREATE TABLE `jhi_persistent_audit_evt_data`  (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`, `name`) USING BTREE,
  INDEX `idx_persistent_audit_evt_data`(`event_id`) USING BTREE,
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jhi_persistent_audit_evt_data
-- ----------------------------
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (3, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (3, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (4, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (4, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (8, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (8, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (9, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (9, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (13, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (13, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (14, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (14, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (41, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (41, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (42, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (42, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (43, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (43, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (44, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (44, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (53, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (53, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (54, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (54, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (86, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (86, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (87, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (87, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (88, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (88, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (89, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (89, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (169, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (169, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (170, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (170, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (184, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (184, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (185, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (185, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (186, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (186, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (187, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (187, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (188, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (188, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (189, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (189, 'type', 'org.springframework.security.authentication.BadCredentialsException');

-- ----------------------------
-- Table structure for jhi_user
-- ----------------------------
DROP TABLE IF EXISTS `jhi_user`;
CREATE TABLE `jhi_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password_hash` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activation_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reset_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_date` timestamp(0) NULL DEFAULT NULL,
  `reset_date` timestamp(0) NULL DEFAULT NULL,
  `last_modified_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_modified_date` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_user_login`(`login`) USING BTREE,
  UNIQUE INDEX `ux_user_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jhi_user
-- ----------------------------
INSERT INTO `jhi_user` VALUES (1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', '', b'1', 'zh-cn', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO `jhi_user` VALUES (2, 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', '', b'1', 'zh-cn', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO `jhi_user` VALUES (3, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', '', b'1', 'zh-cn', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO `jhi_user` VALUES (4, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '', b'1', 'zh-cn', NULL, NULL, 'system', NULL, NULL, 'system', NULL);

-- ----------------------------
-- Table structure for jhi_user_authority
-- ----------------------------
DROP TABLE IF EXISTS `jhi_user_authority`;
CREATE TABLE `jhi_user_authority`  (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`user_id`, `authority_name`) USING BTREE,
  INDEX `fk_authority_name`(`authority_name`) USING BTREE,
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jhi_user_authority
-- ----------------------------
INSERT INTO `jhi_user_authority` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `jhi_user_authority` VALUES (3, 'ROLE_ADMIN');
INSERT INTO `jhi_user_authority` VALUES (1, 'ROLE_USER');
INSERT INTO `jhi_user_authority` VALUES (3, 'ROLE_USER');
INSERT INTO `jhi_user_authority` VALUES (4, 'ROLE_USER');

-- ----------------------------
-- Table structure for knowledge_attribute_relation_meta_event
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_attribute_relation_meta_event`;
CREATE TABLE `knowledge_attribute_relation_meta_event`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `knowledge_attribute` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `knowledge_attribute_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `meta_event_id` bigint(20) NULL DEFAULT NULL,
  `topic_attribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `topic_attribute_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `device_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_attribute_relation_meta_event
-- ----------------------------
INSERT INTO `knowledge_attribute_relation_meta_event` VALUES (18, 'http://www.w3.org/ns/sosa/光频_输入功率', 'double ', 25, 'optical_input_power ', 'double ', 'EDFA设备', '输入光功率');
INSERT INTO `knowledge_attribute_relation_meta_event` VALUES (19, 'http://www.w3.org/ns/sosa/光频_输出功率', 'double ', 26, 'optical_output_power ', 'double ', 'EDFA设备', '输出光功率');
INSERT INTO `knowledge_attribute_relation_meta_event` VALUES (24, 'http://www.w3.org/ns/sosa/光频_环内拍频信号', 'double ', 32, 'optical_paipin_signal ', 'double ', '光频发送设备', '拍频信号');

-- ----------------------------
-- Table structure for knowledge_complex_event
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_complex_event`;
CREATE TABLE `knowledge_complex_event`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relation` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_relation` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_relation` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id_target_relation` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_complex_event
-- ----------------------------
INSERT INTO `knowledge_complex_event` VALUES (6, '光纤完整度事件', '判断A-B链路光纤完整度的复杂事件', '((光频输入事件-optical_input_power >-20)&(光频输入事件-optical_input_power <-10))&((光频输出事件-optical_output_power >0)&(光频输出事件-optical_output_power <10))', '((光频输入事件-optical_input_power >-13)|(光频输入事件-optical_input_power <-18))&((光频输出事件-optical_output_power >=1)&(光频输出事件-optical_output_power <=4))', '((23)&(24))&((25)&(26))', '((15)|(16))&((17)&(18))');
INSERT INTO `knowledge_complex_event` VALUES (11, '测试', '测试', '(光频输入事件-optical_input_power <10)&(光频输出事件-optical_output_power >-20)', '(光频输入事件-optical_input_power <3)&(光频输出事件-optical_output_power >-4)', '(43)&(44)', '(29)&(30)');

-- ----------------------------
-- Table structure for knowledge_complex_event_alarm
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_complex_event_alarm`;
CREATE TABLE `knowledge_complex_event_alarm`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sys` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统',
  `complex_event` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复杂事件',
  `complex_event_synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复杂事件说明',
  `site` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_complex_event_alarm
-- ----------------------------
INSERT INTO `knowledge_complex_event_alarm` VALUES (38, '1', '1', '1', '1', '1');
INSERT INTO `knowledge_complex_event_alarm` VALUES (39, '光纤授时光频系统', '光纤完整度事件', '判断A-B链路光纤完整度的复杂事件', '西安', 'Fri Apr 30 14:33:24 CST 2021');

-- ----------------------------
-- Table structure for knowledge_complex_subevent
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_complex_subevent`;
CREATE TABLE `knowledge_complex_subevent`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subevent_range` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subevent_id` bigint(20) NULL DEFAULT NULL,
  `subevent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `complex_event_id` bigint(20) NULL DEFAULT NULL,
  `attr_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attribute_relation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relation_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_complex_subevent
-- ----------------------------
INSERT INTO `knowledge_complex_subevent` VALUES (23, '西安', 25, '光频输入事件-optical_input_power >-20', 6, 'optical_input_power ', '4', '-20');
INSERT INTO `knowledge_complex_subevent` VALUES (24, '西安', 25, '光频输入事件-optical_input_power <20', 6, 'optical_input_power ', '0', '20');
INSERT INTO `knowledge_complex_subevent` VALUES (25, '西安', 26, '光频输出事件-optical_output_power >-20', 6, 'optical_output_power ', '4', '-20');
INSERT INTO `knowledge_complex_subevent` VALUES (26, '西安', 26, '光频输出事件-optical_output_power <20', 6, 'optical_output_power ', '0', '20');
INSERT INTO `knowledge_complex_subevent` VALUES (43, '北京', 25, '光频输入事件-optical_input_power <10', 11, 'optical_input_power ', '0', '10');
INSERT INTO `knowledge_complex_subevent` VALUES (44, '北京', 26, '光频输出事件-optical_output_power >-20', 11, 'optical_output_power ', '4', '-20');

-- ----------------------------
-- Table structure for knowledge_complex_subevent_relation
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_complex_subevent_relation`;
CREATE TABLE `knowledge_complex_subevent_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insert_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relation_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `complex_event_id` bigint(20) NULL DEFAULT NULL,
  `relation_id_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_complex_subevent_relation
-- ----------------------------
INSERT INTO `knowledge_complex_subevent_relation` VALUES (26, 'Thu Apr 30 19:18:16 CST 2020', '光频输入事件-optical_input_power >-20', 6, '23', 0);
INSERT INTO `knowledge_complex_subevent_relation` VALUES (27, 'Thu Apr 30 19:18:34 CST 2020', '光频输入事件-optical_input_power <20', 6, '24', 0);
INSERT INTO `knowledge_complex_subevent_relation` VALUES (28, 'Thu Apr 30 19:19:09 CST 2020', '光频输出事件-optical_output_power >-20', 6, '25', 0);
INSERT INTO `knowledge_complex_subevent_relation` VALUES (29, 'Thu Apr 30 19:19:25 CST 2020', '光频输出事件-optical_output_power <20', 6, '26', 0);
INSERT INTO `knowledge_complex_subevent_relation` VALUES (30, 'Thu Apr 30 19:21:11 CST 2020', '(光频输入事件-optical_input_power >-20)&(光频输入事件-optical_input_power <-10)', 6, '(23)&(24)', 1);
INSERT INTO `knowledge_complex_subevent_relation` VALUES (31, 'Thu Apr 30 19:21:30 CST 2020', '(光频输出事件-optical_output_power >0)&(光频输出事件-optical_output_power <10)', 6, '(25)&(26)', 1);
INSERT INTO `knowledge_complex_subevent_relation` VALUES (32, 'Thu Apr 30 19:21:47 CST 2020', '((光频输入事件-optical_input_power >-20)&(光频输入事件-optical_input_power <-10))&((光频输出事件-optical_output_power >0)&(光频输出事件-optical_output_power <10))', 6, '((23)&(24))&((25)&(26))', 1);
INSERT INTO `knowledge_complex_subevent_relation` VALUES (55, 'Mon Mar 08 15:27:10 CST 2021', '光频输入事件-optical_input_power <10', 11, '43', 0);
INSERT INTO `knowledge_complex_subevent_relation` VALUES (56, 'Mon Mar 08 15:27:30 CST 2021', '光频输出事件-optical_output_power >-20', 11, '44', 0);
INSERT INTO `knowledge_complex_subevent_relation` VALUES (57, 'Mon Mar 08 15:28:29 CST 2021', '(光频输入事件-optical_input_power <10)&(光频输出事件-optical_output_power >-20)', 11, '(43)&(44)', 1);

-- ----------------------------
-- Table structure for knowledge_complex_target
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_complex_target`;
CREATE TABLE `knowledge_complex_target`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subevent_id` bigint(20) NULL DEFAULT NULL,
  `subevent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `complex_event_id` bigint(20) NULL DEFAULT NULL,
  `attr_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attribute_relation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relation_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time_window` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `len_window` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_complex_target
-- ----------------------------
INSERT INTO `knowledge_complex_target` VALUES (15, 25, '光频输入事件-optical_input_power >-13', 6, 'optical_input_power ', '4', '-13', '1', NULL);
INSERT INTO `knowledge_complex_target` VALUES (16, 25, '光频输入事件-optical_input_power <-18', 6, 'optical_input_power ', '0', '-18', '1', NULL);
INSERT INTO `knowledge_complex_target` VALUES (17, 26, '光频输出事件-optical_output_power >=1', 6, 'optical_output_power ', '3', '1', '1', NULL);
INSERT INTO `knowledge_complex_target` VALUES (18, 26, '光频输出事件-optical_output_power <=4', 6, 'optical_output_power ', '1', '4', '1', NULL);
INSERT INTO `knowledge_complex_target` VALUES (29, 25, '光频输入事件-optical_input_power <3', 11, 'optical_input_power ', '0', '3', '10', NULL);
INSERT INTO `knowledge_complex_target` VALUES (30, 26, '光频输出事件-optical_output_power >-4', 11, 'optical_output_power ', '4', '-4', '10', NULL);

-- ----------------------------
-- Table structure for knowledge_complex_target_relation
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_complex_target_relation`;
CREATE TABLE `knowledge_complex_target_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insert_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relation_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `complex_event_id` bigint(20) NULL DEFAULT NULL,
  `target_id_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_complex_target_relation
-- ----------------------------
INSERT INTO `knowledge_complex_target_relation` VALUES (16, 'Thu Apr 30 19:24:16 CST 2020', '光频输入事件-optical_input_power >-13', 6, '15', 0);
INSERT INTO `knowledge_complex_target_relation` VALUES (17, 'Thu Apr 30 19:24:41 CST 2020', '光频输入事件-optical_input_power <-18', 6, '16', 0);
INSERT INTO `knowledge_complex_target_relation` VALUES (18, 'Thu Apr 30 19:25:20 CST 2020', '光频输出事件-optical_output_power >=1', 6, '17', 0);
INSERT INTO `knowledge_complex_target_relation` VALUES (19, 'Thu Apr 30 19:25:38 CST 2020', '光频输出事件-optical_output_power <=4', 6, '18', 0);
INSERT INTO `knowledge_complex_target_relation` VALUES (33, 'Fri May 15 22:48:44 CST 2020', '(光频输入事件-optical_input_power >-13)|(光频输入事件-optical_input_power <-18)', 6, '(15)|(16)', 1);
INSERT INTO `knowledge_complex_target_relation` VALUES (34, 'Fri May 15 22:48:52 CST 2020', '(光频输出事件-optical_output_power >=1)&(光频输出事件-optical_output_power <=4)', 6, '(17)&(18)', 1);
INSERT INTO `knowledge_complex_target_relation` VALUES (35, 'Fri May 15 22:48:59 CST 2020', '((光频输入事件-optical_input_power >-13)|(光频输入事件-optical_input_power <-18))&((光频输出事件-optical_output_power >=1)&(光频输出事件-optical_output_power <=4))', 6, '((15)|(16))&((17)&(18))', 1);
INSERT INTO `knowledge_complex_target_relation` VALUES (54, 'Mon Mar 08 15:29:43 CST 2021', '光频输入事件-optical_input_power <3', 11, '29', 0);
INSERT INTO `knowledge_complex_target_relation` VALUES (55, 'Mon Mar 08 15:30:00 CST 2021', '光频输出事件-optical_output_power >-4', 11, '30', 0);
INSERT INTO `knowledge_complex_target_relation` VALUES (56, 'Mon Mar 08 15:30:32 CST 2021', '(光频输入事件-optical_input_power <3)&(光频输出事件-optical_output_power >-4)', 11, '(29)&(30)', 1);

-- ----------------------------
-- Table structure for knowledge_directory_management
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_directory_management`;
CREATE TABLE `knowledge_directory_management`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insert_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKqwvduqc5p2d1y2m9tha5v9unc`(`parent_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_directory_management
-- ----------------------------
INSERT INTO `knowledge_directory_management` VALUES (1, '2019-05-27 09:35:29', '领域1', '01', NULL);
INSERT INTO `knowledge_directory_management` VALUES (2, '2019-05-27 09:35:37', '领域2', '01', NULL);
INSERT INTO `knowledge_directory_management` VALUES (4, '2019-05-27 09:36:03', '领域3', '01', NULL);
INSERT INTO `knowledge_directory_management` VALUES (5, '2019-05-27 09:37:42', '部门11', '02', 1);
INSERT INTO `knowledge_directory_management` VALUES (6, '2019-05-27 09:36:30', '部门21', '02', 2);
INSERT INTO `knowledge_directory_management` VALUES (7, '2019-05-27 09:36:38', '部门22', '02', 2);
INSERT INTO `knowledge_directory_management` VALUES (8, '2019-05-27 09:37:36', '部门12', '02', 1);
INSERT INTO `knowledge_directory_management` VALUES (12, '2019-06-03 10:15:20', '11122', '03', 6);
INSERT INTO `knowledge_directory_management` VALUES (13, '2019-05-27 16:14:03', '元目录121', '03', 8);
INSERT INTO `knowledge_directory_management` VALUES (14, '2019-06-03 10:14:27', '11111q21', '03', 5);
INSERT INTO `knowledge_directory_management` VALUES (16, '2019-06-05 10:25:28', '524524zzz', '12', NULL);

-- ----------------------------
-- Table structure for knowledge_directory_node
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_directory_node`;
CREATE TABLE `knowledge_directory_node`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `n_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `attributes` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `owner` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 419 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_directory_node
-- ----------------------------
INSERT INTO `knowledge_directory_node` VALUES (1, '西安', 11, '', 'knowledge_class', '西安');
INSERT INTO `knowledge_directory_node` VALUES (2, '授时系统', 1, '', 'knowledge_class', '授时系统');
INSERT INTO `knowledge_directory_node` VALUES (9, '武汉', 11, '', 'knowledge_class', '武汉');
INSERT INTO `knowledge_directory_node` VALUES (11, 'root', -1, NULL, 'knowledge_class', 'root');
INSERT INTO `knowledge_directory_node` VALUES (13, '北京', 11, '', 'knowledge_class', '北京');
INSERT INTO `knowledge_directory_node` VALUES (185, 'knowledge', 2, NULL, 'knowledge_class', 'http://www.w3.org/ns/sosa/光纤光频传递分系统设备');
INSERT INTO `knowledge_directory_node` VALUES (186, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/再生光中继器');
INSERT INTO `knowledge_directory_node` VALUES (187, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元');
INSERT INTO `knowledge_directory_node` VALUES (188, 'knowledge_instance', 185, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1');
INSERT INTO `knowledge_directory_node` VALUES (189, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/双向EDFA设备');
INSERT INTO `knowledge_directory_node` VALUES (190, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/监控单元');
INSERT INTO `knowledge_directory_node` VALUES (191, 'knowledge_instance', 185, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/监控单元A1.5');
INSERT INTO `knowledge_directory_node` VALUES (192, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光频传递系统发送设备');
INSERT INTO `knowledge_directory_node` VALUES (193, 'knowledge_instance', 185, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.3');
INSERT INTO `knowledge_directory_node` VALUES (194, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/拍频比对设备');
INSERT INTO `knowledge_directory_node` VALUES (195, 'knowledge_instance', 185, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/拍频比对设备A1.2');
INSERT INTO `knowledge_directory_node` VALUES (196, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光纤稳频激光器单元');
INSERT INTO `knowledge_directory_node` VALUES (197, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光频光纤多路由切换设备');
INSERT INTO `knowledge_directory_node` VALUES (198, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光频传递系统接收设备');
INSERT INTO `knowledge_directory_node` VALUES (199, 'knowledge_instance', 185, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.4');
INSERT INTO `knowledge_directory_node` VALUES (200, 'knowledge', 185, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/EDFA中继器');
INSERT INTO `knowledge_directory_node` VALUES (201, 'knowledge', 2, NULL, 'knowledge_class', 'http://www.w3.org/ns/sosa/微波频率传递分系统设备');
INSERT INTO `knowledge_directory_node` VALUES (202, 'knowledge', 201, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/微波光纤多路由切换设备');
INSERT INTO `knowledge_directory_node` VALUES (203, 'knowledge', 201, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/信号净化设备');
INSERT INTO `knowledge_directory_node` VALUES (204, 'knowledge', 201, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/频率基准源子系统单元');
INSERT INTO `knowledge_directory_node` VALUES (205, 'knowledge', 201, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/相噪分析模块');
INSERT INTO `knowledge_directory_node` VALUES (206, 'knowledge', 201, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/同步子系统单元');
INSERT INTO `knowledge_directory_node` VALUES (207, 'knowledge', 201, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/微波频率合成器发射和接收设备');
INSERT INTO `knowledge_directory_node` VALUES (208, 'knowledge', 2, NULL, 'knowledge_class', 'http://www.w3.org/ns/sosa/光纤时间同步分系统设备');
INSERT INTO `knowledge_directory_node` VALUES (209, 'knowledge', 208, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/测试设备');
INSERT INTO `knowledge_directory_node` VALUES (210, 'knowledge', 208, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/时间光纤多路由切换设备');
INSERT INTO `knowledge_directory_node` VALUES (211, 'knowledge', 208, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/多通道无缝切换设备');
INSERT INTO `knowledge_directory_node` VALUES (212, 'knowledge', 208, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/信号再生净化设备');
INSERT INTO `knowledge_directory_node` VALUES (213, 'knowledge', 208, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光纤时间同步本地端设备');
INSERT INTO `knowledge_directory_node` VALUES (214, 'knowledge', 208, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光纤时间同步远程端设备');
INSERT INTO `knowledge_directory_node` VALUES (215, 'knowledge', 208, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/三通道无缝切换设备');
INSERT INTO `knowledge_directory_node` VALUES (216, 'knowledge', 208, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/同步网内实时闭环运控监控');
INSERT INTO `knowledge_directory_node` VALUES (217, 'knowledge', 208, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/守时钟驯服控制设备');
INSERT INTO `knowledge_directory_node` VALUES (218, '授时系统', 9, '', 'knowledge_class', '授时系统');
INSERT INTO `knowledge_directory_node` VALUES (219, '授时系统', 13, '', 'knowledge_class', '授时系统');
INSERT INTO `knowledge_directory_node` VALUES (387, 'knowledge', 219, NULL, 'knowledge_class', 'http://www.w3.org/ns/sosa/光纤光频传递分系统设备');
INSERT INTO `knowledge_directory_node` VALUES (388, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/再生光中继器');
INSERT INTO `knowledge_directory_node` VALUES (389, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元');
INSERT INTO `knowledge_directory_node` VALUES (390, 'knowledge_instance', 387, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1');
INSERT INTO `knowledge_directory_node` VALUES (391, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/双向EDFA设备');
INSERT INTO `knowledge_directory_node` VALUES (392, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/监控单元');
INSERT INTO `knowledge_directory_node` VALUES (393, 'knowledge_instance', 387, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/监控单元A1.5');
INSERT INTO `knowledge_directory_node` VALUES (394, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光频传递系统发送设备');
INSERT INTO `knowledge_directory_node` VALUES (395, 'knowledge_instance', 387, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.3');
INSERT INTO `knowledge_directory_node` VALUES (396, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/拍频比对设备');
INSERT INTO `knowledge_directory_node` VALUES (397, 'knowledge_instance', 387, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/拍频比对设备A1.2');
INSERT INTO `knowledge_directory_node` VALUES (398, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光纤稳频激光器单元');
INSERT INTO `knowledge_directory_node` VALUES (399, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光频光纤多路由切换设备');
INSERT INTO `knowledge_directory_node` VALUES (400, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光频传递系统接收设备');
INSERT INTO `knowledge_directory_node` VALUES (401, 'knowledge_instance', 387, NULL, 'knowledge_instance', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.4');
INSERT INTO `knowledge_directory_node` VALUES (402, 'knowledge', 387, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/EDFA中继器');
INSERT INTO `knowledge_directory_node` VALUES (403, 'knowledge', 219, NULL, 'knowledge_class', 'http://www.w3.org/ns/sosa/微波频率传递分系统设备');
INSERT INTO `knowledge_directory_node` VALUES (404, 'knowledge', 403, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/微波光纤多路由切换设备');
INSERT INTO `knowledge_directory_node` VALUES (405, 'knowledge', 403, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/信号净化设备');
INSERT INTO `knowledge_directory_node` VALUES (406, 'knowledge', 403, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/频率基准源子系统单元');
INSERT INTO `knowledge_directory_node` VALUES (407, 'knowledge', 403, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/相噪分析模块');
INSERT INTO `knowledge_directory_node` VALUES (408, 'knowledge', 403, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/同步子系统单元');
INSERT INTO `knowledge_directory_node` VALUES (409, 'knowledge', 403, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/微波频率合成器发射和接收设备');
INSERT INTO `knowledge_directory_node` VALUES (410, 'knowledge', 219, NULL, 'knowledge_class', 'http://www.w3.org/ns/sosa/光纤时间同步分系统设备');
INSERT INTO `knowledge_directory_node` VALUES (411, 'knowledge', 410, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/测试设备');
INSERT INTO `knowledge_directory_node` VALUES (412, 'knowledge', 410, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/时间光纤多路由切换设备');
INSERT INTO `knowledge_directory_node` VALUES (413, 'knowledge', 410, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/多通道无缝切换设备');
INSERT INTO `knowledge_directory_node` VALUES (414, 'knowledge', 410, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/信号再生净化设备');
INSERT INTO `knowledge_directory_node` VALUES (415, 'knowledge', 410, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光纤时间同步本地端设备');
INSERT INTO `knowledge_directory_node` VALUES (416, 'knowledge', 410, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/光纤时间同步远程端设备');
INSERT INTO `knowledge_directory_node` VALUES (417, 'knowledge', 410, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/三通道无缝切换设备');
INSERT INTO `knowledge_directory_node` VALUES (418, 'knowledge', 410, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/同步网内实时闭环运控监控');
INSERT INTO `knowledge_directory_node` VALUES (419, 'knowledge', 410, NULL, 'knowledge', 'http://www.w3.org/ns/sosa/守时钟驯服控制设备');

-- ----------------------------
-- Table structure for knowledge_file
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_file`;
CREATE TABLE `knowledge_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识模型名称',
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识文件名称',
  `file_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识文件路径',
  `graph_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识图url',
  `tdb_model_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tdb知识模型名称',
  `knowledge_synopsis` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识模型描述',
  `db_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'TDB数据库路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_file
-- ----------------------------
INSERT INTO `knowledge_file` VALUES (22, 'model1', '西安.owl', 'ontology/', 'model1', 'model1', 'model1', 'dbData');
INSERT INTO `knowledge_file` VALUES (28, '北京', '北京.owl', 'ontology/', 'test', '北京', 'test', 'dbData');

-- ----------------------------
-- Table structure for knowledge_fomula
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_fomula`;
CREATE TABLE `knowledge_fomula`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公式名称',
  `is_global` int(11) NULL DEFAULT NULL COMMENT '是否全局',
  `is_complete` int(11) NULL DEFAULT NULL COMMENT '是否完整',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公式内容',
  `var_knowledge_r` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '变量公式关系',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_fomula
-- ----------------------------
INSERT INTO `knowledge_fomula` VALUES (10, '光频稳定度公式', 1, 0, '<math xmlns=\"http://www.w3.org/1998/Math/MathML\"><msqrt><mrow><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><msup><mi>y</mi><mn>3</mn></msup><mo>-</mo><msup><mi>z</mi><mn>2</mn></msup></mrow></msqrt></math>', 'x=http://www.w3.org/ns/sosa/光频_输入功率\ny=http://www.w3.org/ns/sosa/光频_输出功率\nz=http://www.w3.org/ns/sosa/光频_透射峰电压');
INSERT INTO `knowledge_fomula` VALUES (11, 'tests', 1, 1, '<math xmlns=\"http://www.w3.org/1998/Math/MathML\"><msqrt><mrow><msup><mi>x</mi><mn>2</mn></msup><mo>+</mo><msup><mi>y</mi><mn>2</mn></msup></mrow></msqrt></math>', 'x=http://www.w3.org/ns/sosa/光频_输入功率\ny=http://www.w3.org/ns/sosa/光频_输出功率');

-- ----------------------------
-- Table structure for knowledge_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_knowledge`;
CREATE TABLE `knowledge_knowledge`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `knowledge_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识名称',
  `knowledge_uri` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识uri',
  `knowldege_file_id` bigint(20) NULL DEFAULT NULL COMMENT '知识文件id',
  `knowledge_dir` bigint(20) NULL DEFAULT NULL COMMENT '知识目录id',
  `type` int(11) NULL DEFAULT NULL COMMENT '知识类型，0 知识类， 1知识实例',
  `knowledge_class_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识类型id（仅在是实例的时候才有）',
  `dir_node_id` bigint(20) NULL DEFAULT NULL,
  `model_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 330 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_knowledge
-- ----------------------------
INSERT INTO `knowledge_knowledge` VALUES (121, '再生光中继器', 'http://www.w3.org/ns/sosa/再生光中继器', 22, 185, 0, NULL, 186, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (122, '参考腔稳频激光器单元', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元', 22, 185, 0, NULL, 187, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (123, '参考腔稳频激光器单元A1.1', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1', 22, 185, 1, '122', 188, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (124, '双向EDFA设备', 'http://www.w3.org/ns/sosa/双向EDFA设备', 22, 185, 0, NULL, 189, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (125, '监控单元', 'http://www.w3.org/ns/sosa/监控单元', 22, 185, 0, NULL, 190, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (126, '监控单元A1.5', 'http://www.w3.org/ns/sosa/监控单元A1.5', 22, 185, 1, '125', 191, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (127, '光频传递系统发送设备', 'http://www.w3.org/ns/sosa/光频传递系统发送设备', 22, 185, 0, NULL, 192, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (128, '光频传递系统发送设备A1.3', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.3', 22, 185, 1, '127', 193, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (129, '拍频比对设备', 'http://www.w3.org/ns/sosa/拍频比对设备', 22, 185, 0, NULL, 194, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (130, '拍频比对设备A1.2', 'http://www.w3.org/ns/sosa/拍频比对设备A1.2', 22, 185, 1, '129', 195, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (131, '光纤稳频激光器单元', 'http://www.w3.org/ns/sosa/光纤稳频激光器单元', 22, 185, 0, NULL, 196, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (132, '光频光纤多路由切换设备', 'http://www.w3.org/ns/sosa/光频光纤多路由切换设备', 22, 185, 0, NULL, 197, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (133, '光频传递系统接收设备', 'http://www.w3.org/ns/sosa/光频传递系统接收设备', 22, 185, 0, NULL, 198, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (134, '光频传递系统接收设备A1.4', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.4', 22, 185, 1, '133', 199, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (135, 'EDFA中继器', 'http://www.w3.org/ns/sosa/EDFA中继器', 22, 185, 0, NULL, 200, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (136, '微波光纤多路由切换设备', 'http://www.w3.org/ns/sosa/微波光纤多路由切换设备', 22, 201, 0, NULL, 202, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (137, '信号净化设备', 'http://www.w3.org/ns/sosa/信号净化设备', 22, 201, 0, NULL, 203, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (138, '频率基准源子系统单元', 'http://www.w3.org/ns/sosa/频率基准源子系统单元', 22, 201, 0, NULL, 204, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (139, '相噪分析模块', 'http://www.w3.org/ns/sosa/相噪分析模块', 22, 201, 0, NULL, 205, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (140, '同步子系统单元', 'http://www.w3.org/ns/sosa/同步子系统单元', 22, 201, 0, NULL, 206, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (141, '微波频率合成器发射和接收设备', 'http://www.w3.org/ns/sosa/微波频率合成器发射和接收设备', 22, 201, 0, NULL, 207, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (142, '测试设备', 'http://www.w3.org/ns/sosa/测试设备', 22, 208, 0, NULL, 209, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (143, '时间光纤多路由切换设备', 'http://www.w3.org/ns/sosa/时间光纤多路由切换设备', 22, 208, 0, NULL, 210, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (144, '多通道无缝切换设备', 'http://www.w3.org/ns/sosa/多通道无缝切换设备', 22, 208, 0, NULL, 211, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (145, '信号再生净化设备', 'http://www.w3.org/ns/sosa/信号再生净化设备', 22, 208, 0, NULL, 212, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (146, '光纤时间同步本地端设备', 'http://www.w3.org/ns/sosa/光纤时间同步本地端设备', 22, 208, 0, NULL, 213, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (147, '光纤时间同步远程端设备', 'http://www.w3.org/ns/sosa/光纤时间同步远程端设备', 22, 208, 0, NULL, 214, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (148, '三通道无缝切换设备', 'http://www.w3.org/ns/sosa/三通道无缝切换设备', 22, 208, 0, NULL, 215, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (149, '同步网内实时闭环运控监控', 'http://www.w3.org/ns/sosa/同步网内实时闭环运控监控', 22, 208, 0, NULL, 216, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (150, '守时钟驯服控制设备', 'http://www.w3.org/ns/sosa/守时钟驯服控制设备', 22, 208, 0, NULL, 217, 'model1');
INSERT INTO `knowledge_knowledge` VALUES (301, '再生光中继器', 'http://www.w3.org/ns/sosa/再生光中继器', 28, 387, 0, NULL, 388, '北京');
INSERT INTO `knowledge_knowledge` VALUES (302, '参考腔稳频激光器单元', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元', 28, 387, 0, NULL, 389, '北京');
INSERT INTO `knowledge_knowledge` VALUES (303, '参考腔稳频激光器单元A1.1', 'http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1', 28, 387, 1, '302', 390, '北京');
INSERT INTO `knowledge_knowledge` VALUES (304, '双向EDFA设备', 'http://www.w3.org/ns/sosa/双向EDFA设备', 28, 387, 0, NULL, 391, '北京');
INSERT INTO `knowledge_knowledge` VALUES (305, '监控单元', 'http://www.w3.org/ns/sosa/监控单元', 28, 387, 0, NULL, 392, '北京');
INSERT INTO `knowledge_knowledge` VALUES (306, '监控单元A1.5', 'http://www.w3.org/ns/sosa/监控单元A1.5', 28, 387, 1, '305', 393, '北京');
INSERT INTO `knowledge_knowledge` VALUES (307, '光频传递系统发送设备', 'http://www.w3.org/ns/sosa/光频传递系统发送设备', 28, 387, 0, NULL, 394, '北京');
INSERT INTO `knowledge_knowledge` VALUES (308, '光频传递系统发送设备A1.3', 'http://www.w3.org/ns/sosa/光频传递系统发送设备A1.3', 28, 387, 1, '307', 395, '北京');
INSERT INTO `knowledge_knowledge` VALUES (309, '拍频比对设备', 'http://www.w3.org/ns/sosa/拍频比对设备', 28, 387, 0, NULL, 396, '北京');
INSERT INTO `knowledge_knowledge` VALUES (310, '拍频比对设备A1.2', 'http://www.w3.org/ns/sosa/拍频比对设备A1.2', 28, 387, 1, '309', 397, '北京');
INSERT INTO `knowledge_knowledge` VALUES (311, '光纤稳频激光器单元', 'http://www.w3.org/ns/sosa/光纤稳频激光器单元', 28, 387, 0, NULL, 398, '北京');
INSERT INTO `knowledge_knowledge` VALUES (312, '光频光纤多路由切换设备', 'http://www.w3.org/ns/sosa/光频光纤多路由切换设备', 28, 387, 0, NULL, 399, '北京');
INSERT INTO `knowledge_knowledge` VALUES (313, '光频传递系统接收设备', 'http://www.w3.org/ns/sosa/光频传递系统接收设备', 28, 387, 0, NULL, 400, '北京');
INSERT INTO `knowledge_knowledge` VALUES (314, '光频传递系统接收设备A1.4', 'http://www.w3.org/ns/sosa/光频传递系统接收设备A1.4', 28, 387, 1, '313', 401, '北京');
INSERT INTO `knowledge_knowledge` VALUES (315, 'EDFA中继器', 'http://www.w3.org/ns/sosa/EDFA中继器', 28, 387, 0, NULL, 402, '北京');
INSERT INTO `knowledge_knowledge` VALUES (316, '微波光纤多路由切换设备', 'http://www.w3.org/ns/sosa/微波光纤多路由切换设备', 28, 403, 0, NULL, 404, '北京');
INSERT INTO `knowledge_knowledge` VALUES (317, '信号净化设备', 'http://www.w3.org/ns/sosa/信号净化设备', 28, 403, 0, NULL, 405, '北京');
INSERT INTO `knowledge_knowledge` VALUES (318, '频率基准源子系统单元', 'http://www.w3.org/ns/sosa/频率基准源子系统单元', 28, 403, 0, NULL, 406, '北京');
INSERT INTO `knowledge_knowledge` VALUES (319, '相噪分析模块', 'http://www.w3.org/ns/sosa/相噪分析模块', 28, 403, 0, NULL, 407, '北京');
INSERT INTO `knowledge_knowledge` VALUES (320, '同步子系统单元', 'http://www.w3.org/ns/sosa/同步子系统单元', 28, 403, 0, NULL, 408, '北京');
INSERT INTO `knowledge_knowledge` VALUES (321, '微波频率合成器发射和接收设备', 'http://www.w3.org/ns/sosa/微波频率合成器发射和接收设备', 28, 403, 0, NULL, 409, '北京');
INSERT INTO `knowledge_knowledge` VALUES (322, '测试设备', 'http://www.w3.org/ns/sosa/测试设备', 28, 410, 0, NULL, 411, '北京');
INSERT INTO `knowledge_knowledge` VALUES (323, '时间光纤多路由切换设备', 'http://www.w3.org/ns/sosa/时间光纤多路由切换设备', 28, 410, 0, NULL, 412, '北京');
INSERT INTO `knowledge_knowledge` VALUES (324, '多通道无缝切换设备', 'http://www.w3.org/ns/sosa/多通道无缝切换设备', 28, 410, 0, NULL, 413, '北京');
INSERT INTO `knowledge_knowledge` VALUES (325, '信号再生净化设备', 'http://www.w3.org/ns/sosa/信号再生净化设备', 28, 410, 0, NULL, 414, '北京');
INSERT INTO `knowledge_knowledge` VALUES (326, '光纤时间同步本地端设备', 'http://www.w3.org/ns/sosa/光纤时间同步本地端设备', 28, 410, 0, NULL, 415, '北京');
INSERT INTO `knowledge_knowledge` VALUES (327, '光纤时间同步远程端设备', 'http://www.w3.org/ns/sosa/光纤时间同步远程端设备', 28, 410, 0, NULL, 416, '北京');
INSERT INTO `knowledge_knowledge` VALUES (328, '三通道无缝切换设备', 'http://www.w3.org/ns/sosa/三通道无缝切换设备', 28, 410, 0, NULL, 417, '北京');
INSERT INTO `knowledge_knowledge` VALUES (329, '同步网内实时闭环运控监控', 'http://www.w3.org/ns/sosa/同步网内实时闭环运控监控', 28, 410, 0, NULL, 418, '北京');
INSERT INTO `knowledge_knowledge` VALUES (330, '守时钟驯服控制设备', 'http://www.w3.org/ns/sosa/守时钟驯服控制设备', 28, 410, 0, NULL, 419, '北京');

-- ----------------------------
-- Table structure for knowledge_knowledge_info
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_knowledge_info`;
CREATE TABLE `knowledge_knowledge_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department_id` bigint(20) NOT NULL,
  `department_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `field_id` bigint(20) NOT NULL,
  `field_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `graph_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `knowledge_synopsis` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `meta_catalogue_id` bigint(20) NOT NULL,
  `meta_catalogue_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_knowledge_info
-- ----------------------------
INSERT INTO `knowledge_knowledge_info` VALUES (1, 1, '部门11', 1, '领域1', '1', 'new4', 1, '11元目录', 'new4');
INSERT INTO `knowledge_knowledge_info` VALUES (2, 22, '部门22', 22, '领域2', '22', 'new', 22, '22元目录', 'new');
INSERT INTO `knowledge_knowledge_info` VALUES (3, 3, '部门33', 3, '领域3', '3', 'xian', 33, '33元目录', 'xian');
INSERT INTO `knowledge_knowledge_info` VALUES (54, 5, '部门11', 1, '领域1', 'http://6617', NULL, 14, '11111q21', '617');
INSERT INTO `knowledge_knowledge_info` VALUES (55, 8, '部门12', 1, '领域1', 'http://6618', NULL, 13, '元目录121', '618');
INSERT INTO `knowledge_knowledge_info` VALUES (56, 6, '部门21', 2, '领域2', 'http://66618', NULL, 12, '11122', '6618');
INSERT INTO `knowledge_knowledge_info` VALUES (59, 6, '部门21', 2, '领域2', 'http://6625', '625cs', 12, '11122', '625');
INSERT INTO `knowledge_knowledge_info` VALUES (60, 6, '部门21', 2, '领域2', 'http://6625', '625cs', 12, '11122', '625');
INSERT INTO `knowledge_knowledge_info` VALUES (46, 8, '部门12', 1, '领域1', 'http://csshi', 'zzzzzz', 13, '元目录121', 'ceshicscs');
INSERT INTO `knowledge_knowledge_info` VALUES (45, 8, '部门12', 1, '领域1', 'http://ceshi612', '测试612', 13, '元目录121', 'ceshi612');

-- ----------------------------
-- Table structure for knowledge_meta_event
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_meta_event`;
CREATE TABLE `knowledge_meta_event`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原子事件名称',
  `synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原子事件描述',
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布订阅主题名',
  `knowledge_id` bigint(20) NULL DEFAULT NULL COMMENT '知识/实例id',
  `knowledge` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_meta_event
-- ----------------------------
INSERT INTO `knowledge_meta_event` VALUES (25, '光频输入事件', '光频输入事件简介', 'optical_receive', 124, '双向EDFA设备');
INSERT INTO `knowledge_meta_event` VALUES (26, '光频输出事件', '光频输出事件简介', 'optical_send', 124, '双向EDFA设备');
INSERT INTO `knowledge_meta_event` VALUES (32, '光源输出事件', '光源输出事件简介', 'optical_send', 127, '光频传递系统发送设备');
INSERT INTO `knowledge_meta_event` VALUES (33, '光频输入事件2', '光频输入事件2', 'optical_receive', 304, '双向EDFA设备');

-- ----------------------------
-- Table structure for knowledge_selected_formula
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_selected_formula`;
CREATE TABLE `knowledge_selected_formula`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `complex_id` bigint(20) NOT NULL,
  `formula_id` bigint(20) NOT NULL,
  `attribute_relation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relation_value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of knowledge_selected_formula
-- ----------------------------
INSERT INTO `knowledge_selected_formula` VALUES (31, 6, 10, '0', '10');

-- ----------------------------
-- Table structure for meta_event
-- ----------------------------
DROP TABLE IF EXISTS `meta_event`;
CREATE TABLE `meta_event`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insert_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `synopsis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meta_event
-- ----------------------------
INSERT INTO `meta_event` VALUES (4, '2019-06-20 14:37:24', '测试事件', '这是一个测试');
INSERT INTO `meta_event` VALUES (2, '2019-08-08 11:11:00', '2', '222');
INSERT INTO `meta_event` VALUES (3, '2019-06-18 14:07:58', 'qweqqqq', 'asd zxc2qqqq');
INSERT INTO `meta_event` VALUES (5, '2019-06-20 16:07:26', '620', '111111');
INSERT INTO `meta_event` VALUES (6, '', '1', '1');
INSERT INTO `meta_event` VALUES (7, NULL, '2', '1');
INSERT INTO `meta_event` VALUES (8, NULL, '3', '1');
INSERT INTO `meta_event` VALUES (9, NULL, '4', '1');
INSERT INTO `meta_event` VALUES (10, NULL, '5', '1');
INSERT INTO `meta_event` VALUES (11, NULL, '6', '1');
INSERT INTO `meta_event` VALUES (12, NULL, '7', '1');

-- ----------------------------
-- Table structure for seledted_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `seledted_knowledge`;
CREATE TABLE `seledted_knowledge`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `complex_id` bigint(20) NOT NULL,
  `knowledge_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of seledted_knowledge
-- ----------------------------
INSERT INTO `seledted_knowledge` VALUES (98, 11, 131);
INSERT INTO `seledted_knowledge` VALUES (97, 11, 129);
INSERT INTO `seledted_knowledge` VALUES (96, 11, 138);
INSERT INTO `seledted_knowledge` VALUES (95, 11, 137);
INSERT INTO `seledted_knowledge` VALUES (94, 11, 126);
INSERT INTO `seledted_knowledge` VALUES (93, 11, 144);
INSERT INTO `seledted_knowledge` VALUES (92, 11, 127);
INSERT INTO `seledted_knowledge` VALUES (91, 11, 125);
INSERT INTO `seledted_knowledge` VALUES (90, 11, 124);
INSERT INTO `seledted_knowledge` VALUES (89, 11, 122);
INSERT INTO `seledted_knowledge` VALUES (88, 11, 128);
INSERT INTO `seledted_knowledge` VALUES (87, 11, 130);
INSERT INTO `seledted_knowledge` VALUES (86, 11, 123);
INSERT INTO `seledted_knowledge` VALUES (85, 11, 143);
INSERT INTO `seledted_knowledge` VALUES (84, 11, 142);
INSERT INTO `seledted_knowledge` VALUES (83, 11, 136);
INSERT INTO `seledted_knowledge` VALUES (82, 11, 121);
INSERT INTO `seledted_knowledge` VALUES (81, 6, 135);
INSERT INTO `seledted_knowledge` VALUES (80, 6, 134);
INSERT INTO `seledted_knowledge` VALUES (79, 6, 133);
INSERT INTO `seledted_knowledge` VALUES (78, 6, 132);
INSERT INTO `seledted_knowledge` VALUES (77, 6, 131);
INSERT INTO `seledted_knowledge` VALUES (76, 6, 130);
INSERT INTO `seledted_knowledge` VALUES (75, 6, 129);
INSERT INTO `seledted_knowledge` VALUES (74, 6, 128);
INSERT INTO `seledted_knowledge` VALUES (73, 6, 127);
INSERT INTO `seledted_knowledge` VALUES (72, 6, 126);
INSERT INTO `seledted_knowledge` VALUES (71, 6, 125);
INSERT INTO `seledted_knowledge` VALUES (70, 6, 124);
INSERT INTO `seledted_knowledge` VALUES (69, 6, 123);
INSERT INTO `seledted_knowledge` VALUES (68, 6, 122);
INSERT INTO `seledted_knowledge` VALUES (67, 6, 121);
INSERT INTO `seledted_knowledge` VALUES (99, 11, 139);
INSERT INTO `seledted_knowledge` VALUES (100, 11, 145);
INSERT INTO `seledted_knowledge` VALUES (101, 11, 146);
INSERT INTO `seledted_knowledge` VALUES (102, 11, 132);
INSERT INTO `seledted_knowledge` VALUES (103, 11, 133);
INSERT INTO `seledted_knowledge` VALUES (104, 11, 135);
INSERT INTO `seledted_knowledge` VALUES (105, 11, 140);
INSERT INTO `seledted_knowledge` VALUES (106, 11, 141);
INSERT INTO `seledted_knowledge` VALUES (107, 11, 147);
INSERT INTO `seledted_knowledge` VALUES (108, 11, 148);
INSERT INTO `seledted_knowledge` VALUES (109, 11, 149);
INSERT INTO `seledted_knowledge` VALUES (110, 11, 150);
INSERT INTO `seledted_knowledge` VALUES (111, 11, 134);
INSERT INTO `seledted_knowledge` VALUES (112, 11, 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('小明', '语文', 5);
INSERT INTO `student` VALUES ('小红', '语文', 100);
INSERT INTO `student` VALUES ('小明', '数学', 98);
INSERT INTO `student` VALUES ('小红', '数学', 3);

-- ----------------------------
-- Table structure for warn
-- ----------------------------
DROP TABLE IF EXISTS `warn`;
CREATE TABLE `warn`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warn
-- ----------------------------
INSERT INTO `warn` VALUES (10000, '1');

SET FOREIGN_KEY_CHECKS = 1;

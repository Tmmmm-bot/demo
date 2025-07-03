/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : demo23

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2025-06-07 11:14:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crew_management
-- ----------------------------
DROP TABLE IF EXISTS `crew_management`;
CREATE TABLE `crew_management` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `crew_name` varchar(100) NOT NULL COMMENT '乘务组名称',
  `driver_name` varchar(50) NOT NULL COMMENT '司机姓名',
  `driver_phone` varchar(20) DEFAULT NULL COMMENT '司机联系电话',
  `start_station_name` varchar(100) NOT NULL COMMENT '起始站名称',
  `end_station_name` varchar(100) NOT NULL COMMENT '终到站名称',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态(0:待命,1:在岗,2:休息)',
  `qualification_level` varchar(50) DEFAULT NULL COMMENT '资质等级',
  `work_experience` int(11) DEFAULT NULL COMMENT '工作年限',
  `safety_record` text COMMENT '安全记录',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='乘务组管理表';

-- ----------------------------
-- Records of crew_management
-- ----------------------------
INSERT INTO `crew_management` VALUES ('1', '1', '2', '3', '4', '5', '0', '一级', '6', '7', '8', '2025-06-05 15:31:01', '2025-06-05 15:31:01');
INSERT INTO `crew_management` VALUES ('2', '1', '2', '3', '4', '5', '0', '一级', '6', '7', '8', '2025-06-05 15:31:02', '2025-06-05 15:31:02');
INSERT INTO `crew_management` VALUES ('3', '1', '2', '3', '4', '5', '0', '一级', '6', '7', '8', '2025-06-05 15:31:06', '2025-06-05 15:31:06');
INSERT INTO `crew_management` VALUES ('4', '1', '2', '3', '4', '5', '0', '一级', '6', '7', '8', '2025-06-05 15:31:15', '2025-06-05 15:31:15');

-- ----------------------------
-- Table structure for dispatch_command
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_command`;
CREATE TABLE `dispatch_command` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `command_type` varchar(50) DEFAULT NULL COMMENT '命令类型',
  `command_content` text COMMENT '命令内容',
  `train_no` varchar(50) DEFAULT NULL COMMENT '车次号',
  `station_code` varchar(50) DEFAULT NULL COMMENT '站点编码',
  `station_name` varchar(100) DEFAULT NULL COMMENT '站点名称',
  `execute_time` datetime DEFAULT NULL COMMENT '执行时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态(0:未执行,1:执行中,2:已完成,3:已取消)',
  `priority` tinyint(4) DEFAULT '0' COMMENT '优先级(0:普通,1:重要,2:紧急)',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='调度命令管理表';

-- ----------------------------
-- Records of dispatch_command
-- ----------------------------
INSERT INTO `dispatch_command` VALUES ('1', '121', '12', '12', null, '12', '2025-06-05 00:00:00', '0', '0', '12', '2025-06-05 15:47:29', '2025-06-05 15:47:29');
INSERT INTO `dispatch_command` VALUES ('2', '121', '12', '12', null, '12', '2025-06-05 00:00:00', '0', '0', '12', '2025-06-05 15:47:29', '2025-06-05 15:47:29');

-- ----------------------------
-- Table structure for emu_dynamic_info
-- ----------------------------
DROP TABLE IF EXISTS `emu_dynamic_info`;
CREATE TABLE `emu_dynamic_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `current_station_name` varchar(100) NOT NULL COMMENT '当前站点名称',
  `next_station_name` varchar(100) DEFAULT NULL COMMENT '下一站名称',
  `arrival_time` datetime DEFAULT NULL COMMENT '到达时间',
  `departure_time` datetime DEFAULT NULL COMMENT '发车时间',
  `running_status` tinyint(4) DEFAULT '0' COMMENT '运行状态(0:待发,1:运行中,2:到站,3:停运)',
  `speed` int(11) DEFAULT NULL COMMENT '当前速度(km/h)',
  `temperature` decimal(5,2) DEFAULT NULL COMMENT '车厢温度',
  `power_status` tinyint(4) DEFAULT '1' COMMENT '供电状态(0:断电,1:供电)',
  `door_status` tinyint(4) DEFAULT '0' COMMENT '车门状态(0:关闭,1:开启)',
  `maintenance_status` tinyint(4) DEFAULT '0' COMMENT '维护状态(0:正常,1:需要维护)',
  `fault_description` text COMMENT '故障描述',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='动车组动态信息表';

-- ----------------------------
-- Records of emu_dynamic_info
-- ----------------------------
INSERT INTO `emu_dynamic_info` VALUES ('2', '12', '22', '2025-06-05 00:00:00', '2025-06-05 00:00:00', '0', '2', '2.00', '0', '0', '0', '2', '2', '2025-06-05 16:14:41', '2025-06-05 16:14:41');

-- ----------------------------
-- Table structure for maintenance_capacity
-- ----------------------------
DROP TABLE IF EXISTS `maintenance_capacity`;
CREATE TABLE `maintenance_capacity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `maintenance_station_name` varchar(100) NOT NULL COMMENT '检修站名称',
  `maintenance_type` varchar(50) NOT NULL COMMENT '检修类型(一级修/二级修/三级修/四级修)',
  `maintenance_line_capacity` int(11) NOT NULL COMMENT '检修线容量(可同时检修动车组数量)',
  `daily_capacity` int(11) NOT NULL COMMENT '日检修能力(辆/天)',
  `monthly_capacity` int(11) NOT NULL COMMENT '月检修能力(辆/月)',
  `current_workload` int(11) DEFAULT '0' COMMENT '当前工作量',
  `maintenance_status` tinyint(4) DEFAULT '0' COMMENT '检修线状态(0:空闲,1:使用中,2:维护中)',
  `current_emu_no` varchar(50) DEFAULT NULL COMMENT '当前检修动车组号',
  `maintenance_start_time` datetime DEFAULT NULL COMMENT '当前检修开始时间',
  `team_member_count` int(11) DEFAULT NULL COMMENT '班组人数',
  `qualification_level` varchar(50) DEFAULT NULL COMMENT '资质等级',
  `equipment_status` tinyint(4) DEFAULT '1' COMMENT '设备状态(0:故障,1:正常)',
  `quality_check_status` tinyint(4) DEFAULT '0' COMMENT '质检状态(0:未检查,1:检查中,2:已通过,3:未通过)',
  `safety_check_status` tinyint(4) DEFAULT '0' COMMENT '安全检查状态(0:未检查,1:检查中,2:已通过,3:未通过)',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='运用检修能力管理表';

-- ----------------------------
-- Records of maintenance_capacity
-- ----------------------------
INSERT INTO `maintenance_capacity` VALUES ('2', '12', '一级修', '12', '2', '2', '2', '0', '2', '2025-06-05 00:00:00', null, '一级', '0', '0', '1', '2', '2025-06-05 16:13:22', '2025-06-05 16:13:22');

-- ----------------------------
-- Table structure for shunting_operation
-- ----------------------------
DROP TABLE IF EXISTS `shunting_operation`;
CREATE TABLE `shunting_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `operation_type` varchar(50) NOT NULL COMMENT '作业类型',
  `start_station_name` varchar(100) NOT NULL COMMENT '起始站名称',
  `end_station_name` varchar(100) NOT NULL COMMENT '终到站名称',
  `planned_start_time` datetime NOT NULL COMMENT '计划开始时间',
  `planned_end_time` datetime NOT NULL COMMENT '计划结束时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态(0:未开始,1:进行中,2:已完成,3:已取消)',
  `priority` tinyint(4) DEFAULT '0' COMMENT '优先级(0:普通,1:重要,2:紧急)',
  `operator` varchar(50) NOT NULL COMMENT '作业人员',
  `car_count` int(11) DEFAULT NULL COMMENT '调车数量',
  `operation_content` text COMMENT '作业内容',
  `safety_check` tinyint(4) DEFAULT '0' COMMENT '安全检查(0:未检查,1:已检查)',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='调车作业管理表';

-- ----------------------------
-- Records of shunting_operation
-- ----------------------------

-- ----------------------------
-- Table structure for temperature_plan
-- ----------------------------
DROP TABLE IF EXISTS `temperature_plan`;
CREATE TABLE `temperature_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_name` varchar(100) NOT NULL COMMENT '计划名称',
  `station_name` varchar(100) NOT NULL COMMENT '站点名称',
  `target_temperature` decimal(5,2) NOT NULL COMMENT '目标调度',
  `plan_time` datetime NOT NULL COMMENT '计划时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态(0:未执行,1:已执行,2:执行失败)',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='温度计划管理表';

-- ----------------------------
-- Records of temperature_plan
-- ----------------------------
INSERT INTO `temperature_plan` VALUES ('2', '2', '2', '2.00', '2025-06-05 00:00:00', '0', '2', '2025-06-05 16:29:22', '2025-06-05 16:29:22');
INSERT INTO `temperature_plan` VALUES ('3', '23', '23', '32.00', '2025-06-05 00:00:00', '0', '32', '2025-06-05 16:45:44', '2025-06-05 16:45:44');

-- ----------------------------
-- Table structure for work_info_query
-- ----------------------------
DROP TABLE IF EXISTS `work_info_query`;
CREATE TABLE `work_info_query` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `work_no` varchar(50) NOT NULL COMMENT '作业编号',
  `work_type` varchar(50) NOT NULL COMMENT '作业类型',
  `emu_no` varchar(50) NOT NULL COMMENT '动车组编号',
  `station_code` varchar(50) NOT NULL COMMENT '站点编码',
  `station_name` varchar(100) NOT NULL COMMENT '站点名称',
  `work_time` datetime NOT NULL COMMENT '作业时间',
  `work_status` tinyint(4) DEFAULT '0' COMMENT '作业状态(0:未开始,1:进行中,2:已完成)',
  `operator` varchar(50) NOT NULL COMMENT '操作人',
  `work_result` tinyint(4) DEFAULT '0' COMMENT '作业结果(0:正常,1:异常)',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='作业信息查询表';

-- ----------------------------
-- Records of work_info_query
-- ----------------------------

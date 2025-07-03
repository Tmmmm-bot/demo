package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <p>
 * 运用检修能力管理表
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("maintenance_capacity")
public class MaintenanceCapacity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检修站名称
     */
    private String maintenanceStationName;

    /**
     * 检修类型(一级修/二级修/三级修/四级修)
     */
    private String maintenanceType;

    /**
     * 检修线容量(可同时检修动车组数量)
     */
    private Integer maintenanceLineCapacity;

    /**
     * 日检修能力(辆/天)
     */
    private Integer dailyCapacity;

    /**
     * 月检修能力(辆/月)
     */
    private Integer monthlyCapacity;

    /**
     * 当前工作量
     */
    private Integer currentWorkload;

    /**
     * 检修线状态(0:空闲,1:使用中,2:维护中)
     */
    private Integer maintenanceStatus;

    /**
     * 当前检修动车组号
     */
    private String currentEmuNo;

    /**
     * 当前检修开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime maintenanceStartTime;

    /**
     * 班组人数
     */
    private Integer teamMemberCount;

    /**
     * 资质等级
     */
    private String qualificationLevel;

    /**
     * 设备状态(0:故障,1:正常)
     */
    private Integer equipmentStatus;

    /**
     * 质检状态(0:未检查,1:检查中,2:已通过,3:未通过)
     */
    private Integer qualityCheckStatus;

    /**
     * 安全检查状态(0:未检查,1:检查中,2:已通过,3:未通过)
     */
    private Integer safetyCheckStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @Id
    @GeneratedValue
    int id;

}

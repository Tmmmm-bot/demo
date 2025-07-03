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
 * 调车作业管理表
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shunting_operation")
public class ShuntingOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作业类型
     */
    private String operationType;

    /**
     * 起始站名称
     */
    private String startStationName;

    /**
     * 终到站名称
     */
    private String endStationName;

    /**
     * 计划开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime plannedStartTime;

    /**
     * 计划结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime plannedEndTime;

    /**
     * 状态(0:未开始,1:进行中,2:已完成,3:已取消)
     */
    private Integer status;

    /**
     * 优先级(0:普通,1:重要,2:紧急)
     */
    private Integer priority;

    /**
     * 作业人员
     */
    private String operator;

    /**
     * 调车数量
     */
    private Integer carCount;

    /**
     * 作业内容
     */
    private String operationContent;

    /**
     * 安全检查(0:未检查,1:已检查)
     */
    private Integer safetyCheck;

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

package com.example.demo.entity;

import java.math.BigDecimal;
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
 * 温度计划管理表
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("temperature_plan")
public class TemperaturePlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 目标调度
     */
    private BigDecimal targetTemperature;

    /**
     * 计划时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime planTime;

    /**
     * 状态(0:未执行,1:已执行,2:执行失败)
     */
    private Integer status;

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

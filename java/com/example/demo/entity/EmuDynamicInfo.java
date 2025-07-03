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
 * 动车组信息表
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("emu_dynamic_info")
public class EmuDynamicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前站点名称
     */
    private String currentStationName;

    /**
     * 下一站名称
     */
    private String nextStationName;

    /**
     * 到达时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;

    /**
     * 发车时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;

    /**
     * 运行状态(0:待发,1:运行中,2:到站,3:停运)
     */
    private Integer runningStatus;

    /**
     * 当前速度(km/h)
     */
    private Integer speed;

    /**
     * 车厢温度
     */
    private BigDecimal temperature;

    /**
     * 供电状态(0:断电,1:供电)
     */
    private Integer powerStatus;

    /**
     * 车门状态(0:关闭,1:开启)
     */
    private Integer doorStatus;

    /**
     * 维护状态(0:正常,1:需要维护)
     */
    private Integer maintenanceStatus;

    /**
     * 故障描述
     */
    private String faultDescription;

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

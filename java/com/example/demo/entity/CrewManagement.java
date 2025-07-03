package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <p>
 * 乘务组管理表
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("crew_management")
public class CrewManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 乘务组名称
     */
    private String crewName;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 司机联系电话
     */
    private String driverPhone;

    /**
     * 起始站名称
     */
    private String startStationName;

    /**
     * 终到站名称
     */
    private String endStationName;

    /**
     * 状态(0:待命,1:在岗,2:休息)
     */
    private Integer status;

    /**
     * 资质等级
     */
    private String qualificationLevel;

    /**
     * 工作年限
     */
    private Integer workExperience;

    /**
     * 安全记录
     */
    private String safetyRecord;

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

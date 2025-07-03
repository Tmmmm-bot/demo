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
 * 调度命令管理表
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dispatch_command")
public class DispatchCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 命令类型
     */
    private String commandType;

    /**
     * 命令内容
     */
    private String commandContent;

    /**
     * 车次号
     */
    private String trainNo;

    /**
     * 站点编码
     */
    private String stationCode;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 执行时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime executeTime;

    /**
     * 状态(0:未执行,1:执行中,2:已完成,3:已取消)
     */
    private Integer status;

    /**
     * 优先级(0:普通,1:重要,2:紧急)
     */
    private Integer priority;

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

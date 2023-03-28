package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author LXD
 * @since 2022-11-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_gengzhang")
public class Gengzhang implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "HyId", type = IdType.AUTO)
    private Integer HyId;

    private String HyDate;

    private Double P;

    private Double EI;

    private Double MQ;

    private Double CQ;


}

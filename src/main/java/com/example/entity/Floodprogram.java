package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author LXD
 * @since 2023-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_floodprogram")
public class Floodprogram implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "programname", type = IdType.ASSIGN_ID)
    private String programname;

    private Double reservoirlevel;

    private Double reservoircapacity;

    @TableField("reservoirQ")
    private Double reservoirq;

    private Double bayilevel;

    @TableField("bayiQ")
    private Double bayiq;


}

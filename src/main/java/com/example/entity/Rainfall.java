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
 * @since 2022-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_rainfall")
public class Rainfall implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Site", type = IdType.ASSIGN_ID)
    private String Site;

    private String Date;

    private Double P;

    private Double EI;


}

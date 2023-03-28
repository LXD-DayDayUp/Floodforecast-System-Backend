package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author LXD
 * @since 2023-02-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_floodforecast")
public class Floodforecast implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Date", type = IdType.ASSIGN_ID)
    private LocalDate date;

    @TableField("Foresection")
    private String foresection;

    @TableField("ForeQ")
    private Double foreq;

    @TableField("Forelevel")
    private Double forelevel;


}

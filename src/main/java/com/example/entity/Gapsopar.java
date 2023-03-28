package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author LXD
 * @since 2023-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_GAPSOpar")
@AllArgsConstructor
@NoArgsConstructor
public class Gapsopar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("basin")
    private String basin;

    private double popsize;

    private double generation;

    @TableField("WUM")
    private double wum;

    @TableField("WLM")
    private double wlm;

    @TableField("WDM")
    private double wdm;

    @TableField("K")
    private double k;

    @TableField("C")
    private double c;

    @TableField("B")
    private double b;

    @TableField("IMP")
    private double imp;

    @TableField("SM")
    private double sm;

    @TableField("CS")
    private double cs;

    @TableField("EX")
    private double ex;

    @TableField("KSS")
    private double kss;

    @TableField("KG")
    private double kg;

    @TableField("KKSS")
    private double kkss;

    @TableField("KKG")
    private double kkg;

}

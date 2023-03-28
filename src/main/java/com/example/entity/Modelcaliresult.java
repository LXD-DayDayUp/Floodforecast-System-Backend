package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_modelcaliresult")
public class Modelcaliresult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String resultname;

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

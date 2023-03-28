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
 * @since 2023-02-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_PSOpar")
public class Psopar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String basin;

    private Double popsize;

    private Double generation;

    @TableField("WUM")
    private Double wum;

    @TableField("WLM")
    private Double wlm;

    @TableField("WDM")
    private Double wdm;

    @TableField("K")
    private Double k;

    @TableField("C")
    private Double c;

    @TableField("B")
    private Double b;

    @TableField("IMP")
    private Double imp;

    @TableField("SM")
    private Double sm;

    @TableField("CS")
    private Double cs;

    @TableField("EX")
    private Double ex;

    @TableField("KSS")
    private Double kss;

    @TableField("KG")
    private Double kg;

    @TableField("KKSS")
    private Double kkss;

    @TableField("KKG")
    private Double kkg;


}

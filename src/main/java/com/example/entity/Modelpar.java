package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@TableName("sys_modelpar")
public class Modelpar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "K")
    private double K;
    @TableField(value = "WUM")
    private double WUM;
    @TableField(value = "WLM")
    private double WLM;
    @TableField(value = "WDM")
    private double WDM;
    @TableField(value = "SM")
    private double SM;
    @TableField(value = "KSS")
    private double KSS;
    @TableField(value = "KG")
    private double KG;
    @TableField(value = "KKSS")
    private double KKSS;
    @TableField(value = "KKG")
    private double KKG;


}

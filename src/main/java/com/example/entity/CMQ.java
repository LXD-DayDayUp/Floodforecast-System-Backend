package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_CMQ")
public class CMQ implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Date", type = IdType.ASSIGN_ID)
    private String Date;


}

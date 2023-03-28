package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_flow")
public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Site", type = IdType.ASSIGN_ID)
    private String Site;

    private LocalDate Date;

    private Double MQ;

    private Double CQ;

}

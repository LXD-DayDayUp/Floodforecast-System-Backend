package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("sys_floodwarning")
public class Floodwarning implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 预警对象
     */
    @TableId(value = "object", type = IdType.ASSIGN_ID)
    private String object;

    /**
     * 时间
     */
    private LocalDate date;

    /**
     * 多布出库流量
     */
    private double duobu;

    /**
     * 巴及曲汇入流量
     */
    private double bajiqu;

    /**
     * 觉木曲汇入流量
     */
    private double juemuqu;

    /**
     * 八一大桥预测水位
     */
    private double preQ;

    /**
     * 八一大桥预测水位
     */
    private double prelevel;

    /**
     * 八一大桥预警水位
     */
    private double warlevel;

    /**
     * 负责人
     */
    private String leader;


}

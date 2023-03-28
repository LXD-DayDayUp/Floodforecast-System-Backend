package com.example.entity;

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
 * @since 2022-11-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CMQ implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Site;

    private String[] Date;

    private double[] MQ;

    private double[] CQ;
}

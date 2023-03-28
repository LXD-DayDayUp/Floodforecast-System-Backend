package com.example.service;

import com.example.entity.Gapar;
import com.example.entity.Psopar;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LXD
 * @since 2023-02-15
 */
public interface PsoparService extends IService<Psopar> {

    Psopar selectPSOParByID(int id);
}

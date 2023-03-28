package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Modelpar;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LXD
 * @since 2022-10-30
 */
public interface ModelparService extends IService<Modelpar> {
    Modelpar selectParByID(int id);
}

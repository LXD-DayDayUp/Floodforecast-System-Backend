package com.example.service;

import com.example.entity.Gapsopar;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LXD
 * @since 2023-02-02
 */
public interface GapsoparService extends IService<Gapsopar> {
//    接口
    Gapsopar selectGAPSOParByID(int id);
}

package com.example.service;

import com.example.entity.Gapar;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Gapsopar;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LXD
 * @since 2023-02-15
 */
public interface GaparService extends IService<Gapar> {

    Gapar selectGAParByID(int id);

}

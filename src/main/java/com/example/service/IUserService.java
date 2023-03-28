package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LXD
 * @since 2022-10-24
 */
public interface IUserService extends IService<User> {

    User findUserByUserName(String username);
}

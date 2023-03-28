package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LXD
 * @since 2022-10-24
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> findPermissionListByUserId(Long userID);
}

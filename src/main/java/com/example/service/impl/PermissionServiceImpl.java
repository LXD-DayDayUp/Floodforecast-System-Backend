package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.PermissionMapper;
import com.example.entity.Permission;
import com.example.service.IPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LXD
 * @since 2022-10-24
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    public List<Permission> findPermissionListByUserId(Long userId){
        return baseMapper.findPermissionListByUserId(userId);
    }
}

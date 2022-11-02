package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2022-10-24
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    /*** 根据用户ID查询权限列表 * @param userId * @return */
    @Select("select DISTINCT p.id,p.parent_id,p.label,p.`code`,p.url,p.type,p.icon,p.remark,p.path,p.name from sys_user as u left join sys_user_role as ur on u.id = ur.user_id left join sys_role as r on ur.role_id = r.id left join sys_role_permission as rp on rp.role_id = r.id left join sys_permission as p on rp.permission_id = p.id where u.id =#{userId} order by p.id asc")
    List<Permission> findPermissionListByUserId(Long userId);
}

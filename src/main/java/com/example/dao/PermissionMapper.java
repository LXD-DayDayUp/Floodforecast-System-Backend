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
    //asc表示升序 distinct用来查询不重复记录的条数
    @Select("select\n" +
            "distinct\n" +
            "p.id,p.parent_id,p.parent_name,p.label,p.code,p.url,p.type,p.icon,p.remark,p.path,p.name\n" +
            "from sys_user as u\n" +
            "left join sys_user_role as ur on u.id = ur.user_id\n" +
            "left join sys_role as r on ur.role_id = r.id \n" +
            "left join sys_role_permission as rp on rp.role_id = r.id \n" +
            "left join sys_permission as p on rp.permission_id = p.id \n" +
            "where u.id =#{userId} \n" +
            "order by p.id asc")
    List<Permission> findPermissionListByUserId(Long userId);
}

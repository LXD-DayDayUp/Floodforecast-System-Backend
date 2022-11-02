package com.example.config.security.service;

import com.example.entity.Permission;
import com.example.entity.User;
import com.example.service.IPermissionService;
import com.example.service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Resource
    private IUserService userService;

    @Resource
    private IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        //    查询用户拥有的权限列表
        List<Permission> permissionList = permissionService.findPermissionListByUserId(user.getId());
//        获取权限编码
        List<String> collect=permissionList.stream().filter(Objects::nonNull).map(Permission::getCode).filter(Objects::nonNull).collect(Collectors.toList());
//        转换成数组
        String[] strings=collect.toArray(new String[0]);
//        设置权限列表
        List<GrantedAuthority> authorities= AuthorityUtils.createAuthorityList(strings);
        user.setAuthorities(authorities);
//        设置菜单列表
        user.setPermissionList(permissionList);
        return user;
    }

}

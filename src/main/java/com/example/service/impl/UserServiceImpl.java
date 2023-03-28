package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.UserMapper;
import com.example.entity.User;
import com.example.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
//    根据用户名查询用户信息
    @Override
    public User findUserByUserName(String username) {
//        这三行代码突然不能用，查不到用户数据，导致系统登录不了
//        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
//        queryWrapper.eq("username",username);
//        System.out.println(baseMapper.selectOne(queryWrapper));
        User user=userMapper.selectUserByUsername(username);
        return user;
    }
}

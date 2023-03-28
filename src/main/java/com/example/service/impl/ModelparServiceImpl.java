package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.ModelparMapper;
import com.example.entity.Modelpar;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.service.ModelparService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LXD
 * @since 2022-10-30
 */

//        按条件查询方式一
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.lt("userid",18);//lt为小于，gt为大于
//        List<User> userList=userMapper.selectList(queryWrapper);
//        System.out.println(userService.findUserByUserName("tom"));
//        按条件查询方式二
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.lambda().lt(User::getId,18);//lt为小于，gt为大于
//        List<User> userList=userMapper.selectList(queryWrapper);
//        按条件查询方式三
//        LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<User>();
//        lqw.lt(User::getId,18);
//        List<User> userList=userMapper.selectList(lqw);
//        按条件查询方式三多条件第一种
//        LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<User>();
//        lqw.lt(User::getId,18);
//        lqw.gt(User::getId,10);
//        List<User> userList=userMapper.selectList(lqw);
//        按条件查询方式三多条件第二种,并且语句
//        LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<User>();
//        lqw.lt(User::getId,18).gt(User::getId,18);
//        List<User> userList=userMapper.selectList(lqw);
//        按条件查询方式三多条件第二种,或者语句
//        LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<User>();
//        lqw.lt(User::getId,18).or().gt(User::getId,18);
//        List<User> userList=userMapper.selectList(lqw);
//        查询投影,只查看某些字段，第一种
//        LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<User>();
//        lqw.select(User::getId,User::getAvatar);
//        List<User> userList=userMapper.selectList(lqw);
//        查询投影,只查看某些字段，第一种
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.select("User::getId,User::getAvatar");
//        List<User> userList=userMapper.selectList(queryWrapper);
//        List<Rainfall> rainfallList=rainfallMapper.selectAll();
//        System.out.println(rainfallList);
@Service
@Transactional
public class ModelparServiceImpl extends ServiceImpl<ModelparMapper, Modelpar> implements ModelparService {

    @Override
    public Modelpar selectParByID(int id) {
        Modelpar mp=new Modelpar();
        QueryWrapper<Modelpar> qw = new QueryWrapper<>();
        qw.eq("id",id);
        mp=baseMapper.selectOne(qw);
        return mp;
    }

}

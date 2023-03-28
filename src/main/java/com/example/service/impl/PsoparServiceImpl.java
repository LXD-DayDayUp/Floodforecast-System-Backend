package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Gapar;
import com.example.entity.Psopar;
import com.example.dao.PsoparMapper;
import com.example.service.PsoparService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LXD
 * @since 2023-02-15
 */
@Service
public class PsoparServiceImpl extends ServiceImpl<PsoparMapper, Psopar> implements PsoparService {

    @Override
    public Psopar selectPSOParByID(int id) {
        Psopar psopar=new Psopar();
        QueryWrapper<Psopar> qw = new QueryWrapper<>();
        qw.eq("id",id);
        psopar=baseMapper.selectOne(qw);
        return psopar;
    }
}

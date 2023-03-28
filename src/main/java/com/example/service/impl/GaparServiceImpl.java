package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Gapar;
import com.example.dao.GaparMapper;
import com.example.entity.Gapsopar;
import com.example.service.GaparService;
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
public class GaparServiceImpl extends ServiceImpl<GaparMapper, Gapar> implements GaparService {

    @Override
    public Gapar selectGAParByID(int id) {
        Gapar gapar=new Gapar();
        QueryWrapper<Gapar> qw = new QueryWrapper<>();
        qw.eq("id",id);
        gapar=baseMapper.selectOne(qw);
        return gapar;
    }
}

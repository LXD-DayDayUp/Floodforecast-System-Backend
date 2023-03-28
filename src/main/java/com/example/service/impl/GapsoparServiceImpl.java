package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.GapsoparMapper;
import com.example.entity.Gapsopar;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.service.GapsoparService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LXD
 * @since 2023-02-02
 */
@Service
public class GapsoparServiceImpl extends ServiceImpl<GapsoparMapper, Gapsopar> implements GapsoparService {

    @Override
    public Gapsopar selectGAPSOParByID(int id) {
        Gapsopar gapsopar=new Gapsopar();
        QueryWrapper<Gapsopar> qw = new QueryWrapper<>();
        qw.eq("id",id);
        gapsopar=baseMapper.selectOne(qw);
        return gapsopar;
    }
}

package com.example.dao;

import com.example.entity.CMQ;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2022-11-02
 */
public interface CMQMapper extends BaseMapper<CMQ> {
    @Select("update sys_CMQ set CS=#{CQ} where Date=#{date}")
    public void update(double CQ,String date);
}

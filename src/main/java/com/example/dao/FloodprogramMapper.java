package com.example.dao;

import com.example.entity.Floodprogram;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Flow;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2023-03-08
 */
public interface FloodprogramMapper extends BaseMapper<Floodprogram> {

    //    查询所有洪水数据
    @Select("select * from sys_floodprogram")
    List<Floodprogram> queryAllprogram();

//    模糊优选

}

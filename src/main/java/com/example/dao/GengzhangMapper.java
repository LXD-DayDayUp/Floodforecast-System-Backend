package com.example.dao;

import com.example.entity.Gengzhang;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Rainfall;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2022-11-01
 */
public interface GengzhangMapper extends BaseMapper<Gengzhang> {

    //    根据日期查降雨数据
    @Select("select * from sys_gengzhang where HyDate between #{starttime} and #{endtime}")
    public List<Gengzhang> selectPEByDate(String starttime, String endtime);
}

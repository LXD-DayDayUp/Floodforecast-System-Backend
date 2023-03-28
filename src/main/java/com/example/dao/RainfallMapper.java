package com.example.dao;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.entity.Rainfall;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2022-10-27
 */
@Mapper
public interface RainfallMapper extends BaseMapper<Rainfall> {
    //    增加新降雨数据
    @Select("insert into sys_rainfall (Site,Date,P) values (#{site},#{date},#{p})")
    public void addrainfall(double p, String date, String site);

    //    查询所有降雨数据
    @Select("select * from sys_rainfall")
    public List<Rainfall> selectAll();
    //    根据具体日期查询降雨数据
    @Select("select * from sys_rainfall where Date=#{date} and Site=#{site} ")
    public Rainfall selectByDate(String date, String site);
    //    根据时间段和站点查询降雨数据
    @Select("select * from sys_rainfall where Date between #{starttime} and #{endtime} and Site=#{site}")
    public List<Rainfall> selectAllByDate(String starttime, String endtime, String site);

    //    根据站点和日期修改降雨数据
    @Select("update sys_rainfall set P=#{p} where Date=#{date} and Site=#{site}")
    public Rainfall reviserainfall(double p, String date, String site);

    //    根据站点和日期删除降雨数据
    @Select("delete from sys_rainfall where Site=#{site} and Date=#{date}")
    public void deleterainfall(String date, String site);

}

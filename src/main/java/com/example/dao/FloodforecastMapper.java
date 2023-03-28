package com.example.dao;

import com.example.entity.Floodforecast;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2023-02-19
 */
public interface FloodforecastMapper extends BaseMapper<Floodforecast> {

    //根据条件查询数据
    @Select("select * from sys_floodforecast where Date=#{Date} and Foresection=#{Foresection}")
    Floodforecast queryForeResult(LocalDate Date, String Foresection);

    //   查询时间段内数据
    @Select("select * from sys_floodforecast where Foresection=#{foresection} and Date between #{starttime} and #{endtime}")
    List<Floodforecast> queryFloodFore(String foresection, LocalDate starttime, LocalDate endtime);
    //    向数据库中插入数据
    @Select("insert into sys_floodforecast (Date,Foresection,ForeQ) values (#{Date},#{Foresection},#{ForeQ})")
    void storeForeResult(LocalDate Date, String Foresection, double ForeQ);

    //    更新预报结果数据库数据
    @Select("update sys_floodforecast set ForeQ=#{ForeQ} where Date=#{Date} and Foresection=#{Foresection}")
    void updateForeResult(LocalDate Date, String Foresection, double ForeQ);
}

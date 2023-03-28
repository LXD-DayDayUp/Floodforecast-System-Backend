package com.example.dao;

import com.example.entity.Flow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2022-10-30
 */
public interface FlowMapper extends BaseMapper<Flow> {
//    增加新洪水数据
    @Select("insert into sys_flow (Site,Date,MQ) values (#{site},#{date},#{mq})")
    void addflow(String site,String date,double mq);

//    查询所有洪水数据
    @Select("select * from sys_flow")
    List<Flow> selectAllFlow();

//    根据具体日期查询洪水数据
    @Select("select * from sys_flow where Date=#{date} and Site=#{site} ")
    Flow selectFlowByDate(String date,String site);

//    根据时间段和站点查询洪水数据
    @Select("select * from sys_flow where Date between #{starttime} and #{endtime} and Site=#{site}")
    List<Flow> selectAllFlowByDate(String starttime,String endtime,String site);

//    根据站点和日期修改洪水数据
    @Select("update sys_flow set MQ=#{mq} where Date=#{date} and Site=#{site}")
    Flow reviseflow(double mq,String date,String site);

//    根据站点和日期删除洪水数据
    @Select("delete from sys_flow where Site=#{site} and Date=#{date}")
    public void deleteflow(String date,String site);
}

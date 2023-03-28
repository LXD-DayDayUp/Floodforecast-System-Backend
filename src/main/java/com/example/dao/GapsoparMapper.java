package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Caliresult;
import com.example.entity.Flow;
import com.example.entity.Gapsopar;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2023-02-02
 */
public interface GapsoparMapper extends BaseMapper<Gapsopar> {

    //    根据用户输入的条件查询率定后的参数
    @Select("select * from sys_GAPSOpar where popsize=#{popsize} and generation=#{generation}")
    List<Gapsopar> queryAllParByFactor(int popsize, int generation);

    //    查询数据库中实测流量
    @Select("select * from sys_flow where Date between #{starttime} and #{endtime} and Site=#{site}")
    List<Flow> queryMqAndCq(String site, String starttime, String endtime);

    //    将率定后的结果存入result数据库
    @Select("insert into sys_program (username,resultname,basin,popsize,generation,WUM,WLM,WDM,K,C,B,IMP,SM,CS,EX,KSS,KG,KKSS,KKG) values (#{username},#{resultname},#{basin},#{popsize},#{generation},#{WUM},#{WLM},#{WDM},#{K},#{C},#{B},#{IMP},#{SM},#{CS},#{EX},#{KSS},#{KG},#{KKSS},#{KKG})")
    void addModelCaliResult(String username, String resultname, String basin, double popsize, double generation, double WUM, double WLM, double WDM, double K, double C, double B, double IMP, double SM, double CS, double EX, double KSS, double KG, double KKSS, double KKG);

    //    查询result数据库中数据
    @Select("select * from sys_program where username=#{username} and resultname=#{resultname} and basin=#{basin} and popsize=#{popsize} and generation=#{generation}")
    Caliresult queryResult(String username, String resultname, String basin, double popsize, double generation);

    // 修改行数据
    @Select("update sys_GAPSOpar set WUM=#{wum},WLM=#{wlm}, WDM=#{wdm}, K=#{k}, C=#{c}, B=#{b}, IMP=#{imp}, SM=#{sm}, CS=#{cs}, EX=#{ex}, KSS=#{kss}, KG=#{kg}, KKSS=#{kkss}, KKG=#{kkg} where ID=#{id} and basin=#{basin} and popsize=#{popsize} and generation=#{generation}")
    void updaterowdata(double wum, double wlm, double wdm, double k, double c, double b, double imp, double sm, double cs, double ex, double kss, double kg, double kkss, double kkg, double id, String basin, double popsize, double generation);
}

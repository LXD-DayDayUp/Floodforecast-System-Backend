package com.example.dao;

import com.example.entity.Caliresult;
import com.example.entity.Modelcaliresult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2023-02-07
 */
public interface ModelcaliresultMapper extends BaseMapper<Modelcaliresult> {

    //    根据流域查询当前用户模型率定方案
    @Select("select * from sys_program where username=#{username} and basin=#{basin}")
    List<Modelcaliresult> queryprogram(String username, String basin);

    @Select("select * from sys_program where username=#{username}")
    List<Modelcaliresult> queryallprogram(String username);

    //      根据用户名和方案名查询方案
    @Select("select * from sys_program where username=#{username} and resultname=#{resultname}")
    Modelcaliresult queryprogramByResultname(String username, String resultname);

    @Select("update sys_program set WUM=#{wum}, WLM=#{wlm}, WDM=#{wdm}, K=#{k}, SM=#{sm}, CS=#{cs}, KSS=#{kss}, KG=#{kg}, KKSS=#{kkss}, KKG=#{kkg} where resultname=#{resultname}")
    List<Caliresult> updaterowdata(double wum, double wlm, double wdm, double k, double sm, double cs,  double kss, double kg, double kkss, double kkg, String resultname);

    @Select("delete from sys_program where resultname=#{resultname}")
    void deleterowdata(String resultname);
}

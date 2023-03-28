package com.example.dao;

import com.example.entity.Modelpar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2022-10-30
 */
public interface ModelparMapper extends BaseMapper<Modelpar> {

    @Select("select * from sys_modelpar where id=#{id}")
    public Modelpar selectAllByIdModelpar(int id);
}

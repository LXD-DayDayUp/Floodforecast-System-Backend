package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LXD
 * @since 2022-10-24
 */
public interface UserMapper extends BaseMapper<User> {
    //    根据用户id查询用户信息
    @Select("select * from sys_user where id=#{userid}")
    public User selectUserByUserid(Integer userid);

    //    根据用户名查询用户信息
    @Select("select * from sys_user where username=#{username}")
    public User selectUserByUsername(String username);

    //    根据用户真名查询用户信息
    @Select("select * from sys_user where real_name=#{realname}")
    public User selectUserByUserrealname(String realname);

    //    修改用户信息
    @Select("update sys_user set avatar=#{avatar},username=#{username},real_name=#{realname},phone=#{phone},email=#{email},gender=#{gender} where id=#{userid}")
    public void reviseUserInfo(String avatar, String username, String realname, String phone, String email, String gender, Integer userid);

    //    查询所有用户信息
    @Select("select * from sys_user")
    public List<User> selectAllUserInfo();

    //    删除用户信息
    @Select("delete from sys_user where id=#{userid}")
    public void deleteUserInfoByUserid(Integer userid);

    //    修改用户密码
    @Select("update sys_user set password=#{newPass} where username=#{username}")
    public void reviseUserPass(String newPass, String username);

//    修改行数据
    @Select("update sys_user set real_name=#{realname},phone=#{phone},email=#{email},department_name=#{departmentName},create_time=#{createTime} where username=#{username}")
    void updaterowdata( String realname, String phone, String email, String departmentName, LocalDate createTime,String username);
}

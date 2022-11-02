package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author LXD
 * @since 2022-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录名称(用户名)
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 帐户是否过期(1-未过期，0-已过期)
     */
    private Integer is_account_non_expired;

    /**
     * 帐户是否被锁定(1-未过期，0-已过期)
     */
    private Integer is_account_non_locked;

    /**
     * 密码是否过期(1-未过期，0-已过期)
     */
    private Integer is_credentials_non_expired;

    /**
     * 帐户是否可用(1-可用，0-禁用)
     */
    private Integer is_enabled;

    /**
     * 真实姓名
     */
    private String real_name;

    /**
     * 昵称
     */
    private String nick_name;

    /**
     * 所属部门ID
     */
    private Long department_id;

    /**
     * 所属部门名称
     */
    private String department_name;

    /**
     * 性别(0-男，1-女)
     */
    private Integer gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 是否是管理员(1-管理员)
     */
    private Integer is_admin;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Integer is_delete;


    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;

    public boolean isAccountNonExpired=true;

    public boolean isAccountNonLocked=true;

    public boolean isCredentialsNonExpired=true;

    public boolean isEnabled=true;

    @TableField(exist = false)
    private List<Permission> permissionList;

}

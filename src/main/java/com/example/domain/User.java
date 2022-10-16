package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

//lombok继承了setter getter tostring 有参和无参构造方法 @Data包括所有
@Data
public class User {
    @TableId(value = "userId")
    private int UserId;
    @TableField(value = "userName")
    private String UserName;
    @TableField(value = "userPass")
    private String UserPass;
    @TableField(value = "userPhone")
    private String UserPhone;
    @TableField(value = "userEmail")
    private String UserEmail;


}
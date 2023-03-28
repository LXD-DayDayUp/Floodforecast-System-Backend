package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.dao.UserMapper;
import com.example.entity.Modelcaliresult;
import com.example.entity.User;
import com.example.service.IUserService;
import com.example.utils.Result;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LXD
 * @since 2022-10-24
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user")
@ResponseBody
public class UserController {
    //    controller是数据操作层
    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;

    //    查询所有用户
    @PostMapping("/selectAllUserInfo")
    public Result listAll() {
        List<User> userInfoList=userMapper.selectAllUserInfo();
        return Result.ok(userInfoList);
    }

    //    根据用户id查询用户个人信息,前端post提交，后端也要post接收
    @PostMapping("/selectUserInfoByUserid")
    public Result userInfoList(@RequestBody Map<String, Object> params) {
        User userinfo=userMapper.selectUserByUserid((Integer) params.get("userid"));
        return Result.ok(userinfo);
    }

//    根据用户id修改用户信息
    @PostMapping("/reviseUsinfoByUserid")
    public Result reviseUserInfo(@RequestBody Map<String,Object> params){
        System.out.println(params);
        String avatar= params.get("avatar").toString();
        String username= params.get("username").toString();
        String realname= params.get("realname").toString();
        String phone= params.get("phone").toString();
        String email= params.get("email").toString();
        String gender= params.get("gender").toString();
        Integer userid=(Integer) params.get("userid");
        userMapper.reviseUserInfo(avatar,username,realname,phone,email,gender,userid);
        return Result.ok();
    }

//    根据用户id删除用户信息
    @PostMapping("/deleteUserInfoByUserid")
    public Result deleteUserInfoByUserid(@RequestBody Map<String,Object> params){
        Integer userid=(Integer) params.get("userid");
        userMapper.deleteById(userid);
        return Result.ok();
    }

//    验证用户密码是否正确
    @PostMapping("/valiUserPass")
    public Result valiUserPass(@RequestBody Map<String,Object> params){
        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        String oldPass=params.get("oldPass").toString();
        String userPass=params.get("userPass").toString();
        boolean a=bcrypt.matches(oldPass,userPass);
        if (a){
            return Result.ok(true);
        }else {
            return Result.ok(false);
        }
    }

    //    验证用户密码是否正确
    @PostMapping("/reviseUserPass")
    public Result reviseUserPass(@RequestBody Map<String,Object> params){
        String username=params.get("username").toString();
        String newPass=params.get("newPass").toString();
        System.out.println(newPass);
        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        String password=bcrypt.encode(newPass);
        userMapper.reviseUserPass(password,username);
        return Result.ok();
    }

    //    修改用户管理数据行
    @PostMapping("/updaterowdata")
    public Result updaterowdata(@RequestBody Map<String,Object> params){
//        将前端传递的参数转换为java对象
        User rowData = JSON.parseObject(JSON.toJSONString(params.get("rowData")), new TypeReference<User>() {
        });
        userMapper.updaterowdata(rowData.getRealName(), rowData.getPhone(), rowData.getEmail(), rowData.getDepartmentName(), rowData.getCreateTime(),rowData.getUsername());
        return Result.ok();
    }
}


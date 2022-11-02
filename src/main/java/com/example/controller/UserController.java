package com.example.controller;


import com.example.service.IUserService;
import com.example.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LXD
 * @since 2022-10-24
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping("/listAll")
    public Result listAll(){
        return Result.ok(userService.list());
    }
}


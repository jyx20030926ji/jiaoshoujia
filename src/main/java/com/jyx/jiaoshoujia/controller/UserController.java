package com.jyx.jiaoshoujia.controller;

import com.jyx.jiaoshoujia.enity.dto.UserLoginDTO;
import com.jyx.jiaoshoujia.enity.po.Result;
import com.jyx.jiaoshoujia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
     public Result<String> userLogin(@RequestBody UserLoginDTO userLoginDTO)
    {
       return Result.success(userService.userLogin(userLoginDTO)) ;
    }

    @GetMapping("/hello")
    public  Result<String> hello()
    {
        return Result.success("hello");
    }

}

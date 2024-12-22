package com.jyx.jiaoshoujia.service.impl;


import com.jyx.jiaoshoujia.Utils.JwtUtils;
import com.jyx.jiaoshoujia.constant.StatusConstant;
import com.jyx.jiaoshoujia.enity.dto.UserLoginDTO;
import com.jyx.jiaoshoujia.enity.po.User;
import com.jyx.jiaoshoujia.exception.AccountNoExistException;
import com.jyx.jiaoshoujia.mapper.UserMapper;
import com.jyx.jiaoshoujia.menu.CodeMessageMenu;
import com.jyx.jiaoshoujia.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    private final Long ttlMillis = 36000000L;

    @Override
    public String userLogin(UserLoginDTO userLoginDTO) {
        User user = userMapper.getUserByAccount(userLoginDTO.getAccount());

        if (user == null) {
            throw new AccountNoExistException(CodeMessageMenu.ACCOUNT_BUSYS);
        } else if (!user.getPassWord().equals(userLoginDTO.getPassWord())) {
            throw new AccountNoExistException(CodeMessageMenu.PASSWORD_BUSYS);
        } else {
            Map claims = new HashMap<>();
            claims.put("userId", user.getId());




            return jwtUtils.encoding(user.getUserName(), ttlMillis, claims);
        }
    }

}

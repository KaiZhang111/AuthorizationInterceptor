package com.zzkk.test.controller;

import com.zzkk.test.annotation.LoginUser;
import com.zzkk.test.annotation.Token;
import com.zzkk.test.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TestController {

    @RequestMapping(value="/test")
    @Token
    public String test(@LoginUser User user){
        System.out.println("需要token才可以访问，呵呵……");
        System.out.println(user);
        return "test";
    }
    @RequestMapping(value="/noToken")
    public String noToken(){
        System.out.println("不用token就可以访问……");
        return "test";
    }
}

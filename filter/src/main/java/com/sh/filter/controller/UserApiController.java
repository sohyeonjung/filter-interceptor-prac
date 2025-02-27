package com.sh.filter.controller;

import com.sh.filter.interceptor.OpenApi;
import com.sh.filter.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @OpenApi
    @PostMapping("")
    public UserRequest register(
            @RequestBody UserRequest userRequest
            //HttpEntity http
    ) {
        //log.info("{}",http.getBody());
        log.info(userRequest.toString());

        //Returning throw 테스트
        //throw new NumberFormatException("");
        return userRequest;
    }

    @GetMapping("/hello")
    public void hello(){
        log.info("hello");
    }



}

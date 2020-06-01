package com.leyou.api;

import com.leyou.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Guqiancheng
 * @date: 2020-05-28 0028 19:57
 * @description:
 */

public interface UserApi {

    @GetMapping("/query")
    public User queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password);
}

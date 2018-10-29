package com.sangoes.boot.uc.modules.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/10/27 9:24 PM
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @GetMapping("/info")
    public String getString() {
        return "home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}

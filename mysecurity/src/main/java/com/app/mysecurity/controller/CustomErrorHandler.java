package com.app.mysecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/error/*")
public class CustomErrorHandler {

    @GetMapping("403")
    public void error403(){;}

    @GetMapping("401")
    public void error401(){;}

}

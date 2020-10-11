package com.stt.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @create 2020-10-10 14:40
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }


}

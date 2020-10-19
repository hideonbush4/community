package com.stt.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @create 2020-10-16 17:11
 */
@Controller
public class ProfileController {
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action")String action
    , Model model){
        if("questions".equals(action)){
            model.addAttribute("section",action);
            model.addAttribute("sectionName","我的提问");
        }

        return "profile";
    }
}

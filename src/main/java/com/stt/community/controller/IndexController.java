package com.stt.community.controller;

import com.stt.community.dto.PaginationDTO;
import com.stt.community.dto.QuestionDTO;
import com.stt.community.mapper.QuestionMapper;
import com.stt.community.mapper.UserMapper;
import com.stt.community.model.Question;
import com.stt.community.model.User;
import com.stt.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @create 2020-10-10 14:40
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest
                        , Model model
                        ,@RequestParam(name="page",defaultValue="1")Integer page
                        ,@RequestParam(name = "size",defaultValue = "2")Integer size){
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null&&cookies.length!=0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token=cookie.getValue();
                    User user=userMapper.findByToken(token);
                    if(user != null){
                        httpServletRequest.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        PaginationDTO paginationDTO = questionService.list(page, size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }


}

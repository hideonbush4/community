package com.stt.community.controller;

import com.stt.community.dto.QuestionDTO;
import com.stt.community.mapper.QuestionMapper;
import com.stt.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @create 2020-10-19 14:30
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Integer id
                        , Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}

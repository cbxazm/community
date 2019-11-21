package com.cbx.springbootcommunity.controller;

import com.cbx.springbootcommunity.domain.Question;
import com.cbx.springbootcommunity.domain.User;
import com.cbx.springbootcommunity.dto.PaginationDTO;
import com.cbx.springbootcommunity.dto.QuestionDto;
import com.cbx.springbootcommunity.mapper.QuestionMapper;
import com.cbx.springbootcommunity.mapper.UserMapper;
import com.cbx.springbootcommunity.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
      public String index(HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "2") Integer size
    ){

        PaginationDTO pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}

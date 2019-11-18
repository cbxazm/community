package com.cbx.springbootcommunity.controller;

import com.cbx.springbootcommunity.domain.Question;
import com.cbx.springbootcommunity.domain.User;
import com.cbx.springbootcommunity.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    //使用get方式则请求页面
    @GetMapping("/publish")
      public String publish(){
           return "publish";
      }
//      使用post方式获取请求页面
      @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
      ){
          /**
           * 出现问题进行回显
           */
          model.addAttribute("title",title);
          model.addAttribute("description",description);
          model.addAttribute("tag",tag);
        if (title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
          if (description==null||description==""){
              model.addAttribute("error","描述不能为空");
              return "publish";
          }
          if (tag==null||tag==""){
              model.addAttribute("error","标签不能为空");
              return "publish";
          }
          User user = (User) request.getSession().getAttribute("user");
          if(user==null){
               model.addAttribute("error","用户未登录");
               return "publish";
          }
          Question question = new Question();
          question.setTitle(title);
          question.setDescription(description);
          question.setTag(tag);
          question.setCreatorId(user.getId());
          question.setGmtCreate(System.currentTimeMillis());
          question.setGmtModified(question.getGmtCreate());
          questionMapper.create(question);
               return "redirect:/";
      }

}
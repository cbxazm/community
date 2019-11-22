package com.cbx.springbootcommunity.controller;

import com.cbx.springbootcommunity.domain.Question;
import com.cbx.springbootcommunity.domain.User;
import com.cbx.springbootcommunity.mapper.QuestionMapper;
import com.cbx.springbootcommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

//修改评论
    @GetMapping("/publish/{id}")
       public String edit(@PathVariable(name = "id") Integer id,Model model){
        Question question = questionMapper.getById(id);
         model.addAttribute("title",question.getTitle());
         model.addAttribute("description",question.getDescription());
         model.addAttribute("tag",question.getTag());
         model.addAttribute("id",question.getId());
        return "publish";
       }

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
            @RequestParam("id") Integer id,
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

          question.setId(id);
          questionService.createOrUpdate(question);
               return "redirect:/";
      }

}

package com.cbx.springbootcommunity.controller;

import com.cbx.springbootcommunity.domain.User;
import com.cbx.springbootcommunity.dto.AccessTokenDto;
import com.cbx.springbootcommunity.dto.GithubUser;
import com.cbx.springbootcommunity.mapper.UserMapper;
import com.cbx.springbootcommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
       public String callback(@RequestParam(name = "code") String code,
                              @RequestParam(name = "state") String state,
                               HttpServletRequest request

                              ){

        AccessTokenDto accessTokenDto = new AccessTokenDto();
          accessTokenDto.setClient_id(clientId);
          accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser);
          if(githubUser!=null){
              User user = new User();
              user.setToken(UUID.randomUUID().toString());
              user.setName(githubUser.getName());
//              String.valueof() 将Long类型转换为字符串
              user.setAccountId(String.valueOf(githubUser.getId()));
              user.setGmtCreate(System.currentTimeMillis());
              user.setGmtModified(user.getGmtCreate());
              userMapper.insert(user);
              //登录成功
             request.getSession().setAttribute("user",githubUser);
             return "redirect:/";
          }else {
                 //失败就重新登录
           return "redirect:/";
          }

       }

}

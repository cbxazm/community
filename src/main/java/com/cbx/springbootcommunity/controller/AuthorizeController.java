package com.cbx.springbootcommunity.controller;

import com.cbx.springbootcommunity.dto.AccessTokenDto;
import com.cbx.springbootcommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
       public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){

        AccessTokenDto accessTokenDto = new AccessTokenDto();
          accessTokenDto.setClient_id("d0c1d996ad5ffb3b9cf5");
          accessTokenDto.setCode(code);
          accessTokenDto.setClient_secret("bae90bbcaf31dc9a5cf7fcc7109f518354ee67bf");
          accessTokenDto.setRedirect_uri("http://localhost:8887/callback");
          accessTokenDto.setState(state);
        githubProvider.getAccessToken(accessTokenDto);
           return "index";
       }

}

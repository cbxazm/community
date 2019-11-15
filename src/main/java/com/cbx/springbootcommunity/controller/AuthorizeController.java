package com.cbx.springbootcommunity.controller;

import com.cbx.springbootcommunity.dto.AccessTokenDto;
import com.cbx.springbootcommunity.dto.GithubUser;
import com.cbx.springbootcommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
       public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){

        AccessTokenDto accessTokenDto = new AccessTokenDto();
          accessTokenDto.setClient_id(clientId);
          accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);
        return "index";
       }

}

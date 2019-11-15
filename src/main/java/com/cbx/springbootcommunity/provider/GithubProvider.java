package com.cbx.springbootcommunity.provider;

import com.alibaba.fastjson.JSON;
import com.cbx.springbootcommunity.dto.AccessTokenDto;
import com.cbx.springbootcommunity.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
            public String getAccessToken(AccessTokenDto accessTokenDto){
                MediaType mediaType = MediaType.get("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                    RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
                    Request request = new Request.Builder()
                            .url("https://github.com/login/oauth/access_token")
                            .post(body)
                            .build();
                    try (Response response = client.newCall(request).execute()) {
                        String string = response.body().string();
                        System.out.println(string);//access_token=40227727e2ba54a22f2c08eb73d6ba81fd5162ca&scope=user&token_type=bearer
                        /**
                         *     进行分割，拿到acces_token的值

                         */
                        String[] split = string.split("&");
                        String tokenStr = split[0];
                        String token = tokenStr.split("=")[1];
                        return token;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
              return null;
            }
//            public GithubUser getUser(String accessToken){
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url("https://api.github.com/user?access_token="+accessToken)
//                        .build();
//
//                try (Response response = client.newCall(request).execute()) {
////                    return  response.body().string();
//                    String string=response.body().toString();
////                    把string对象转换成为java的类对象
//                    GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
//                    System.out.println(githubUser);
//                    return githubUser;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
public GithubUser getUser(String accessToken) {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url("https://api.github.com/user?access_token=" + accessToken)
            .build();
    try {
        Response response = client.newCall(request).execute();
        String string = response.body().string();
        GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
        return githubUser;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
}

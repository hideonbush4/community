package com.stt.community.provider;

import com.alibaba.fastjson.JSON;
import com.stt.community.dto.AccessTokenDTO;
import com.stt.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @create 2020-10-12 9:22
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            String token=string.split("&")[0].split("=")[1];
            return token;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public GithubUser getUser(String accessToken){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request =new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            String string=response.body().string();
            GithubUser githubUser= JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {

        }
        return null;


    }
}

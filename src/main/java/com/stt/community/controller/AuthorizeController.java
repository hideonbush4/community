package com.stt.community.controller;

import com.stt.community.dto.AccessTokenDTO;
import com.stt.community.dto.GithubUser;
import com.stt.community.mapper.UserMapper;
import com.stt.community.model.User;
import com.stt.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


/**
 * @create 2020-10-11 10:38
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state") String state,
                            HttpServletRequest request ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if(user!=null){
            //登录成功，写cookie和session
            User user1 = new User();
            user1.setToken(UUID.randomUUID().toString());
            user1.setName(user.getName());
            user1.setAccount_id(user.getId().toString());
            user1.setGmt_create(System.currentTimeMillis());
            user1.setGmt_modified(user1.getGmt_modified());

            userMapper.insert(user1);
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }
}

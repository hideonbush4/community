package com.stt.community.service;

import com.stt.community.mapper.UserMapper;
import com.stt.community.model.User;
import com.stt.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2020-10-19 16:06
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
//        User dbUser =userMapper.findByAccountId(user.getAccount_id());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==0){//新建用户
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {//更新用户
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
//            userMapper.update(dbUser);
            UserExample example=new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());

            userMapper.updateByExampleSelective(updateUser,example);
        }

    }
}

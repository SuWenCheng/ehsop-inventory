/*
package com.alwin.eshop.inventory.service.impl;

import com.alwin.eshop.inventory.dao.RedisDao;
import com.alwin.eshop.inventory.mapper.UserMapper;
import com.alwin.eshop.inventory.model.User;
import com.alwin.eshop.inventory.service.UserService;
import com.alwin.eshop.inventory.util.JsonHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final RedisDao redisDao;

    @Override
    public User getUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCachedUser() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(25);
        redisDao.set("user_cache", JsonHelper.toJson(user));
        String user_cache = redisDao.get("user_cache");

        return JsonHelper.fromJson(user_cache, User.class);
    }
}
*/

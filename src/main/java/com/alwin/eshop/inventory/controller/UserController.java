/*
package com.alwin.eshop.inventory.controller;

import com.alwin.eshop.inventory.model.User;
import com.alwin.eshop.inventory.service.UserService;
import com.alwin.eshop.inventory.util.JsonHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserInfo")
    public User getUserInfo() {
        User user = userService.getUserInfo();
        if (user != null) {
            log.info(JsonHelper.toJson(user));
        }
        return user;
    }

    @GetMapping("/getCacheUser")
    public User getCacheUser() {
        User user = userService.getCachedUser();
        if (user != null) {
            log.info(JsonHelper.toJson(user));
        }
        return user;
    }

}
*/

package com.alwin.eshop.inventory.service;

import com.alwin.eshop.inventory.model.User;

public interface UserService {
    User getUserInfo();

    User getCachedUser();
}

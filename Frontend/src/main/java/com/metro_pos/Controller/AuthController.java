package com.metro_pos.Controller;

import com.metro_pos.Service.UserService;

public class AuthController {

    private UserService userService;

    public AuthController() {
        this.userService = new UserService();
    }

    public void authenticate(String userName, String password) {

        userService.getByUserName(userName);

        return;
    }
}
package com.metro_pos.Controller;

import java.util.Map;

import com.metro_pos.Model.User;
import com.metro_pos.Service.UserService;
import com.metro_pos.Store.UserStore;

public class AuthController {

    private UserService userService;

    public AuthController() {
        this.userService = new UserService();
    }

    public boolean authenticate(String userName, String password, String role) {

        User user = userService.getByUserNameAndPasswordAndRole(userName, password, role);

        if (user == null) {
            return false;
        }

        UserStore.setCurrentUser(user);

        return true;
    }

    public boolean updatePassword(String password) {
        return userService.update(password);
    }
}
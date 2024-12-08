package com.metro_pos.Store;

import com.metro_pos.Model.User;

public class UserStore {
    private static User currentUser;

    protected UserStore() {
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}

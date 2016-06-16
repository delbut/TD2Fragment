package com.esgi.td2fragment.utils;

import com.esgi.td2fragment.models.User;

/**
 * Created by maxime on 15/06/16.
 */
public final class SessionData {

    private static final SessionData INSTANCE = new SessionData();

    private User currentUser;

    public SessionData() {
    }

    public static SessionData getInstance() {
        return INSTANCE;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        PreferenceHelper.setPreviousLogin(currentUser.getLogin());
        this.currentUser = currentUser;
    }
}

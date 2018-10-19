package com.study.shop.security;

import com.study.shop.entity.User;
import com.study.shop.security.entity.Session;
import com.study.shop.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SecurityService {
    private List<Session> sessionList = new ArrayList<>();
    private UserService userService;

    public Session auth(String login, String password) {
        User user = userService.getUser(login, password);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            Session session = new Session();
            session.setUser(user);
            session.setToken(token);

            sessionList.add(session);
            return session;
        }

        return null;
    }

    public Session getSession(String userToken) {
        return null;
    }
}

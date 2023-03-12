package com.platzi.fundamentos.useCases;

import com.platzi.fundamentos.entity.User;
import com.platzi.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplements implements GetUser{
    private UserService userService;

    public GetUserImplements(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}

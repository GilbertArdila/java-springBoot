package com.platzi.fundamentos.useCases;

import com.platzi.fundamentos.entity.User;
import com.platzi.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUsersImplemets {
    private UserService userService;

    public CreateUsersImplemets(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
       return userService.save(newUser);
    }
}

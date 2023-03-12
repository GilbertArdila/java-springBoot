package com.platzi.fundamentos.useCases;

import com.platzi.fundamentos.entity.User;
import com.platzi.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUsersImplemets {
    private UserService userService;

    public UpdateUsersImplemets(UserService userService) {
        this.userService = userService;
    }

    public User update(User newUser, Long id) {
            return userService.update(newUser,id);
    }
}

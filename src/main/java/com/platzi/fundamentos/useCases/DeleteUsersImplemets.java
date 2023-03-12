package com.platzi.fundamentos.useCases;

import com.platzi.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUsersImplemets {
    private UserService userService;

    public DeleteUsersImplemets(UserService userService) {
        this.userService = userService;
    }

    public void delete(long id) {
        userService.delete(id);
    }
}

package com.platzi.fundamentos.configuration;

import com.platzi.fundamentos.service.UserService;
import com.platzi.fundamentos.useCases.GetUser;
import com.platzi.fundamentos.useCases.GetUserImplements;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class useCasesConfiguration {

    @Bean
    GetUser getUser(UserService userService){
       return new GetUserImplements(userService);
    }
}

package com.platzi.fundamentos.service;

import com.platzi.fundamentos.entity.User;
import com.platzi.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    //creamos log para los mensajes en consola
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //metodo para guardar un usuario
    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("Usuario insertado "+user))
                .forEach(userRepository::save);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(long id) {
         userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return
        userRepository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setName(newUser.getName());
                    user.setBirthday(newUser.getBirthday());
                    return userRepository.save(user);
                }).get();

    }
}

package com.platzi.fundamentos.controller;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.platzi.fundamentos.entity.User;
import com.platzi.fundamentos.useCases.CreateUsersImplemets;
import com.platzi.fundamentos.useCases.DeleteUsersImplemets;
import com.platzi.fundamentos.useCases.GetUser;
import com.platzi.fundamentos.useCases.UpdateUsersImplemets;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;

//esta anotación nos formatea los datos en Json
@RestController
@RequestMapping("/users")
public class UserRestController {
 private GetUser getUser;
 private CreateUsersImplemets createUser;
 private DeleteUsersImplemets deleteUser;
 private UpdateUsersImplemets updateUser;

    public UserRestController(GetUser getUser,CreateUsersImplemets createUser,DeleteUsersImplemets deleteUser,UpdateUsersImplemets updateUser) {
        this.getUser = getUser;
        this.createUser=createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }

    @GetMapping("/all")
    List<User> get(){
        return getUser.getAll();
    }

    @PostMapping("/post")
    ResponseEntity<User> newUser(@RequestBody User newUser){
        return  new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity deleteUser(@PathVariable long id){
        deleteUser.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser,@PathVariable Long id){
       updateUser.update(newUser,id);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}

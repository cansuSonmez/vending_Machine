package com.example.vendingMachine.controller;

import com.example.vendingMachine.entity.User;
import com.example.vendingMachine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(path = "/insert",consumes = MediaType.APPLICATION_JSON_VALUE)
    private String save(@RequestBody User user){
        userService.save(user);
        return "başarılı";
    }

    @DeleteMapping(path = "/delete/{user_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private String delete(@PathVariable(name = "user_id") int user_id){

        return  userService.deleteById(user_id);
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update (@RequestBody User user ){
        return userService.updateUser(user);
    }

}

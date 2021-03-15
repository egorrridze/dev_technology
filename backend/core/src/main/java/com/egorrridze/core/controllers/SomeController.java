package com.egorrridze.core.controllers;

import com.egorrridze.core.models.User;
import com.egorrridze.core.services.UserService;
import liquibase.pro.packaged.U;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SomeController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/hello/{id}")
    @ResponseBody
    public User sayHello(@PathVariable Long id){
        return userService.getUser(id);
    }
}

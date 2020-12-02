package com.apotik.controller.api;

import com.apotik.dto.UserRegistrationDTO;
import com.apotik.entity.User;
import com.apotik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/registration")
    public User registrationUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        return userService.save(userRegistrationDTO);
    }

}

package com.apotik.controller.api;

import com.apotik.dto.user.UserRegistrationDTO;
import com.apotik.entity.User;
import com.apotik.service.user.UserService;
import com.apotik.utils.ErrorUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUsers")
    public List<User> getUsers(){
        try{
            return userService.getUsers();
        }catch (Exception ex){
            throw new RuntimeException("error : "+ex.getMessage());
        }

    }

    @PostMapping(value = "/registration")
    public String registrationUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO,
                                 BindingResult bindingResult)throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }
        else{
            try{
                userService.save(userRegistrationDTO);
                jsonObject.put("status", "Success");
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
            return jsonObject.toString();
        }
    }

}

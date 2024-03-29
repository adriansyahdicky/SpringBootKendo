package com.apotik.service.user;

import com.apotik.dto.user.UserRegistrationDTO;
import com.apotik.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDTO userRegistrationDTO);
    List<User> getUsers();

}

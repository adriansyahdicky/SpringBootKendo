package com.apotik.service;

import com.apotik.dto.UserRegistrationDTO;
import com.apotik.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDTO userRegistrationDTO);

}

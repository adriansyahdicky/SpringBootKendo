package com.apotik.repository;

import com.apotik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositroy extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

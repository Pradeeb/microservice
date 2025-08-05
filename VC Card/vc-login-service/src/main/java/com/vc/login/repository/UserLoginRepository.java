package com.vc.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vc.login.entity.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String> {
    boolean existsByUsername(String username);
    UserLogin findByUsername(String username);
}
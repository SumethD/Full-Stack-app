package com.sept_g4.sept_project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService {


    LoginResponse loginUser(LoginDTO loginDTO);
}

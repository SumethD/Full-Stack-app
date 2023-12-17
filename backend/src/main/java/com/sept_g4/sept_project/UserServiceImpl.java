package com.sept_g4.sept_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg = "";
        Users user = repo.findByEmail(loginDTO.getEmail());
        // Check Eamil is exist or not
        if (user != null) {
            String password = loginDTO.getPassword();
            String userPassword = user.getPassword();
            boolean isPwdRight = password.equals(userPassword);
            //Return true Password is matched with user input and DB password
            if (isPwdRight) {
                Optional<Users> User = repo.findOneByEmailAndPassword(loginDTO.getEmail(), userPassword);
                if (User.isPresent()) {
                    //Case : Login is success
                    return new LoginResponse("Login Success", true);
                } else {
                    //Case : Login is fail
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                //Case : password is not mathced
                return new LoginResponse("password Not Match", false);
            }
        }else {
             //Case : Email is not exist
            return new LoginResponse("Email not exits", false);
        }
    }

}

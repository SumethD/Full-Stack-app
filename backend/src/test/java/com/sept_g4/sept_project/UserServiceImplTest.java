package com.sept_g4.sept_project;
import com.sept_g4.sept_project.LoginDTO;
import com.sept_g4.sept_project.LoginResponse;
import com.sept_g4.sept_project.Users;
import com.sept_g4.sept_project.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import java.util.Date;
import java.text.ParseException;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoginUser_Success() {
        // Arranges and creates user with details
        LoginDTO loginDTO = new LoginDTO("john@test1.com", "password");
        Users mockUser = new Users();
        mockUser.setEmail("john@test1.com");
        mockUser.setPassword("password");
        mockUser.setContact("0123456789");
    
        // Create a SimpleDateFormat to parse the date from a string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dobDate = null;
        try {
            dobDate = sdf.parse("1990-01-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    
        mockUser.setDob(dobDate);
    
        // Mocking userRepository's behavior and passes the values
        when(userRepository.findByEmail(eq("john@test1.com"))).thenReturn(mockUser);
    
        // Mock the findOneByEmailAndPassword to return a value when called
        when(userRepository.findOneByEmailAndPassword(eq("john@test1.com"), eq("password"))).thenReturn(Optional.of(mockUser));
    
        // Attempt to login
        LoginResponse loginResponse = userService.loginUser(loginDTO);
    
        // Expects login to succeed
        assertEquals("Login Success", loginResponse.getMessage());
        assertEquals(true, loginResponse.getStatus());
    
        // Verify that the userRepository methods were called
        verify(userRepository, times(1)).findByEmail(eq("john@test1.com"));
        verify(userRepository, times(1)).findOneByEmailAndPassword(eq("john@test1.com"), eq("password"));
    }


    @Test
    void testLoginUser_IncorrectPassword() {
        // Arranges and creates user with details
        LoginDTO loginDTO = new LoginDTO("john@test1.com", "incorrect_password");
        Users user = new Users();
        user.setFirstname("John");
        user.setSurname("Doe");
        user.setEmail("john@test1.com");
        user.setContact("0123456789");
        user.setPassword("password");


        // Mocking userRepository's behavior and passes the values
        when(userRepository.findByEmail(eq("john@test1.com"))).thenReturn(user);

        // Attemps to login
        LoginResponse loginResponse = userService.loginUser(loginDTO);

        // expects login to fail
        assertEquals("password Not Match", loginResponse.getMessage());
        assertEquals(false, loginResponse.getStatus());

        // Verify that the userRepository method was called
        verify(userRepository, times(1)).findByEmail(eq("john@test1.com"));
    }

    @Test
    void testLoginUser_UserNotFound() {
        // Arranges and creates user with details
        LoginDTO loginDTO = new LoginDTO("unknown@test1.com", "password");

        // Mocking userRepository's behavior to return null (user not found)
        when(userRepository.findByEmail(eq("unknown@test1.com"))).thenReturn(null);

        // attemps to login
        LoginResponse loginResponse = userService.loginUser(loginDTO);

        // says email does not exist if the details are entered
        assertEquals("Email not exits", loginResponse.getMessage());
        assertEquals(false, loginResponse.getStatus());

        // Verify that the userRepository method was called
        verify(userRepository, times(1)).findByEmail(eq("unknown@test1.com"));
    }
}


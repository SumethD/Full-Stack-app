package com.sept_g4.sept_project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginDTOTest {

    private LoginDTO loginDTO;

    @BeforeEach
    public void setUp() {
        // Initialize an object with sample data before each test
        loginDTO = new LoginDTO("test@test1.com", "password123");
    }

    @Test
    public void testGetEmail() {
        // Test the getEmail() method by passing in a test email
        assertEquals("test@test1.com", loginDTO.getEmail());
    }

    @Test
    public void testGetPassword() {
        // Test the getPassword() method by passing in a random password
        assertEquals("password123", loginDTO.getPassword());
    }

    @Test
    public void testSetEmail() {
        // Test the setEmail() method by setting the email and checking if it is equal
        loginDTO.setEmail("emailSet@set.com");
        assertEquals("emailSet@set.com", loginDTO.getEmail());
    }

    @Test
    public void testSetPassword() {
        // Test the setPassword() method by passing a password and checking if it it gets set.
        loginDTO.setPassword("passwordChanged");
        assertEquals("passwordChanged", loginDTO.getPassword());
    }

    @Test
    public void testToString() {
        // Test the toString() method and converts to string
        String expectedToString = "LoginDTO{" +
                "email='test@test1.com', " +
                "password='password123'}";
        assertEquals(expectedToString, loginDTO.toString());
    }
}


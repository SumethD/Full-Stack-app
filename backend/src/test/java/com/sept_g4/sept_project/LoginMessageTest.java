package com.sept_g4.sept_project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginMessageTest {

    private LoginMessage loginMessage;

    @BeforeEach
    public void setUp() {
        // Initialize a LoginMessage object with sample data before each test
        loginMessage = new LoginMessage("Login successful.", true);
    }

    @Test
    public void testGetMessage() {
        // Test the getMessage() method
        assertEquals("Login successful.", loginMessage.getMessage());
    }

    @Test
    public void testIsLoggedIn() {
        // Test the isLoggedIn() method
        assertEquals(true, loginMessage.isLoggedIn());
    }

    @Test
    public void testSetMessage() {
        // Test the setMessage() method
        loginMessage.setMessage("Authentication failed.");
        assertEquals("Authentication failed.", loginMessage.getMessage());
    }

    @Test
    public void testSetLoggedIn() {
        // Test the setLoggedIn() method
        loginMessage.setLoggedIn(false);
        assertEquals(false, loginMessage.isLoggedIn());
    }

    @Test
    public void testToString() {
        // Test the toString() method
        String expectedToString = "LoginMessage{" +
                "message='Login successful.', " +
                "loggedIn=true}";
        assertEquals(expectedToString, loginMessage.toString());
    }
}


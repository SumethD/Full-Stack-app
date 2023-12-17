package com.sept_g4.sept_project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginResponseTest {

    private LoginResponse loginResponse;

    @BeforeEach
    public void setUp() {
        // Initialize a LoginResponse object with sample data before each test
        loginResponse = new LoginResponse("Login successful.", true);
    }

    @Test
    public void testGetMessage() {
        // Test the getMessage() method
        assertEquals("Login successful.", loginResponse.getMessage());
    }

    @Test
    public void testGetStatus() {
        // Test the getStatus() method
        assertEquals(true, loginResponse.getStatus());
    }

    @Test
    public void testSetMessage() {
        // Test the setMessage() method
        loginResponse.setMessage("Authentication failed.");
        assertEquals("Authentication failed.", loginResponse.getMessage());
    }

    @Test
    public void testSetStatus() {
        // Test the setStatus() method
        loginResponse.setStatus(false);
        assertEquals(false, loginResponse.getStatus());
    }

    @Test
    public void testToString() {
        // Test the toString() method
        String expectedToString = "LoginResponse{" +
                "message='Login successful.', " +
                "status=true}";
        assertEquals(expectedToString, loginResponse.toString());
    }
}


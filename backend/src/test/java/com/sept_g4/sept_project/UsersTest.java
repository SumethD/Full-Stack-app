package com.sept_g4.sept_project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsersTest {

    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users(1L, "John", "Jonathan", getDate("1990-01-15"), "john@test1.com", "1234567890", "password");
    }

    @Test
    void testConstructorAndGetters() {
        //attempts to check if the details entered before each test is returned
        assertAll("Verify user",
            () -> assertEquals(1L, user.getId()),
            () -> assertEquals("John", user.getFirstname()),
            () -> assertEquals("Jonathan", user.getSurname()),
            () -> assertEquals(getDate("1990-01-15"), user.getDob()),
            () -> assertEquals("john@test1.com", user.getEmail()),
            () -> assertEquals("1234567890", user.getContact()),
            () -> assertEquals("password", user.getPassword())
        );

    }

    @Test
    void testSetters() {
        //sets details
        user.setId(2L);
        user.setFirstname("Alicia");
        user.setSurname("Samuel");
        user.setDob(getDate("1985-05-20"));
        user.setEmail("alice@test1.com");
        user.setContact("9876543210");
        user.setPassword("newPassword");


        ////attempts to check if the details set now are returned accoridngly
        assertAll("Verify user",
            () -> assertEquals(2L, user.getId()),
            () -> assertEquals("Alicia", user.getFirstname()),
            () -> assertEquals("Samuel", user.getSurname()),
            () -> assertEquals(getDate("1985-05-20"), user.getDob()),
            () -> assertEquals("alice@test1.com", user.getEmail()),
            () -> assertEquals("9876543210", user.getContact()),
            () -> assertEquals("newPassword", user.getPassword())
        );

    }

    @Test
    void testSettersWithNullValues() {
        //sets all values to null
        user.setId(null);
        user.setFirstname(null);
        user.setSurname(null);
        user.setDob(null);
        user.setEmail(null);
        user.setContact(null);
        user.setPassword(null);

        //verifies user by passing the null values and expecting it to return null
        assertAll("Verify user",
            () -> assertNull(user.getId()),
            () -> assertNull(user.getFirstname()),
            () -> assertNull(user.getSurname()),
            () -> assertNull(user.getDob()),
            () -> assertNull(user.getEmail()),
            () -> assertNull(user.getContact()),
            () -> assertNull(user.getPassword())
        );

    }

    private Date getDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

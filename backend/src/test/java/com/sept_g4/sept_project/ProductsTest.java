package com.sept_g4.sept_project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductsTest {

    private Products product;

    @BeforeEach
    public void setUp() {
        // Create a new product before each test
        product = new Products("Sample Product", "10.00");
    }

    @Test
    public void testConstructorAndGetters() {
        // Test the constructor and getters checks if the sample products name, price and id gets printed or not
        assertAll("Verify product",
            () -> assertEquals("Sample Product", product.getName()),
            () -> assertEquals("10.00", product.getPrice()),
            () -> assertNull(product.getId())
        );

    }

    @Test
    public void testSetters() {
        // Test the setters
        product.setName("Updated Product");
        product.setPrice("15.00");
        assertEquals("Updated Product", product.getName());
        assertEquals("15.00", product.getPrice());
    }

    @Test
    public void testToString() {
        // Test the toString method
        String expectedString = "Products{" +
                "id=null, " +
                "name='Sample Product', " +
                "price='10.00'}";
        assertEquals(expectedString, product.toString());
    }
}


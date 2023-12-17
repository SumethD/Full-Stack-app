package com.sept_g4.sept_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class RepoActionsTest {
    private Products product;


    //setsup before each
    @BeforeEach
    public void setUp() {
        // Initialize a Products object with sample data before each test
        product = new Products("Sample Product", "10.99");
        product.setId(1L); // Set the ID separately
    }

    @Test
    public void testGetId() {
        // Test the getId() method by retrieving the product's ID
        assertEquals(1L, product.getId());
    }

    @Test
    public void testGetName() {
        // Test the getName() method by getting the products name
        assertEquals("Sample Product", product.getName());
    }

    @Test
    public void testGetPrice() {
        // Test the getPrice() method by getting the products price
        assertEquals("10.99", product.getPrice());
    }

    @Test
    public void testSetName() {
        // Test the setName() method by setting a new name and checking if it is equal
        product.setName("New Product");
        assertEquals("New Product", product.getName());
    }

    @Test
    public void testSetPrice() {
        // Test the setPrice() method by setting a new price and checking if it is equal
        product.setPrice("15.99");
        assertEquals("15.99", product.getPrice());
    }

    @Test
    public void testToString() {
        // Test the toString() method and convert the product to a string representation
        String expectedToString = "Products{" +
                "id=1, " +
                "name='Sample Product', " +
                "price='10.99'}";
        assertEquals(expectedToString, product.toString());
    }

}
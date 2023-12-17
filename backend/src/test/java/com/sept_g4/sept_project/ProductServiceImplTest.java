package com.sept_g4.sept_project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchProducts() {
        // Define a search query
        String query = "apple";

        // Create a list of sample products with names and prices that get added in
        List<Products> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Products("Apple", "1.00"));
        sampleProducts.add(new Products("Banana", "0.50"));

        // Mock the behavior of the productRepository
        when(productRepository.findByItemContainingIgnoreCase(query)).thenReturn(sampleProducts);

        // Call the service method which is the search prod method
        List<Products> foundProducts = productService.searchProducts(query);

        // Verify that the service method returns the expected products by checking if the expected output matches the output that is recieved
        assertAll("Verify found products",
            () -> assertEquals(2, foundProducts.size()),
            () -> assertEquals("Apple", foundProducts.get(0).getName()),
            () -> assertEquals("Banana", foundProducts.get(1).getName())
        );

        // Verify that the productRepository method was called once
        verify(productRepository, times(1)).findByItemContainingIgnoreCase(query);
    }

    @Test
    public void testGetProductById() {
        // Define a sample product ID
        Long productId = 1L;

        // Create a sample product
        Products sampleProduct = new Products("Apple", "1.00");

        // Mock the behavior of the productRepository
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.ofNullable(sampleProduct));

        // Call the service method
        Products retrievedProduct = productService.getProductById(productId);

        // Verify that the service method returns the expected product
        assertEquals("Apple", retrievedProduct.getName());

        // Verify that the productRepository method was called once
        verify(productRepository, times(1)).findById(productId);
    }
    @Test
    public void testSearchProductsEmptyResult() {
        // Define a search query that doesn't match any products
        String query = "nonexistent";

        // Mock the behavior of the productRepository to return an empty list
        when(productRepository.findByItemContainingIgnoreCase(query)).thenReturn(new ArrayList<>());

        // Call the service method
        List<Products> foundProducts = productService.searchProducts(query);

        // Verify that the service method returns an empty list
        assertTrue(foundProducts.isEmpty());

        // Verify that the productRepository method was called once
        verify(productRepository, times(1)).findByItemContainingIgnoreCase(query);
    }

    @Test
    public void testGetProductByIdNotFound() {
        // Define a sample product ID that does not exist
        Long productId = 100L;

        // Mock the behavior of the productRepository to return an empty optional
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.empty());

        // Call the service method
        Products retrievedProduct = productService.getProductById(productId);

        // Verify that the service method returns null for a non-existent product
        assertNull(retrievedProduct);

        // Verify that the productRepository method was called once
        verify(productRepository, times(1)).findById(productId);
    }


    @Test
    public void testSearchProductsMultipleResults() {
        // Define a search query that matches multiple products
        String query = "apple";

        // Create a list of sample products with names and prices
        List<Products> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Products("Apple", "1.00"));
        sampleProducts.add(new Products("Apple Pie", "2.50"));

        // Mock the behavior of the productRepository to return multiple results
        when(productRepository.findByItemContainingIgnoreCase(query)).thenReturn(sampleProducts);

        // Call the service method
        List<Products> foundProducts = productService.searchProducts(query);

        // Verify that the service method returns all matching products
        assertEquals(2, foundProducts.size());

        // Verify that the productRepository method was called once
        verify(productRepository, times(1)).findByItemContainingIgnoreCase(query);
    }

    @Test
    public void testGetProductByIdMultipleResults() {
        // Define a sample product ID that exists multiple times
        Long productId = 1L;

        // Create a list of sample products with the same ID but different names and prices
        List<Products> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Products("Apple", "1.00"));
        sampleProducts.add(new Products("Banana", "0.50"));

        // Mock the behavior of the productRepository to return multiple results
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.ofNullable(sampleProducts.get(0)));

        // Call the service method
        Products retrievedProduct = productService.getProductById(productId);

        // Verify that the service method returns the first matching product
        assertEquals("Apple", retrievedProduct.getName());

        // Verify that the productRepository method was called once
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testSearchProductsCaseInsensitive() {
        // Define a search query with mixed case
        String query = "ApPlE";

        // Create a list of sample products with names and prices that get added in
        List<Products> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Products("Apple", "1.00"));

        // Mock the behavior of the productRepository
        when(productRepository.findByItemContainingIgnoreCase(query)).thenReturn(sampleProducts);

        // Call the service method with a case-insensitive query
        List<Products> foundProducts = productService.searchProducts(query);

        // Verify that the service method returns the expected products by checking if the expected output matches the output that is received
        assertAll("Verify found products",
                () -> assertEquals(1, foundProducts.size()),
                () -> assertEquals("Apple", foundProducts.get(0).getName())
        );

        // Verify that the productRepository method was called once
        verify(productRepository, times(1)).findByItemContainingIgnoreCase(query);
    }

    @Test
    public void testSearchProductsSpecialCharacters() {
        // Define a search query with special characters
        String query = "!@#$%";

        // Mock the behavior of the productRepository to return an empty list for special characters
        when(productRepository.findByItemContainingIgnoreCase(query)).thenReturn(new ArrayList<>());

        // Call the service method with a query containing special characters
        List<Products> foundProducts = productService.searchProducts(query);

        // Verify that the service method returns an empty list for special characters
        assertTrue(foundProducts.isEmpty());

        // Verify that the productRepository method was called once
        verify(productRepository, times(1)).findByItemContainingIgnoreCase(query);
    }

}


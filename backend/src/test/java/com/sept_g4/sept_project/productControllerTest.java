package com.sept_g4.sept_project;
import com.sept_g4.sept_project.ProductController;
import com.sept_g4.sept_project.ProductService;
import com.sept_g4.sept_project.Products;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class productControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productController = new ProductController(productService);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testSearchProducts() throws Exception {
        // Sample products for testing added in
        List<Products> productsList = Arrays.asList(
            new Products("Product 1", "10.00"),
            new Products("Product 2", "15.00")
        );

        // Mock ProductService behavior
        when(productService.searchProducts(anyString())).thenReturn(productsList);

        // Perform GET request to the endpoint and compare values that are recieved to the expected values.
        mockMvc.perform(get("/api/products/search")
            .param("query", "Product"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Product 1"))
            .andExpect(jsonPath("$[1].name").value("Product 2"));
    }

    @Test
    public void testGetProductDetails() throws Exception {
        // Sample product for testing added in
        Products product = new Products("Product 1", "10.00");

        // Mock ProductService behavior
        when(productService.getProductById(1L)).thenReturn(product);

        // Perform GET request to the endpoint and compare values that are recieved to the expected values.
        mockMvc.perform(get("/api/products/1/Product"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Product 1"))
            .andExpect(jsonPath("$.price").value("10.00"));
    }
}



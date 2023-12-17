package com.sept_g4.sept_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public List<Products> searchProducts(@RequestParam String query) {
        // Can search the all items matched with query 
        return productService.searchProducts(query);
    }

    @GetMapping("/{id}/{searchQuery}")
    public Products getProductDetails(@PathVariable Long id, @PathVariable String searchQuery) {
        // Here, you can use 'id' and 'searchQuery' to fetch product details accordingly
        return productService.getProductById(id);
    }
    @GetMapping("/{id}")
    public Products getProduct(@PathVariable Long id) {
        // Here, you can use 'id' and 'searchQuery' to fetch product details accordingly
        return productService.getProductById(id);
    }

    @GetMapping
    // List of the Featured product 
    public List<Products> getFeatured_Product(){
        return productService.featuredProduct();
    }
}

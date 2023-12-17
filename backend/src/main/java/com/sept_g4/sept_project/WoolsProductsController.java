package com.sept_g4.sept_project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/woolsproducts")
public class WoolsProductsController {

    private final WoolsProductsService woolsProductsService;

    @Autowired
    public WoolsProductsController(WoolsProductsService woolsProductsService) {
        this.woolsProductsService = woolsProductsService;
    }

    // This endpoint handles GET requests to search for WoolsProducts based on a query parameter.
    @GetMapping("/search")
    public List<WoolsProducts> searchWoolsProducts(@RequestParam String query) {
        return woolsProductsService.searchWoolsProducts(query);
    }
    
    // This endpoint handles GET requests to retrieve a specific WoolsProduct by its ID.
    @GetMapping("/{id}")
    public WoolsProducts getWoolsProductById(@PathVariable Long id) {
        return woolsProductsService.getWoolsProductById(id);
    }
}

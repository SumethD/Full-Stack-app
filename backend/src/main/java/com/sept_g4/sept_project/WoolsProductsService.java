package com.sept_g4.sept_project;
import java.util.List;

public interface WoolsProductsService {

    // Method to search for WoolsProducts based on a query
    List<WoolsProducts> searchWoolsProducts(String query);

    // Method to get a specific WoolsProduct by its ID.
    WoolsProducts getWoolsProductById(Long id);
}

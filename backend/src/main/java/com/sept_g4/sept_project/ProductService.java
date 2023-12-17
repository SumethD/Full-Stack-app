package com.sept_g4.sept_project;

import java.util.List;

public interface ProductService {


    List<Products> searchProducts(String query);
    
    Products getProductById(Long productId);

    List<Products> featuredProduct();
}

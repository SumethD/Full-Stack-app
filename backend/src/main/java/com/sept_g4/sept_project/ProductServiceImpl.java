package com.sept_g4.sept_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Optional;


// A service class that implements the ProductService interface.
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // Constructor injection of the ProductRepository dependency.
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Method to search for products based on a query and return a list of matching products.
    @Override
    public List<Products> searchProducts(String query) {
        return productRepository.findByItemContainingIgnoreCase(query);
    }

    // Method to get a product by its unique productId.
    @Override
    public Products getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    // Method to retrieve a list of featured products.

    @Override
    public List<Products> featuredProduct() {
        List<Products> Featured_Porduct_List = new ArrayList<>();
        Random random = new Random();
        for(int i = 0 ; i < 10; ++i){

            Products pr;
            Long random_ProductID = random.nextLong(0,1000);
            pr = productRepository.findColesProductById(random_ProductID);

            if(pr != null){
                Featured_Porduct_List.add(pr);
            }

        }
        return Featured_Porduct_List;
    }
}

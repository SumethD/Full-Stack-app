package com.sept_g4.sept_project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WoolsProductsServiceImpl implements WoolsProductsService {

    private final WoolsProdRepository woolsProductsRepository;

    @Autowired
    public WoolsProductsServiceImpl(WoolsProdRepository woolsProductsRepository) {
        this.woolsProductsRepository = woolsProductsRepository;
    }

    // This method implements the search functionality for WoolsProducts based on a query.
    @Override
    public List<WoolsProducts> searchWoolsProducts(String query) {
        return woolsProductsRepository.findByItemContainingIgnoreCase(query);
    }
    
    // This method retrieves a specific WoolsProduct by its ID.
    @Override
    public WoolsProducts getWoolsProductById(Long id) {
        return woolsProductsRepository.findById(id).orElse(null);
    }
}

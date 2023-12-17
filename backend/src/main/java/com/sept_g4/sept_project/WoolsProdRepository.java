package com.sept_g4.sept_project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WoolsProdRepository extends JpaRepository<WoolsProducts, Long> {
    List<WoolsProducts> findByItemContainingIgnoreCase(String query);
    WoolsProducts getWoolsProductByItem(String item);
}



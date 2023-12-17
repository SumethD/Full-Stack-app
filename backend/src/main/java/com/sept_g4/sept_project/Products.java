package com.sept_g4.sept_project;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="colesproducts")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item; // The name of the product
    private String price;
    private String img_url;

    @Column(columnDefinition = "TEXT")
    private String description; // The price of the product

    public Products() {
    }

    // Constructor for creating a product with name and price
    public Products(String name, String price) {
        this.item = name;
        this.price = price;
    }

    // Getter for retrieving the name of the product
    public String getName() {
        return item;
    }

    public String getImgUrl(){
        return img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setImgUrl(String img_url){
        this.img_url= img_url;
    }

    public void setDescription(String description) {
        this.description= description;
    }

    // Getter for retrieving the price of the product
    public String getPrice() {
        return price;
    }

    // Setter for setting the name of the product
    public void setName(String name) {
        this.item = name;
    }

    // Setter for setting the price of the product
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + item + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

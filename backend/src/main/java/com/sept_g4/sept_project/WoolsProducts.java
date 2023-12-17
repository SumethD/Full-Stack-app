package com.sept_g4.sept_project;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="woolsproducts")
public class WoolsProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private String price;
    private String img_url;

    @Column(columnDefinition = "TEXT")
    private String description;

    public WoolsProducts() {
    }

    public WoolsProducts(Long id, String item, String price) {
        this.id = id;
        this.item = item;
        this.price = price;
    }


    public Long getId() { // Getter method for id.
        return id;
    }

    public String getImgUrl(){ // Getter  methods for image url.
        return img_url;
    }

    public String getdescription() {  // Getter method for description.
        return description;
    }

    public void setId(Long id) {  // Setter method for id.
        this.id = id;
    }

    public void setImgUrl(String img_url){  // Setter methods for img url.
        this.img_url= img_url;
    }

    public void setDescription(String description) { // Setter methods for description
        this.description = description;
    }

    public String getItem() {  // Getter methods for item
        return item;
    }

    public void setItem(String item) {  // Setter methods for item
        this.item = item;
    }

    public String getPrice() {   // Getter methods for price 
        return price;
    }

    public void setPrice(String price) {  // Setter methods for price
        this.price = price;
    }

    @Override
    public String toString() {
        return "WoolsProducts{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}



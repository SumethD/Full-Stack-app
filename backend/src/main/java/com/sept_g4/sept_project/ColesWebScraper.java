package com.sept_g4.sept_project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;

@Component
public class ColesWebScraper {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void scrapeData() {
        // List of categories to scrape
        String[] categories = {
            "cooking-oil", "flours", "ghee", "lentils", "nuts-and-dried-fruit", "rice", "salt",
            "whole-spices", "ground-spices", "crushed-spices", "curry-paste", "curry-powder", "mixed-spices",
            "herbs", "coffee", "drinks", "juices", "tea", "chilli-sauce", "cooking-sauce", "flavouring-essence",
            "hot-sauce", "jam", "maxican-salsa", "mayonnaise", "chutney-and-pickle", "salad-dressings", "sambal",
            "sauces", "spreads", "syrups", "vinegar", "biscuits-cookies", "candies", "chips", "chocolates",
            "confectionery", "noodles", "snacks", "breakfast", "canned", "frozen"
        };
        

        for (String category : categories) {
            // Construct the URL for the category page
            String url = "https://www.indoasiangroceries.com.au/category/" + category;

            try {
                Document doc = Jsoup.connect(url).get();

                // Locate the elements containing product information
                Elements productItems = doc.select("div.product__items.product__items2");

                System.out.println("Scraping data from: " + url);

                for (Element item : productItems) {
                    // Extract the name, price, and image URL
                    String name = item.select("h3.product__items--content__title").text().trim();
                    String price = item.select("span.current__price").text().trim();
                    Element imageElement = item.select("img.product__items--img.product__primary--img").first();
                    String imageUrl = (imageElement != null) ? imageElement.attr("src") : "Image not found";

                    // Save the extracted data to the database
                    Products productEntity = new Products();
                    productEntity.setName(name);
                    productEntity.setPrice(price);
                    productEntity.setImgUrl(imageUrl);
                    productRepository.save(productEntity);

                    // Print or log the extracted data
                    System.out.println("Product: " + name);
                    System.out.println("Price: " + price);
                    System.out.println("Image URL: " + imageUrl);
                    System.out.println();
                }
            } catch (IOException e) {
                System.err.println("Failed to retrieve data from: " + url);
                e.printStackTrace();
            }
        }
    }
}


package com.sept_g4.sept_project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.IOException;

//@Component
public class OrmondFreshWebScraper {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void scrapeData() {
        // Base URL of the website
        String base_url = "https://ormondfresh.com.au/collections/all";
        String product_url = "https://ormondfresh.com.au/products/";

        // The number of pages to scrape
        int num_pages = 84;

        int item_count = 0;

        for (int page_number = 1; page_number <= num_pages; page_number++) {
            // Construct the URL for each page
            String url = base_url + "?page=" + page_number;

            try {
                // Send an HTTP GET request to the URL and parse the HTML content
                Document doc = Jsoup.connect(url).get();

                // Locate the elements containing product information
                Elements product_name_elements = doc.select("a.grid-view-item__link.grid-view-item__image-container.full-width-link");
                Elements product_price_elements = doc.select("span.price-item.price-item--regular");

                System.out.println("Scraping data from: " + url);

                for (int i = 0; i < product_name_elements.size(); i++) {
                    // Extract the name and price
                    String name = product_name_elements.get(i).select("span.visually-hidden").text().trim();
                    String price = product_price_elements.get(i).text().trim();

                    // Generate the correct product page URL with the hyphenated product name
                    String product_name_for_url = name.toLowerCase().replaceAll("[^a-zA-Z0-9-]+", "-");
                    String product_page_url = product_url + product_name_for_url;

                    try {
                        // Send an HTTP GET request to the product detail page and parse the HTML content
                        Document product_detail_doc = Jsoup.connect(product_page_url).get();
                        Element image_element = product_detail_doc.selectFirst("img");

                        // Extract the image URL
                        String image_url = (image_element != null) ? image_element.attr("src") : "Image not found";

                        // Save the extracted data to the database
                        Products productEntity = new Products();
                        productEntity.setName(name);
                        productEntity.setPrice(price);
                        productEntity.setImgUrl(image_url);
                        productRepository.save(productEntity);

                        // Increment the item count
                        item_count++;

                        // Print or log the extracted data
                        System.out.println("Product: " + name);
                        System.out.println("Price: " + price);
                        System.out.println("Image URL: " + image_url);
                        System.out.println();

                    } catch (IOException e) {
                        System.err.println("Failed to retrieve data from: " + product_page_url);
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to retrieve data from: " + url);
                e.printStackTrace();
                break;
            }
        }

        // Display the total number of successful items extracted
        System.out.println("Total successful items extracted: " + item_count);
    }
}

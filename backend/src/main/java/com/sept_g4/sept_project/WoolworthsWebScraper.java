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
public class WoolworthsWebScraper {

    @Autowired
    private WoolsProdRepository productRepository;

    @PostConstruct
    public void scrapeData() {
        // Base URL of the website
        String baseUrl = "https://sollys.com.au/collections/all";

        // The number of pages to scrape
        int numPages = 110;

        // Counter for the number of items scraped
        int itemCount = 0;

        for (int page = 1; page <= numPages; page++) {
            // Break the loop if the desired number of items is reached
            if (itemCount >= 1000) {
                break;
            }

            // Construct the URL for each page
            String url = baseUrl + "?page=" + page;

            try {
                // Send an HTTP GET request to the URL and parse the HTML content
                Document doc = Jsoup.connect(url).get();

                // Locate the elements containing product information
                Elements products = doc.select("div.product-block__title-price");

                System.out.println("Scraping data from: " + url);

                // Loop through the elements and extract the product details
                for (Element product : products) {
                    // Break the loop if the desired number of items is reached
                    if (itemCount >= 500) {
                        break;
                    }

                    Element nameElement = product.selectFirst("a.title");
                    String name = (nameElement != null) ? nameElement.text().trim() : "Name not found";

                    Element priceElement = product.selectFirst("span.money");
                    String price = (priceElement != null) ? priceElement.text().trim() : "Price not found";

                    // Extract the product link
                    Element linkElement = nameElement;
                    String productLink = (linkElement != null) ? linkElement.attr("href").trim() : "";

                    // Visit the product detail page
                    try {
                        Document productDetailPage = Jsoup.connect("https://sollys.com.au" + productLink).get();
                        Element descriptionElement = productDetailPage.selectFirst("div.product-description");
                        String description = (descriptionElement != null) ? descriptionElement.text().trim() : "Description not found";

                        // Extract the image URL from the <a> element
                        Element imageAElement = productDetailPage.selectFirst("div.product-media--image a.main-img-link--lightbox");
                        String imageUrl = (imageAElement != null) ? imageAElement.attr("href").trim() : "";

                        // Save the extracted data to the database
                        WoolsProducts productEntity = new WoolsProducts();
                        productEntity.setItem(name);
                        productEntity.setPrice(price);
                        productEntity.setDescription(description);
                        productEntity.setImgUrl(imageUrl); // Set the image URL
                        productRepository.save(productEntity);

                        // Increment the item count
                        itemCount++;

                        // Print or store the extracted data
                        System.out.println("Product: " + name);
                        System.out.println("Price: " + price);
                        System.out.println("Description: " + description);
                        System.out.println("Image URL: " + imageUrl);
                        System.out.println();
                    } catch (IOException e) {
                        System.err.println("Failed to retrieve data from: https://sollys.com.au" + productLink);
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to retrieve data from: " + url);
                e.printStackTrace();
            }
        }
    }
}

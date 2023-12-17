import React, { useState } from "react";
import { MagnifyingGlass } from 'phosphor-react';
import "./catalogue.css"; // Create a new CSS file for Catalogue styles

function Catalogue() {
  const [searchQuery, setSearchQuery] = useState("");

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value);
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    // Implement your search logic here
    // You can use the 'searchQuery' state for searching
    console.log("Search Query:", searchQuery);
  };

    // Product data (this is a dummy data plis replace actual product data)
    const products = [
        { name: "Product 1", description: "Description of Product 1", price: 20 },
        { name: "Product 2", description: "Description of Product 2", price: 30 },
        { name: "Product 3", description: "Description of Product 3", price: 40 },
        { name: "Product 4", description: "Description of Product 4", price: 30 },
        { name: "Product 5", description: "Description of Product 5", price: 50 },
        { name: "Product 6", description: "Description of Product 6", price: 70 },
    ];

  return (
    <div className="catalogue-container">
        <h1 className="catalogue-heading">Catalogue</h1>

        {/* Add space between heading and search/filter */}
        <div className="space"></div>

        <div className="catalogue-content">
            {/* Search Bar */}
            <div className="search-bar">
                <form onSubmit={handleSearchSubmit} style={{ display: "flex", alignItems: "center" }}>
                <input
                    type="text"
                    placeholder="Search for products"
                    className="search-input"
                    value={searchQuery}
                    onChange={handleSearchChange}
                />
                <button type="submit" className="search-button">
                    <MagnifyingGlass size={24} />
                </button>
                </form>
            </div>

            {/* Filter Sidebar and Product Grid Container */}
            <div className="filter-product-container">
                {/* Filter Sidebar */}
                <div className="filter-sidebar">
                    <h2>Filter</h2>
                    <ul>
                    <li>Baby</li>
                    <li>Bakery</li>
                    <li>Condiment</li>
                    <li>Cold</li>
                    <li>Dessert</li>
                    <li>Drinks</li>
                    </ul>
                </div>

                {/* Product Grid */}
                <div className="product-grid">
                    {/* Display products in a grid */}
                    {products.map((product, index) => (
                        <div key={index} className="product-item">
                            <h3>{product.name}</h3>
                            <p>Description: {product.description}</p>
                            <p className="product-price">${product.price}</p>
                            <button className="add-to-cart">Add to Cart</button>
                        </div>
                    ))}
                </div>
                </div>
      </div>
    </div>
  );
}

export default Catalogue;

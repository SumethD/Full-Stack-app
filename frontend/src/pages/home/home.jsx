import React, { useEffect, useState } from "react";
import { MagnifyingGlass } from "phosphor-react";
import "./home.css";
import { Link, Route, Routes } from "react-router-dom";
import axios from "axios";
import ProductDetails from "../productpage/Productdetails";
import { useWishlist } from "../WishlistContext";



function Home(props) {
  const [searchQuery, setSearchQuery] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [parsedProducts, setParsedProducts] = useState([]);
  const [featuredProducts, setFeaturedProducts] = useState([]);
  const { wishlist, setWishlist } = useWishlist();
  const userId = props.userId;


/**
 * Add a user wishlist attribute if the button clicks
 * */
  const addToWishlist = async (product) => {
    try {
        // Fetch the current wishlist data from the server
        const response = await axios.get(`http://localhost:8080/wishlist/${userId}`);
        const currentWishlistData = response.data;
        console.log("curr data", currentWishlistData);

        let updatedWishlistString = '';

        if (!currentWishlistData) {
            // If the wishlist is empty, set the new item as the wishlist
            updatedWishlistString = product.id.toString();
        } else if (typeof currentWishlistData === 'string' && currentWishlistData.includes('-')) {
            // If the wishlist contains multiple items, check if the item is already in the wishlist
            const wishlistItems = currentWishlistData.split('-');
            if (!wishlistItems.includes(product.id.toString())) {
                // If the item is not in the wishlist, add it
                updatedWishlistString = currentWishlistData + "-" + product.id.toString();
            } else {
                // If the item is already in the wishlist, show an alert and do not update
                alert('Item is already in the wishlist!');
                return;
            }
        } else {
            // If the wishlist contains a single item, check if it is the same item being added
            if (currentWishlistData !== product.id.toString()) {
                // If it's not the same item, add it to the wishlist
                updatedWishlistString = currentWishlistData + "-" + product.id.toString();
            } else {
                // If it's the same item, show an alert and do not update
                alert('Item is already in the wishlist!');
                return;
            }
        }
        console.log(updatedWishlistString);

        // Update the server with the updated wishlist string
        await axios.put(`http://localhost:8080/wishlist/${userId}`, updatedWishlistString, {
            headers: {
                'Content-Type': 'text/plain', // Set content type to plain text
            },
        });

        // Update the local state if needed (optional)
        setWishlist([...wishlist, product]);

        alert('Item added to wishlist!');
    } catch (error) {
        console.error('Error adding item to wishlist:', error);
    }
};


  
  
/**
 * show the Featured product when the page is mount first time.
 * */
  useEffect(() => {
    async function fetchData() {
      try {
        // Fetch all products from your API
        const response = await axios.get("http://localhost:8080/api/products");
        const products = response.data;

        // Map and set the products to parsedProducts
        const parsedProducts = products.map((product) => ({
          id: product.id,
          name: product.name,
          price: product.price,
          description: product.description,
          img_url: product.img_url,
        }));

        setParsedProducts(parsedProducts);

        // Set the featuredProducts to the first 4 items
        setFeaturedProducts(parsedProducts.slice(0, 4));

        console.log(parsedProducts);
      } catch (error) {
        console.error("Cannot get the result");
      }
    }

    fetchData();
  }, []);

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value);
  };

  /**
   * Search bar implement
   * */
  const handleSearchSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get(
        "http://localhost:8080/api/products/search?query=" + searchQuery
      );
      setSearchResults(response.data);
    } catch (error) {
      console.error("Error searching products:", error);
    }
  };

  return (
    <div>
      {/* Search Bar */}
      <div className="search-bar">
        <form
          onSubmit={handleSearchSubmit}
          style={{ display: "flex", alignItems: "center" }}
        >
          <input
            type="text"
            placeholder="Search for groceries"
            className="search-input"
            value={searchQuery}
            onChange={handleSearchChange}
          />
          <button type="submit" className="search-button">
            <MagnifyingGlass size={24} />
          </button>
        </form>
      </div>
      {/* Display Search Results */}
      <div className="search-results">
        <h2>Search Results</h2>
        <div className="product-container-2">
          {searchResults.map((product) => (
            <div className="product-card-2" key={product.id}>
              <h3>{product.name}</h3>
              <p className="product-price">{product.price}</p>
              <Link to={`/product/${product.id}/${searchQuery}`}>
                <button className="add-to-cart">Compare Item</button>
              </Link>
            </div>
          ))}
        </div>
      </div>

      {/* Navigation Bar */}
      <div className="navbar">
        
      </div>

      <div className="featured-product">
        <h2>Featured Products</h2>
        <div className="product-container">
          {featuredProducts.map((product) => (
            <div className="product-card" key={product.id}>
              <h3>{product.name}</h3>
              <p className="product-price">{product.price}</p>
              <img
                src={product.img_url}
                alt={product.name}
                className="product-image"
                onError={(e) => console.error("Image load error:", e)}
              />
              <p>{product.description}</p>
              <button className="add-to-cart">Add to Cart</button>
              <button className="compare-items" onClick={() => addToWishlist(product)}>Add to WishList</button>
            </div>
          ))}
        </div>
      </div>

      {/* Shop Online */}
      <div className="shop-online">
        <h2>Shop Online</h2>
        <div className="product-container-shop">
          {parsedProducts.slice(4, 9).map((product) => (
            <div className="product-card-shop" key={product.id}>
              <h3>{product.name}</h3>
              <img
                src={product.img_url}
                alt={product.name}
                className="product-image"
                onError={(e) => console.error("Image load error:", e)}
              />
              <p>{product.description}</p>
              <p className="product-price-shop">{product.price}</p>
              <button className="add-to-cart-shop">Add to Cart</button>
            </div>
          ))}
        </div>
      </div>
      <Routes>
        <Route
          path="/product/:id/:searchQuery"
          element={<ProductDetails />}
        />
      </Routes>
    </div>
  );
}
export default Home;
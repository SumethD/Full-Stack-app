import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import "./Productdetails.css"
import { useWishlist } from '../WishlistContext';

function ProductDetails(props) {
    const { id, searchQuery } = useParams();
    const [product, setProduct] = useState(null);
    const [searchResults, setSearchResults] = useState([]); // Initialize with an empty array
    const [sortBy, setSortBy] = useState("low-high");
    const { wishlist, setWishlist } = useWishlist();
    const userId = props.userId;


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
    
      


    useEffect(() => {
        // Fetch product details when the component mounts
        async function fetchProductDetails() {
            try {
                const response = await axios.get(`http://localhost:8080/api/products/${id}/${searchQuery}`);
                setProduct(response.data);
            } catch (error) {
                console.error('Error fetching product details:', error);
            }
        }

        fetchProductDetails();
    }, [id, searchQuery]);

    useEffect(() => {
        // Fetch search results when the component mounts and when the searchQuery parameter changes
        async function fetchSearchResults() {
            try {
                const response = await axios.get(`http://localhost:8080/api/woolsproducts/search?query=${searchQuery}`);
                setSearchResults(response.data);
            } catch (error) {
                console.error('Error fetching search results:', error);
            }
        }

        // Fetch search results when the component mounts
        fetchSearchResults();

    }, [searchQuery]); // Fetch results when searchQuery changes

    
    const handleSortChange = (e) => {
        const newSortBy = e.target.value;
        setSortBy(newSortBy);
    
        // Sort the searchResults based on the selected sorting criteria
        let sortedResults = [...searchResults]; // Create a copy of the array
    
        if (newSortBy === "low-high") {
            sortedResults = sortedResults.sort((a, b) => {
                // Remove any non-numeric characters and convert to numbers
                const priceA = parseFloat(a.price.replace(/[^0-9.]/g, ''));
                const priceB = parseFloat(b.price.replace(/[^0-9.]/g, ''));
                return priceA - priceB;
            });
        } else if (newSortBy === "high-low") {
            sortedResults = sortedResults.sort((a, b) => {
                // Remove any non-numeric characters and convert to numbers
                const priceA = parseFloat(a.price.replace(/[^0-9.]/g, ''));
                const priceB = parseFloat(b.price.replace(/[^0-9.]/g, ''));
                return priceB - priceA;
            });
        }
    
        // Update the state with the sorted results
        setSearchResults(sortedResults);
    };


    

    return (
        <div className="product-details-container">
            <div className="product-details">
                {product ? (
                    <div>
                        <h2>{product.name}</h2>
                        <img src={product.img_url} alt={product.name} className="product-image" />
                        <p className="product-description">{product.description}</p>
                        <p className="product-price">{product.price}</p>
                        <button className="add-to-cart-button">Add to Cart</button>
                        <button className="add-to-wishlist-button"onClick={() => addToWishlist(product)}>Add to Wishlist</button>
                        {/* Add more details here */}
                    </div>
                ) : (
                    <p>Loading product details...</p>
                )}
            </div>
            <div className="search-results">
                <h2>Similar Products</h2>
                <div className="sort-filter">
                    <label htmlFor="sortSelect">Sort by: </label>
                    <select id="sortSelect" onChange={handleSortChange} value={sortBy}>
                        <option value="low-high">Price: Low to High</option>
                        <option value="high-low">Price: High to Low</option>
                    </select>
                </div>
                <div className="scrollable-list">
                    {searchResults.map((result) => (
                        <div className="search-result-item" key={result.id}>
                            <h3>{result.item}</h3>
                            <p className="product-price">{result.price}</p>
                            {/* Display the product description here */}
                            <p className="product-description">{result.description}</p>
                            {/* Display the product image here */}
                            <img src={result.img_url} alt={result.item} className="product-image" />
                            {/* Add more details here */}
                        </div>
                    ))}
                </div>
            </div>

        </div>
    );
}

export default ProductDetails;

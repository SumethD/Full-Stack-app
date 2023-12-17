  import React, { useState, useEffect } from 'react';
  import './wishlist.css'; // Import CSS styling
  import axios from 'axios'; // Import axios for making HTTP requests
  import { useWishlist } from '../WishlistContext';

  function Wishlist(props) {
    const [wishlistItems, setWishlistItems] = useState([]);
    const userId = props.userId;
    const [items, setItems] = useState(); // Initialize items state as an empty array
    const [isLoading, setIsLoading] = useState(true); // Add a loading state
    const { wishlist, setWishlist } = useWishlist();

    useEffect(() => {
      // Reset wishlist when a new user logs in
      setWishlist([]);
      fetchWishlistItemsFromServer();
    }, [userId]);



    
    
    
    const fetchWishlistItemsFromServer = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/wishlist/${userId}`);
        const wishlistData = response.data;
    
        if (!wishlistData) {
          // Handle empty response
          setIsLoading(false);
          console.log('Wishlist is empty.');
          return;
        }
    
        let wishlistItems;
    
        if (typeof wishlistData === 'string' && wishlistData.includes('-')) {
          wishlistItems = wishlistData.split('-'); // Split the string into individual product IDs
        } else {
          wishlistItems = [wishlistData]; // Convert the single item to an array
        }
    
        wishlistItems = await Promise.all(
          wishlistItems.map(async (productId) => {
            const productResponse = await axios.get(`http://localhost:8080/api/products/${productId}`);
            return productResponse.data;
          })
        );
    
        // Compare data in the wishlist context with wishlistItems
        const newItems = wishlist.filter((wishlistItem) => (
          !wishlistItems.some((item) => item.id === wishlistItem.id)
        ));
    
        // Update wishlistItems with new items
        setWishlistItems([...wishlistItems, ...newItems]);
    
        setIsLoading(false); // Set loading to false when data is fetched
        console.log(wishlistItems);
      } catch (error) {
        console.error('Error fetching wishlist:', error);
        setIsLoading(false); // Set loading to false on error
      }
    };
    
    
    // Add items as a dependency to trigger the effect when items change
    


    const removeItem = async (itemId) => {
      // Filter out the item with the specified itemId from wishlistItems
      const newItems = wishlistItems.filter((item) => item.id !== itemId);
    
      try {
        // Send a request to update the server-side wishlist with the new items
        const itemIds = newItems.map((item) => item.id);
        const wishlistString = itemIds.join('-');
        await axios.put(`http://localhost:8080/wishlist/${userId}`, wishlistString, {
          headers: {
            'Content-Type': 'text/plain', // Set content type to plain text
          },
        });
    
        // Update both the local state and the wishlist context with the new items
        setWishlistItems(newItems);
        setWishlist(newItems); // Update the wishlist context here
    
        alert('Item removed from wishlist!');
      } catch (error) {
        console.error('Error removing item from wishlist:', error);
      }
    };
    
    return (
      <div>
        <h1>My Wishlist</h1>
        {isLoading ? (
          <p>Loading...</p>
        ) : (
          <div>
          <ul>
            {wishlistItems.map((item) => (
              <li key={item.id}>
                {item.name} (ID: {item.id}) <br />
                <button onClick={() => removeItem(item.id)}>Remove</button>
              </li>
            ))}
          </ul>
          </div>
        )}
      </div>
    );
  }

  export default Wishlist;

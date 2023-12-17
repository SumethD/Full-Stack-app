// Create a context in a separate file (e.g., WishlistContext.js)
import { createContext, useContext, useState } from "react";
// Create a new context called WishlistContext
const WishlistContext = createContext();
// Create a provider component called WishlistProvider
export const WishlistProvider = ({ children }) => {
  // Define a state variable for the wishlist and a function to update it
  const [wishlist, setWishlist] = useState([]);
  // Wrap the children components with the WishlistContext.Provider
  // and provide the wishlist state and setWishlist function as the context value
  return (
    <WishlistContext.Provider value={{ wishlist, setWishlist }}>
      {children}
    </WishlistContext.Provider>
  );
};
// Create a custom hook called useWishlist to conveniently access the context value
export const useWishlist = () => {
  return useContext(WishlistContext);
};

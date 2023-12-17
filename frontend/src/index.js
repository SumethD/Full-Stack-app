import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { WishlistProvider } from './pages/WishlistContext'; // Import the WishlistProvider

ReactDOM.render(
  <React.StrictMode>
      {/* Wrap the entire application with the WishlistProvider to use  wishlist, setWishlist as global variables*/}
    <WishlistProvider>
      <App />
    </WishlistProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

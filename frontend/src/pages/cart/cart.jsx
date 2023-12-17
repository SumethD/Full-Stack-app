import React, { useState } from "react";
import "./cart.css";

function Cart() {
  // Example product data with initial quantities
  const initialProducts = [
    { name: "Product Name 1", price: 10.99, quantity: 1 },
    { name: "Product Name 2", price: 8.99, quantity: 2 },
  ];

  // State to store the products and their quantities
  const [products, setProducts] = useState(initialProducts);

  // Function to update the quantity of a product
  const updateQuantity = (index, newQuantity) => {
    const updatedProducts = [...products];
    if (newQuantity >= 1) {
      updatedProducts[index].quantity = newQuantity;
      setProducts(updatedProducts);
    }
  };

  // Function to remove a product from the cart
  const removeProduct = (index) => {
    const updatedProducts = [...products];
    updatedProducts.splice(index, 1);
    setProducts(updatedProducts);
  };

  return (
    <div className="cart-container">
      <h1 className="cart-heading">Shopping Cart</h1>

      <div className="product-box">
        <p className="added-products">Products Added:</p>
        {/* Display individual product items here */}
        {products.map((product, index) => (
          <div key={index} className="product-item">
            <div className="product-info">
              <p>{product.name}</p>
              <div className="quantity-control">
                <button
                  className="quantity-button"
                  onClick={() => updateQuantity(index, product.quantity - 1)}
                >
                  -
                </button>
                <input
                  type="number"
                  className="quantity-input"
                  value={product.quantity}
                  onChange={(e) => updateQuantity(index, parseInt(e.target.value))}
                />
                <button
                  className="quantity-button"
                  onClick={() => updateQuantity(index, product.quantity + 1)}
                >
                  +
                </button>
              </div>
              <p>${product.price}</p>
            </div>
            <button className="remove-product" onClick={() => removeProduct(index)}>
              Remove
            </button>
          </div>
        ))}
      </div>

      <a href = "/checkout">
      <button className="checkout-button">Checkout</button>
      </a>
      
    </div>
  );
}

export default Cart;

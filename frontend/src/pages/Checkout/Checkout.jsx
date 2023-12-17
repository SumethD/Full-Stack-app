import React, { useState } from 'react';
import './checkout.css';

function Checkout() {
  // Hardcoded cart data
  const cartItems = [
    { name: 'Product Name 1', price: 10.99, quantity: 2 },
    { name: 'Product Name 2', price: 19.99, quantity: 1 },
    { name: 'Product Name 3', price: 5.99, quantity: 3 },
    { name: 'Product Name 4', price: 15.99, quantity: 2 },
    { name: 'Product Name 5', price: 12.99, quantity: 1 },
  ];

  // Define state for form fields and validation errors
  const [formData, setFormData] = useState({
    name: '',
    address: '',
    city: '',
    state: '',
    postcode: '',
    email: '',
    cardNumber: '',
    expiration: '',
    cvv: '',
  });

  // Define validation rules and error messages for shipping information
  const shippingValidationRules = {
    name: {
      required: true,
      message: 'Name cannot be empty',
    },
    address: {
      required: true,
      message: 'Address cannot be empty',
    },
    city: {
      required: true,
      message: 'City cannot be empty',
    },
    state: {
      required: true,
      message: 'State cannot be empty',
    },
    postcode: {
      required: true,
      message: 'Postcode cannot be empty',
      numeric: true,
      pattern: /^\d+$/,
    },
    email: {
      required: true,
      message: 'Email cannot be empty',
    },
  };

  // Define validation rules and error messages for payment information
  const paymentValidationRules = {
    cardNumber: {
      required: true,
      message: 'Card number must be 16 digits',
      pattern: /^\d{16}$/,
    },
    expiration: {
      required: true,
      message: 'Expiration needs to be in the format month/year',
      pattern: /^(0[1-9]|1[0-2])\/\d{2}$/,
    },
    cvv: {
      required: true,
      message: 'CVV cannot be empty',
      pattern: /^\d{3}$/,
    },
  };

  const [shippingErrors, setShippingErrors] = useState({});
  const [paymentErrors, setPaymentErrors] = useState({});

  // Function to handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();

    // Validate shipping information
    let isShippingValid = true;
    const newShippingErrors = {};

    for (const fieldName in shippingValidationRules) {
      const rule = shippingValidationRules[fieldName];
      const value = formData[fieldName];

      if (rule.required && value.trim() === '') {
        isShippingValid = false;
        newShippingErrors[fieldName] = rule.message;
      } else if (rule.numeric && isNaN(value)) {
        isShippingValid = false;
        newShippingErrors[fieldName] = 'The postcode needs to be a number';
      } else if (rule.pattern && !rule.pattern.test(value)) {
        isShippingValid = false;
        newShippingErrors[fieldName] = rule.message;
      } else {
        newShippingErrors[fieldName] = '';
      }
    }

    setShippingErrors(newShippingErrors);

    // Validate payment information
    let isPaymentValid = true;
    const newPaymentErrors = {};

    for (const fieldName in paymentValidationRules) {
      const rule = paymentValidationRules[fieldName];
      const value = formData[fieldName];

      if (rule.required && value.trim() === '') {
        isPaymentValid = false;
        newPaymentErrors[fieldName] = rule.message;
      } else if (fieldName === 'cvv' && (value.length !== 3 || isNaN(value))) { // Validate CVV separately
        isPaymentValid = false;
        newPaymentErrors[fieldName] = 'CVV must be a 3-digit number';
      } else if (rule.pattern && !rule.pattern.test(value)) {
        isPaymentValid = false;
        newPaymentErrors[fieldName] = rule.message;
      } else {
        newPaymentErrors[fieldName] = '';
      }
    }

    setPaymentErrors(newPaymentErrors);

    // If both shipping and payment information are valid, handle form submission
    if (isShippingValid && isPaymentValid) {
      alert('Form submitted successfully');
    }
  };

  // Function to handle input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  return (
    <div className="checkout-container">
      <h1 className="checkout-heading">Checkout</h1>

      <form onSubmit={handleSubmit}>
        <div className="checkout-content">
          {/* Cart Details */}
          <div className="form-section cart-details">
            <legend>Cart Details</legend>
            <ul>
              {cartItems.map((item, index) => (
                <li key={index}>
                  {item.name} - ${item.price.toFixed(2)} x {item.quantity}
                </li>
              ))}
            </ul>
          </div>

          {/* Shipping Information */}
          <div className="form-section shipping-info">
            <legend>Shipping Information</legend>
            {Object.keys(shippingValidationRules).map((fieldName) => (
              <div className="form-group" key={fieldName}>
                <label htmlFor={fieldName}>
                  {fieldName.charAt(0).toUpperCase() + fieldName.slice(1)}:
                </label>
                <input
                  type="text"
                  id={fieldName}
                  name={fieldName}
                  value={formData[fieldName]}
                  onChange={handleInputChange}
                />
                <div className="error">{shippingErrors[fieldName]}</div>
              </div>
            ))}
          </div>

          {/* Payment Information */}
          <div className="form-section payment-info">
            <legend>Payment Information</legend>
            {Object.keys(paymentValidationRules).map((fieldName) => (
              <div className="form-group" key={fieldName}>
                <label htmlFor={fieldName}>
                  {fieldName.charAt(0).toUpperCase() + fieldName.slice(1)}:
                </label>
                <input
                  type={fieldName === 'cardNumber' ? 'text' : 'number'}
                  id={fieldName}
                  name={fieldName}
                  value={formData[fieldName]}
                  onChange={handleInputChange}
                />
                <div className="error">{paymentErrors[fieldName]}</div>
              </div>
            ))}
          </div>
        </div>

        <button className="submit-button" type="submit">
          Submit
        </button>
      </form>
    </div>
  );
}

export default Checkout;

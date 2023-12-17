import React, { useState } from 'react';
import './delivery.css'; // Import the CSS file for styling

// Create a function component named DeliveryPage
function DeliveryPage() {
  // Define state variable trackingNumber using useState
  const [trackingNumber] = useState('');

  // Return the JSX content for DeliveryPage
  return (
    <div className="delivery-page">
      <h1>Delivery Details</h1>
      <div className="details-container">

        <div className="detail">
          <label htmlFor="trackingNumber">Tracking Number:</label>
          <input
            type="text"
            id="trackingNumber"
            value={trackingNumber}
          />
          <button>
            Check
          </button>
        </div>

      </div>
      <div className="summary">
        <h2>Delivery Summary</h2>
        <p>Customer Name:</p>
        <p>Order Number:</p>
        <p>Order Status:</p>
      </div>
    </div>
  );
}

export default DeliveryPage;

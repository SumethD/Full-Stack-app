import React from 'react';

//Creating funciton
function WishlistItem({ item, onRemove }) {
  return (
    <li>
      {item}
      <button onClick={onRemove}>Remove</button>
    </li>
  );
}

export default WishlistItem;

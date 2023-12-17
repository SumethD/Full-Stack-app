import React, {useState} from "react";
import { Link, useLocation } from "react-router-dom";
import logo from "../components/logopicture/logosuper.jpg";
import "./navbar.css";
import Home from "../pages/home/home";
// Import the cart logo image
import cartLogo from "../pages/picture/cartlogo.png"

export const Navbar = (props) => {
  // Get the current location using useLocation from React Router
  const location = useLocation();

  // Define an array of routes where you want to hide the Home component
  const routesToHideHome = ["/login", "/register", "/delivery", "/wishlist", "/home", "/cart","/checkout"];

  // Check if the current location matches any of the routes to hide Home
  const hideHome = routesToHideHome.includes(location.pathname) || location.pathname.startsWith("/product/");
  const [user_loggedIn, setUser_LoggedIn] = useState(props.loggedIn);

  const handLogout = () => {
    setUser_LoggedIn(false);
    props.is_LoggedIn(user_loggedIn);
  }
  return (
    <div>
      <main className="main-content">
        <div className="header">
          {/* Logo */}
          <Link to="/home">
            <img
              src={logo}
              alt="SuperPrice Logo"
              className="logo"
              style={{ width: "70px", height: "70px" }}
            />
          </Link>
          {/* Centered SuperPrice Heading */}
          <h1>
            <Link className="header1" to="/home">
              SuperPrice
            </Link>
          </h1>
        </div>
        {/* Shopping Cart */}


        {/* Login/Register Buttons */}
        <div className="buttons">

          {props.loggedIn ? (
              //when user logged In cart, Track Delivery, wishlist button displayed.
              <>
              <Link to="/cart">
                <div className="cart">
                  <img
                      src={cartLogo}
                      alt="Cart Logo"
                      width={24}
                      height={24}
                  />
                </div>
              </Link>
              <Link to="/delivery">Track Delivery</Link>
              <Link to="/wishlist">Wishlist</Link>
              //Logout button
              <Link to="/login" onClick={handLogout}>Logout</Link>
              </>
          ) : (
                  // Display Login and Register links if not logged in
                  <>
                    <Link to="/login">Login</Link>
                    <Link to="/register">Register</Link>
                  </>
              )}
        </div>
      </main>
      {/* Conditionally render the Home component based on the route */}
      {!hideHome && <Home />}
    </div>
  );
};

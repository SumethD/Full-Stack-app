
import './App.css';
import {useEffect, useState} from "react";
import {BrowserRouter as Router,Routes, Route} from 'react-router-dom';
import { Navbar } from './components/navbar';
import Login from './pages/login/login';
import Register from './pages/register/register';
import Home from './pages/home/home';
import Cart from './pages/cart/cart.jsx';
import Catalogue from './pages/catalogue/catalogue.jsx';
import Wishlist from './pages/wishlist/wishlist.jsx';
import Checkout from './pages/Checkout/Checkout';
import Delivery from './pages/Delivery/delivery';
import ProductDetails from './pages/productpage/Productdetails';


function App() {
  const [user, setUser] = useState(null);
  const [loggedIn, setLoggedIn] = useState(null);



    const getUserId = (user) => {
      return user ? user.id : null;
    };

    const userId = (user) => {
      return user.id;
    }

    const updateUser = (userInfo) => {
    setUser(userInfo);
    };

    const is_LoggedIn = (user_LoggedIn) =>{
      setLoggedIn(user_LoggedIn);
    }

    useEffect(() =>{
      if(user !== null){
        setLoggedIn(true);
      }
      console.log(loggedIn);
    },[user]);

    useEffect(() => {
      if(loggedIn === null){
        setUser(null);
      }
    }, [loggedIn])

  return (
    <div className="App">
      <Router>
        <Navbar user={user} loggedIn= {loggedIn} is_LoggedIn={is_LoggedIn}/>
        <Routes>
          <Route path="/home"  element={<Home userId={getUserId(user)} user={user}/>}/>
          <Route path="/login" element={<Login user={user} updateUser={updateUser} />}/>
          <Route path="/register" element={<Register userId={getUserId(user)}/>}/>
          <Route path="/cart" element={<Cart/>}/>
          <Route path="/help"/> 
          <Route path="/wishlist" element={<Wishlist userId={getUserId(user)}/>}/>
          <Route path="/product/:id/:searchQuery" element={<ProductDetails userId={getUserId(user)}/>} />
          <Route path="/checkout" element={<Checkout/>}/>
          <Route path="/delivery" element={<Delivery/>}/>

          
        </Routes>
      </Router>
    </div>
  );
}

export default App;

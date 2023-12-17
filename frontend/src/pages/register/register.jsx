import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./register.css";
import axios from "axios";

export default function Register() {
  let navigate = useNavigate();

  const [user, setUser] = useState({
    email: "",
    password: "",
    firstname: "",
    surname: "",
    contact: "",
    dob: "",
  });

  const [errors, setErrors] = useState({
    email: "",
    password: "",
    firstname: "",
    surname: "",
    contact: "",
    dob: "",
  });

  const { email, password, firstname, surname, contact, dob } = user;

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });

    if (name === "dob") {
      // Format the date in "YYYY-MM-DD" format
      const formattedDate = formatDate(value);
  
      setUser({ ...user, [name]: formattedDate });
    } else {
      // For other fields, simply set the value in state
      setUser({ ...user, [name]: value });
    }

    // Validate the field and set errors
    const error = validateField(name, value);
    setErrors({ ...errors, [name]: error });
  };

  const formatDate = (inputDate) => {
    const parts = inputDate.split('/');
    if (parts.length === 3) {
      const [month, day, year] = parts;
      // Ensure each part is two digits (e.g., "04" instead of "4")
      const formattedMonth = month.padStart(2, '0');
      const formattedDay = day.padStart(2, '0');
      return `${year}-${formattedMonth}-${formattedDay}`;
    }
    return inputDate; // Return the input unchanged if it doesn't match the expected format
  };
  

  const validateField = (fieldName, value) => {
    if (value.trim() === "") {
      return `${fieldName} cannot be empty`;
    }

    if (fieldName === "email") {
      const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
      if (!emailRegex.test(value)) {
        return "Invalid email address";
      }
    }

    if (fieldName === "password") {
      const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;
      if (!passwordRegex.test(value)) {
        return "Password must be at least 8 characters and include at least one uppercase letter, one lowercase letter, one digit, and one special character";
      }
    }

    if (fieldName === "contact") {
      if (!/^\d+$/.test(value)) {
        return "Contact Number must contain only digits";
      }

      if (value.length < 10) {
        return "Contact Number must be at least 10 digits long";
      }
    }

    if (fieldName === "dob") {
      const currentDate = new Date();
      const enteredDate = new Date(value);
      const ageDifference = currentDate.getFullYear() - enteredDate.getFullYear();

      if (ageDifference < 18 || ageDifference > 150) {
        return "Date of Birth must be between 18 and 150 years ago";
      }
    }

    return "";
  };

  const onSubmit = async (e) => {
    e.preventDefault();

    // Check for errors before submitting
    for (const field in user) {
      const error = validateField(field, user[field]);
      if (error) {
        setErrors({ ...errors, [field]: error });
        return;
      }
    }

    try {
      // Send registration data to the server
      await axios.post("http://localhost:8080/user", user);
      navigate("/login");
    } catch (error) {
      // Handle registration error
      console.error("Registration failed:", error);
    }
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      <form onSubmit={(e) => onSubmit(e)}>
        <div className="register-section">
          <div className="form-section">
            <h3>Email</h3>
            <input
              type="email"
              name="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => onInputChange(e)}
            />
            <div className="error">{errors.email}</div>
          </div>
          <div className="form-section">
            <h3>Password</h3>
            <input
              type="password"
              name="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => onInputChange(e)}
            />
            <div className="error">{errors.password}</div>
          </div>
        </div>
        <div className="register-section">
          <h2>Personal Details</h2>
          <div className="form-section">
            <h3>First Name</h3>
            <input
              type="text"
              name="firstname"
              placeholder="Enter your first name"
              value={firstname}
              onChange={(e) => onInputChange(e)}
            />
            <div className="error">{errors.firstname}</div>
          </div>
          <div className="form-section">
            <h3>Surname</h3>
            <input
              type="text"
              name="surname"
              placeholder="Enter your surname"
              value={surname}
              onChange={(e) => onInputChange(e)}
            />
            <div className="error">{errors.surname}</div>
          </div>
          <div className="form-section">
            <h3>Contact Number</h3>
            <input
              type="text"
              name="contact"
              placeholder="Enter your contact number"
              value={contact}
              onChange={(e) => onInputChange(e)}
            />
            <div className="error">{errors.contact}</div>
          </div>
          <div className="form-section">
            <h3>Date of Birth</h3>
            <input
              type="date"
              name="dob"
              placeholder="Select your date of birth"
              value={dob}
              onChange={(e) => onInputChange(e)}
            />
            <div className="error">{errors.dob}</div>
          </div>
        </div>
        <div className="button-container">
          <button className="submitbtn" type="submit">
            Submit
          </button>
          <button className="cancelbtn">
            <Link to="/login">Cancel</Link>
          </button>
        </div>
      </form>
    </div>
  );
}

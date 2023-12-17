import React, {useState} from "react";
import { Link, useNavigate } from "react-router-dom";
import "./login.css";
import axios from "axios";

const Login = (props) => {
  const navigate = useNavigate();
  const [loggedUser, setLoggedUser] = useState({
    email: "",
    password: "",
  });
  const [user, setUser] = useState(props.user)

  const [error, setError] = useState(""); // To store and display error messages

  const { email, password } = loggedUser;

  const onInputChange = (e) => {
    setLoggedUser({ ...loggedUser, [e.target.name]: e.target.value });
  };


  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      await axios.post("http://localhost:8080/login",{
        "email": email, "password" : password
      }).then(async (res) =>
      {

        if (res.data.message === "Email not exits")
        {
          setError("Email not exits");
        }
        else if(res.data.message ==="Login Success")
        {
          const userId = await updateUser();
          console.log("userId:", userId);
          
          navigate("/home", { state: { userId: userId } });
        }
        else
        {
          setError("Incorrect Email and Password not match");
        }
      }, fail => {
        console.error(fail); // Error!
      });
    }

    catch (err) {
      alert(err);
    }

  }
  const updateUser = async () => {
    try {
      // Make a GET request to your Spring Boot getUser endpoint with the user's email as a path variable
      const response = await axios.get(`http://localhost:8080/login/${email}`, {
        params: {
          "password": password,
          "email": email,
        },
      });

      // Assuming the response contains user data, you can access it as follows
      const userData = response.data;
      console.log(userData);
      // Now you can use userData as needed
      console.log("User Data:", userData);

      // Update the user state with the received user data
      setUser(userData);

      // You can also pass it to props.updateUser if necessary
      props.updateUser(userData);
      return userData.id;
    } catch (error) {
      console.error("GET request (user info) error:", error);
      // Handle the error, e.g., show an error message to the user
    }
  };



  return (
      <div>
        <div className="loginbody">
          <h2 className="loginhead">Login</h2>
          <form onSubmit={handleLogin}> {/* Use onSubmit for form submission */}
            <div className="box">
              <div>
                <label>Email:</label>
              </div>
              <div>
                <input
                    type="email"
                    name="email"
                    placeholder="Enter your email"
                    value={email}
                    onChange={(e) => onInputChange(e)}
                />
              </div>
              <div>
                <label>Password:</label>
              </div>
              <div>
                <input
                    type="password"
                    name="password"
                    placeholder="Enter your password"
                    value={password}
                    onChange={(e) => onInputChange(e)}
                />
              </div>
            </div>
            <div>
              <button
                  className="loginbutton-container"
                  type="submit" // Use type="submit" for form submission
              >
                Login
              </button>
            </div>
          </form>
          <p>
            {error && <div className="error-message">{error}</div>}
            Don't have an account?{" "}
            <Link className="regname" to="/register">
              Register
            </Link>
          </p>
        </div>
      </div>
  );
};



export default Login;

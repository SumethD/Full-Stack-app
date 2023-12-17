package com.sept_g4.sept_project;

public class LoginMessage {
    public String message;
    public boolean loggedIn;

    //Login Message DTO
    //Check Login is success or not
    public LoginMessage(String message, boolean loggedIn) {
        this.message = message;
        this.loggedIn = loggedIn;
    }
    public LoginMessage() {
    }

    public String getMessage() {
        return message;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "message='" + message + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}

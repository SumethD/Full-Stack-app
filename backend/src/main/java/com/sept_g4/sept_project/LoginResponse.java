package com.sept_g4.sept_project;

public class LoginResponse {

    String message;
    Boolean status;


     // Default constructor.
    public LoginResponse() {
    }

     // Parameterized constructor that initializes message and status.
    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    // Getter method for retrieving the message.
    public String getMessage() {
        return message;
    }

    // Getter method for retrieving the status.
    public Boolean getStatus() {
        return status;
    }

    // Setter method for setting the message.
    public void setMessage(String message) {
        this.message = message;
    }

    // Setter method for setting the status.
    public void setStatus(Boolean status) {
        this.status = status;
    }


    // Override the toString() method to provide a string representation of the LoginResponse object.
    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}

package com.sept_g4.sept_project;

public class LoginDTO {
    private String email;
    private String password;

    // contsturct for the Login DTO
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    // Empty contructor
    public LoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

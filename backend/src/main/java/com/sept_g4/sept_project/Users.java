package com.sept_g4.sept_project;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String surname;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private String email;
    private String contact;
    private String password;
    private String wishlist;


    public Users() {
    }

    public Users(Long id, String firstname, String surname, Date dob, String email, String contact, String password) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.dob = dob; // Initialize the dob field correctly
        this.email = email;
        this.contact = contact;
        this.password = password;
    }


    // Getter and setter methods for password.
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and setter methods for wishlist.
    public String getWishlist() {
        return wishlist;
    }


    // Getter and setter methods for email.
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

    // Getter and setter methods for id.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and setter methods for first name.
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    // Getter and setter methods for sur name.
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter and setter methods for dob.
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    // Getter and setter methods for contact.
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}



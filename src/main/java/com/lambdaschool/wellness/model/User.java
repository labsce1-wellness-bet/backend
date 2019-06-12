package com.lambdaschool.wellness.model;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fname;

    private String lname;
    private String fullname;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;

    public User() {
        // default constructor
    }

    public long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    // public void setFname(String fname) {
    // this.fname = fname;
    // }

    public String getLname() {
        return lname;
    }

    // public void setLname(String lname) {
    // this.lname = lname;
    // }

    public String getFullname() {
        return this.fullname = lname + " " + fname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
}

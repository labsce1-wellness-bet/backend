package com.lambdaschool.wellness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // @JsonIgnore
    private String fname;

    // @JsonIgnore
    private String lname;

    private String fullname;

    private String email;

    public User() {
        // default constructor
    }

    public long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFullname() {
        return "Caleb Kirkwood";
    }

    public void setFullname(String fname, String lname) {
        this.fullname = fname + ' ' + lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

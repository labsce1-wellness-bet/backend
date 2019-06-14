package com.lambdaschool.wellness.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private long userid;

    private String fname;

    private String lname;
    private String fullname;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_group_Comp", joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "groupid") )
    private List<Group> groups = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_group_Comp", joinColumns = @JoinColumn(name = "userid"),
    inverseJoinColumns = @JoinColumn(name = "compid"))
    private List<Competition> competitions = new ArrayList<>();

    public User()
    {
        //default constructor
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public List<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(List<Group> groups)
    {
        this.groups = groups;
    }



    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
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
        return this.fullname = fname + " " + lname;
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

}

package com.lambdaschool.wellness.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    private String fname;

    private String lname;
    private String fullname;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    //many to many

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Competition> competition = new ArrayList<>();


    public User()
    {
        //default constructor
    }

    public List<Competition> getCompetition()
    {
        return competition;
    }

    public void setCompetition(List<Competition> competition)
    {
        this.competition = competition;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
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

    public void setEmail(String email)
    {
        this.email = email;
    }

}



package com.lambdaschool.wellness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")

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

    @ManyToMany
    @JoinTable(name = "user_group_competition", joinColumns = {
            @JoinColumn(name = "userid", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "groupid") })
    private Set<Group> group = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_group_competition", joinColumns = {
            @JoinColumn(name = "userid", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "compid") })
    private Set<Competition> competition = new HashSet<>();
    // @JsonIgnore
    private String fitbitRefresh;
    // @JsonIgnore
    private String fitbitAccess;
    // many to many

    public User() {
        // default constructor
    }

    public Set<Group> getGroup() {
        return group;
    }

    public void setGroup(Set<Group> group) {
        this.group = group;
    }

    public Set<Competition> getCompetition() {
        return competition;
    }

    public void setCompetition(Set<Competition> competition) {
        this.competition = competition;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFitbitAccess() {
        return fitbitAccess;
    }

    public void setFitbitAccess(String fitbitAccess) {
        this.fitbitAccess = fitbitAccess;
    }

    public String getFitbitRefresh() {
        return fitbitRefresh;
    }

    public void setFitbitRefresh(String fitbitRefresh) {
        this.fitbitRefresh = fitbitRefresh;
    }

}

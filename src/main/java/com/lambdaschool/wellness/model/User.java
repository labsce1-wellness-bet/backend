package com.lambdaschool.wellness.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;
    private String auth0id;

    @ManyToMany
    @JoinTable(name="user_group",
    joinColumns = {@JoinColumn(name="userid")},
    inverseJoinColumns = {@JoinColumn(name="groupid")})
    private Set<Group> group;
    public User() {

    }

    public User(String auth0id, Set<Group> group) {
        this.auth0id = auth0id;
        this.group = group;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getAuth0id() {
        return auth0id;
    }

    public void setAuth0id(String auth0id) {
        this.auth0id = auth0id;
    }

    public Set<Group> getGroup() {
        return group;
    }

    public void setGroup(Set<Group> group) {
        this.group = group;
    }
}

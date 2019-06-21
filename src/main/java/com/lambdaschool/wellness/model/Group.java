package com.lambdaschool.wellness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "wellness_groups")
@Data
public class Group
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long groupid;

    @Column(unique=true)
    private String group_name;
    //Todo:change it to string min 6 hours
    private String invite_code;

    private String adminid;



    @ManyToMany
    @JsonIgnore
    @JoinTable(name="user_group",
            joinColumns = {@JoinColumn(name="groupid")},
            inverseJoinColumns = {@JoinColumn(name="userid")})
    private Set<User> users;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Competition> competitions;

    public Group() {

    }

    public Group(String group_name, String invite_code, String adminid, User... users) {
        this.group_name = group_name;
        this.invite_code = invite_code;
        this.adminid = adminid;
        this.users = Stream.of(users).collect(Collectors.toSet());
        this.users.forEach(x -> x.getGroups().add(this));
    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

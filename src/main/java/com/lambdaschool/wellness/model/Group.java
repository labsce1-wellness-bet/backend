package com.lambdaschool.wellness.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "group")
    private Set<User> users = new HashSet<>();

    public Group() {

    }
    public Group(String group_name, String invite_code, String adminid, Set<User> users) {
        this.group_name = group_name;
        this.invite_code = invite_code;
        this.adminid = adminid;
        this.users = users;
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

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code)
        {
            this.invite_code = invite_code;
        }


}

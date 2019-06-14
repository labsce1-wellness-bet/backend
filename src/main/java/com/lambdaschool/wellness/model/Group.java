package com.lambdaschool.wellness.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "groups")
@Data

public class Group
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long groupid;
    private String group_name;
    private int goal;
    private String admin;
    private String invite_code;

    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<>();

    public Group()
    {
        //default constructor
    }

    public Group(String group_name, int goal, String admin, int bet_amount, String invite_code, String start_date, String end_date, String message, Group competition)
    {
        this.group_name = group_name;
        this.goal = goal;
        this.admin = admin;
        this.invite_code = invite_code;

    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public long getGroupid()
    {
        return groupid;
    }

    public void setGroupid(long groupid)
    {
        this.groupid = groupid;
    }

    public String getGroup_name()
    {
        return group_name;
    }

    public void setGroup_name(String group_name)
    {
        this.group_name = group_name;
    }
    public int getGoal()
    {
        return goal;
    }

    public void setGoal(int goal)
    {
        this.goal = goal;
    }

    public String getAdmin()
    {
        return admin;
    }

    public void setAdmin(String admin)
    {
        this.admin = admin;
    }

    public String getInvite_code()
    {
        int length =7;
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++)
        {
            builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));



        }
        return builder.toString();




    }

    public void setInvite_code(String invite_code)
    {
        this.invite_code = invite_code;
    }


}
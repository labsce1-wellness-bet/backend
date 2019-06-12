package com.lambdaschool.wellness.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "competitions")
@Data
public class Competition
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupid;

    private String group_name;

    private String activity;

    private int goal;
    private String admin;
    private int bet_amount;
    private String invite_code;
    private String start_date;
    private String end_date;
    private String message;
    //Todo:ManytoMany
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties({"competitions", "hibernateLazyInitializer"})
    private User user;

    public Competition()
    {
        //default constructor
    }

    public Competition(String group_name, String activity, int goal, String admin, int bet_amount, String invite_code, String start_date, String end_date, String message, Competition competition)
    {
        this.group_name = group_name;
        this.activity = activity;
        this.goal = goal;
        this.admin = admin;
        this.bet_amount = bet_amount;
        this.invite_code = invite_code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.message = message;
        this.user= user;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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

    public String getActivity()
    {
        return activity;
    }

    public void setActivity(String activity)
    {
        this.activity = activity;
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

    public int getBet_amount()
    {
        return bet_amount;
    }

    public void setBet_amount(int bet_amount)
    {
        this.bet_amount = bet_amount;
    }

    public String getInvite_code()
    {
        return invite_code;
    }

    public void setInvite_code(String invite_code)
    {
        this.invite_code = invite_code;
    }

    public String getStart_date()
    {
        return start_date;
    }

    public void setStart_date(String start_date)
    {
        this.start_date = start_date;
    }

    public String getEnd_date()
    {
        return end_date;
    }

    public void setEnd_date(String end_date)
    {
        this.end_date = end_date;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }


}
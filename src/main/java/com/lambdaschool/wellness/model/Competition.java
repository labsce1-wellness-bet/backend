package com.lambdaschool.wellness.model;

import javax.persistence.*;

@Entity
@Table(name = "Competition")
public class Competition
{
//    ID | int
//    Group_name | string
//    Type | string (ex. Sleeping)
//    Goal | int
//    Goal_metric | string (ex. Hours, minutes)
//    Admin | int | User ID foreign key
//    Bet_amount | int
//    join_code | string
//    Start_date | date
//    End_date | date
//    Message | string


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String group_name;

    private String activity;

    private int goal;
    private String admin;
    private int bet_amount;
    private String invite_code;
    private String start_date;
    private String end_date;
    private String message;

    public Competition()
    {
        //default constructor
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
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

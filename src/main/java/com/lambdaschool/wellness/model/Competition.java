package com.lambdaschool.wellness.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "competitions")
public class Competition
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long compid;
    private String competitionType;
    private double betAmount;
    private String message;
    private String startDate;
    private String endDate;

    @ManyToMany(mappedBy = "competition")
    private Set<User>  users = new HashSet<User>();


    public Competition()
    {
        //
    }

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    public String getCompetitionType()
    {
        return competitionType;
    }

    public void setCompetitionType(String competitionType)
    {
        this.competitionType = competitionType;
    }

    public long getCompid()
    {
        return compid;
    }

    public void setCompid(long compid)
    {
        this.compid = compid;
    }

    public double getBetAmount()
    {
        return betAmount;
    }

    public void setBetAmount(double betAmount)
    {
        this.betAmount = betAmount;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

}

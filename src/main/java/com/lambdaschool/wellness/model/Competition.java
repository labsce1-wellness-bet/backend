package com.lambdaschool.wellness.model;

import lombok.Data;

import javax.persistence.*;

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

    public Competition()
    {
        //
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

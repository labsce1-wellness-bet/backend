package com.lambdaschool.wellness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lambdaschool.wellness.lib.enums.CompetitionStatus;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "competitions")
public class Competition
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long compId;
    private String competitionType;
    private double betAmount;
    private String message;
    private Date startDate;
    private Date endDate;
    private String winnerId;
    private CompetitionStatus competitionStatus;


    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "groupId",  nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Group group;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private Set<Competitor> competitors;

    public Competition()
    {
        //
    }


    public Competition(String competitionType, double betAmount, String message, Date startDate, Date endDate, String winnerId, CompetitionStatus competitionStatus, Group group, Set<Competitor> competitors) {
        this.competitionType = competitionType;
        this.betAmount = betAmount;
        this.message = message;
        this.startDate = startDate;
        this.endDate = endDate;
        this.winnerId = winnerId;
        this.competitionStatus = competitionStatus;
        this.group = group;
        this.competitors = competitors;
    }

    public Set<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Set<Competitor> competitors) {
        this.competitors = competitors;
    }

    public CompetitionStatus getCompetitionStatus() {
        return competitionStatus;
    }

    public void setCompetitionStatus(CompetitionStatus competitionStatus) {
        this.competitionStatus = competitionStatus;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public void setCompetitionType(String competitionType) {
        this.competitionType = competitionType;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCompetitionType() {
        return competitionType;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

package com.lambdaschool.wellness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@Table(name = "competitors")
public class Competitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "compId",  nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Competition competition;

    private String auth0Id;

    private boolean isApproved;

    private boolean hasUploadedReceipt;

    public Competitor() {

    }

    public Competitor(Competition competition, String auth0Id, boolean isApproved, boolean hasUploadedReceipt) {
        this.competition = competition;
        this.auth0Id = auth0Id;
        this.isApproved = isApproved;
        this.hasUploadedReceipt = hasUploadedReceipt;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isHasUploadedReceipt() {
        return hasUploadedReceipt;
    }

    public void setHasUploadedReceipt(boolean hasUploadedReceipt) {
        this.hasUploadedReceipt = hasUploadedReceipt;
    }
}

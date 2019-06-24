package com.lambdaschool.wellness.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "wellness_groups")
@Data
public class Group
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long groupId;

    @Column(unique=true)
    private String groupName;
    //Todo:change it to string min 6 hours
    private String secretCode;

    private String adminId;

    @ElementCollection
    private Set<String> auth0Ids;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Competition> competitions;

    public Group() {

    }

    public Group(String groupName, String secretCode, String adminId, Set<String> auth0Ids, Set<Competition> competitions) {
        this.groupName = groupName;
        this.secretCode = secretCode;
        this.adminId = adminId;
        this.auth0Ids = auth0Ids;
        this.competitions = competitions;
    }

    public Set<String> getAuth0Ids() {
        return auth0Ids;
    }

    public void setAuth0Ids(Set<String> auth0Ids) {
        this.auth0Ids = auth0Ids;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String inviteCode) {
        this.secretCode = inviteCode;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }
}

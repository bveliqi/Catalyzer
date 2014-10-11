package com.hackzurich.catalyzer.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {

    private long id;
    private long authorId;
    private String name;
    private String motivation;
    private String photoUrl;
    private String category; // TODO: better to use enum?
    private int pointsThreshold;
    private String status;
    private List<User> applyingUsers;
    private List<User> approvedUsers;
    private DateTime startDate;
    private DateTime endDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPointsThreshold() {
        return pointsThreshold;
    }

    public void setPointsThreshold(int pointsThreshold) {
        this.pointsThreshold = pointsThreshold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getApplyingUsers() {
        return applyingUsers;
    }

    public void setApplyingUsers(List<User> applyingUsers) {
        this.applyingUsers = applyingUsers;
    }

    public List<User> getApprovedUsers() {
        return approvedUsers;
    }

    public void setApprovedUsers(List<User> approvedUsers) {
        this.approvedUsers = approvedUsers;
    }

    public Timestamp getStartDate() {
        return new Timestamp(startDate.getMillis());
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = new DateTime(startDate);
    }

    public Timestamp getEndDate() {
        return endDate == null ? null : new Timestamp(endDate.getMillis());
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = new DateTime(endDate);
    }


}

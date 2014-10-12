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
    private List<UserProject> applyingUsers;
    private List<UserProject> approvedUsers;
    private DateTime startDate;
    private DateTime endDate;
    private long latitude;
    private long longitude;
    private int points;


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

    public List<UserProject> getApplyingUsers() {
        return applyingUsers;
    }

    public void setApplyingUsers(List<UserProject> applyingUsers) {
        this.applyingUsers = applyingUsers;
    }

    public List<UserProject> getApprovedUsers() {
        return approvedUsers;
    }

    public void setApprovedUsers(List<UserProject> approvedUsers) {
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


    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (authorId != project.authorId) return false;
        if (id != project.id) return false;
        if (pointsThreshold != project.pointsThreshold) return false;
        if (!category.equals(project.category)) return false;
        if (!motivation.equals(project.motivation)) return false;
        if (!name.equals(project.name)) return false;
        if (!photoUrl.equals(project.photoUrl)) return false;
        if (!status.equals(project.status)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (authorId ^ (authorId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + motivation.hashCode();
        result = 31 * result + photoUrl.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + pointsThreshold;
        result = 31 * result + status.hashCode();
        return result;
    }
}

package com.hackzurich.catalyzer.api;

/**
 * Created by behar on 11.10.14.
 */
public class Project {


    private long id;
//    private User author;
    private String author;
    private String name;
    private String motivation;
    private String photoUrl;
//    private List<String> keywords;
    private String category; // TODO: better to use enum?
    private int pointsThreshold;
    private String status;
//    private List<User> applyingUsers;
//    private List<User> approvedUsers;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public User getAuthor() {
//        return author;
//    }

    public String getAuthor() {return author;}

//    public void setAuthor(User author) {
//        this.author = author;
//    }
    public void setAuthor(String author) {
        this.author = author;
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

//    public List<String> getKeywords() {
//        return keywords;
//    }

//    public void setKeywords(List<String> keywords) {
//        this.keywords = keywords;
//    }

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

//    public List<User> getApplyingUsers() {
//        return applyingUsers;
//    }
//
//    public void setApplyingUsers(List<User> applyingUsers) {
//        this.applyingUsers = applyingUsers;
//    }
//
//    public List<User> getApprovedUsers() {
//        return approvedUsers;
//    }
//
//    public void setApprovedUsers(List<User> approvedUsers) {
//        this.approvedUsers = approvedUsers;
//    }



}

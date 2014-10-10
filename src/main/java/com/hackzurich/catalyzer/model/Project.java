package com.hackzurich.catalyzer.model;

import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
public class Project {


    public Project() {
    }


    private long id;
    private User author;
    private String name;
    private String motivation;
    private String photoUrl;
    private List<String> keywords;
    private String category; // TODO: better to use enum?
    private int pointsThreshold;
    private String status;
    private List<User> applyingUsers;
    private List<User> approvedUsers;


}

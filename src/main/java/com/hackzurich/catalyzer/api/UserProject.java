package com.hackzurich.catalyzer.api;

/**
 * Created by behar on 11.10.14.
 */
public class UserProject {

    private long id;
    private String name;
    private String reason;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

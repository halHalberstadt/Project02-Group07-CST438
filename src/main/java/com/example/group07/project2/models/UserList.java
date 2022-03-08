package com.example.group07.project2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * We are creating a UserList entity that represents
 * a user list in our database. This is used to sort
 * the lists that the user wants.
 */
@Entity
public class UserList {

    @Id // this is our primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    String title;
    String description;

    /**
     * Getters and Setters for our private variables
     * This is the data we will be inserting into our entity
     */
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer id) {
        this.userId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setList(String list) {
        this.title=list;
    }
}

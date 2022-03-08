package com.example.group07.project2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * We are creating a List entity that represents
 * a user in our database. This is similar to a room database
 */
@Entity
public class List {
    @Id // our primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer listId;

    private Integer userListId;
    private String listName;
    private String listDescription;


    /**
     * Getters and Setters for our private variables
     * This is the data we will be inserting into our entity
     */
    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getUserListId() {
        return userListId;
    }

    public void setUserListId(Integer userListId) {
        this.userListId = userListId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }
}

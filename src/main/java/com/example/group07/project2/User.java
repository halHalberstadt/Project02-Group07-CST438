package com.example.group07.project2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * We are creating a User entity that represents
 * a user in our database. This is similar to a room database
 */
@Entity
public class User {
    @Id // this is our primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String username;
    private String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

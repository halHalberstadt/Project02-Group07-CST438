package com.example.group07.project2;

import javax.persistence.*;

/**
 * We are creating a User entity that represents
 * a user in our database. This is similar to a room database
 */
@Entity
public class User {
    @Id // this is our primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String name;
    private String username;
    private String password;

    /**
     * user should be able to create a list, so we can create a List so
     * the user can add his/her lists inside this list
     *
     * UserList is a list of lists, so we can add it here
     *
     * OneToOne relationships associate one record in one table with a single
     * record in the other table
     */


    /**
     * Getters and Setters for our private variables
     * This is the data we will be inserting into our entity
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

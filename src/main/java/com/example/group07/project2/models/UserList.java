package com.example.group07.project2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * We are creating a UserList entity that represents
 * the list created by the user
 */
@Entity
public class UserList {
    @Id // this is our primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userListId;

    private Integer userId;
    private List<UserList> list;


    /**
     * Getters and Setters for our private variables
     * This is the data we will be inserting into our entity
     */
    public Integer getUserListId() {
        return userListId;
    }

    public void setUserListId(Integer userListId) {
        this.userListId = userListId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<UserList> getList() {
        return this.list;
    }

    public void setList(List<UserList> list) {
        this.list = list;
    }
}
package com.example.group07.project2;

import javax.persistence.*;
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
    private String list;    // this is the name of the list

//    @OneToMany
//    private List<WishList> list2;

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

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
package com.example.group07.project2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class UserList {
    @Id // this is our primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userListID;

    private Integer userID;
    private String List;


    public Integer getUserListID() {
        return userListID;
    }

    public void setUserListID(Integer userListID) {
        this.userListID = userListID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getList() {
        return List;
    }

    public void setList(String list) {
        List = list;
    }

}

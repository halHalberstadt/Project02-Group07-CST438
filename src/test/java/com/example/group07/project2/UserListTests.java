package com.example.group07.project2;

import com.sun.source.doctree.IndexTree;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserListTests {

    @LocalServerPort
    private int port;

    private int userListId;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void addUserList() {
        /** Testing for an existing item*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/addList?list=testUserList",
                String.class)).contains("Saved!");
        /** Testing for all empty entries*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/addList?list=",
                String.class)).contains("\"status\":405,\"error\":\"Method Not Allowed\",\"path\":\"/userListApi/addList\"");
        /** Testing for regular creation*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/addList?list=testUserList",
                String.class)).contains("\"status\":405,\"error\":\"Method Not Allowed\",\"path\":\"/userListApi/addList\"");
    }

    @Test
    @Order(2)
    void getUserList() {
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=notExisting",
                String.class)).contains("UserList not found.");
        /** Testing for an existing id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=testUserList",
                String.class)).contains("UserListId:");
        /** grabbing id for the list for future calls */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=testUserList", String.class);
        userListId = Integer.getInteger(returnedString.substring(returnedString.indexOf("Id:"), returnedString.indexOf("listI")));
    }

    @Test
    @Order(3)
    void updateUserList() {
        /** Testing for a entry that doesn't exist */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/updateUserList?userListId=523&userId=222&list=yes",
                String.class)).contains("was not found and could not be updated.");
        /** Testing for regular updating */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/updateUserList?userListId=" + userListId + "&userId=69&list=",
                String.class)).contains("was found and updated.");
    }

    @Test
    @Order(3)
    void deleteUserList() {
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/deleteUserList?userListID=&userID=",
                String.class)).contains("was not found and could not be deleted.");
        /** Testing for an existing name*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/deleteUserList?userListID=" + userListId + "userID=69",
                String.class)).contains("UserList was deleted");
    }
}

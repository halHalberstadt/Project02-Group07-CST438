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

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void addUserList() throws Exception {
        /** Testing for all empty entries*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/addList?list=",
                String.class)).contains("unable to create list.");
        /** Testing for regular creation*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/addList?list=testUserList",
                String.class)).contains("Saved!");
    }

    @Test
    @Order(2)
    void getUserList() throws Exception {
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=notExisting",
                String.class)).contains("UserList not found.");
        /** Testing for an existing id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=testUserListUp",
                String.class)).contains("UserListId:");
    }

    @Test
    @Order(3)
    void updateUserList() throws Exception {
        /** grabbing id for the list */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=testUserList", String.class);
        String toInt = (returnedString.substring(returnedString.indexOf(":")+2,returnedString.indexOf(",")));
        int userListId = Integer.parseInt(toInt);
        /** Testing for a entry that doesn't exist */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/updateUserList?userListId=523&userId=222&list=yes",
                String.class)).contains("was not found and could not be updated.");
        /** Testing for regular updating */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/updateUserList?userListId="+userListId+"&userId=69&list=testUserListUp",
                String.class)).contains("was found and updated.");
    }

    @Test
    @Order(4)
    void deleteUserList() throws Exception {
        /** grabbing id for the list */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=testUserListUp", String.class);
        String toInt = (returnedString.substring(returnedString.indexOf(":")+2,returnedString.indexOf(",")));
        int userListId = Integer.parseInt(toInt);
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/deleteUserList?userListID=&userID=",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/userListApi/deleteUserList\"");
        /** Testing for an existing name*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/deleteUserList?userListID="+userListId+"&userID=69",
                String.class)).contains("was deleted");
    }
}

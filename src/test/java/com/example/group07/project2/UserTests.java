package com.example.group07.project2;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {

    private int userId;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void addUser() {
        /** Testing for an existing item*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/addUser?username=addUserTest&password=noSecurity",
                String.class)).contains("");
        /** Testing for all empty entries*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/addUser?username=&password=",
                String.class)).contains("");
        /** Testing for regular creation*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/addUser?username=addUserTest&password=noSecurity",
                String.class)).contains("new User Added!");
    }

    @Test
    void getUser() {
        /** grabbing id for the list for future calls */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=testUserList", String.class);
        userId = Integer.getInteger(returnedString.substring(returnedString.indexOf("Id:")));
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/findByName?name=",
                User.class)).isEqualTo(null);
        /** Testing for an existing id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/findByName?name=addUserTest",
                String.class)).contains("password");
    }

    @Test
    void updateUser() {
        /** Testing for a entry that doesn't exist */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/updateUser?userID=&username=&password=",
                String.class)).contains("was not found and could not be updated.");
        /** Testing for regular updating */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/updateUser?userID=" + userId+ "&username=addUserTestu&password=hahaLessSecurity123",
                String.class)).contains("was found and updated.");
    }

    @Test
    @Order(2)
    void deleteUser() {
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/deleteUser?name=test4delete",
                String.class)).contains("was not found and could not be deleted.");
        /** Testing for an existing name*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/deleteUser?name=addUserTestu",
                String.class)).contains("was found and deleted.");
    }
}

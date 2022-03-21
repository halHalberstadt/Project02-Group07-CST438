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
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/addUser?username=test1&password=test1",
                String.class)).contains("username taken!");
        /** Testing for all empty entries*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/addUser?username=&password=",
                String.class)).contains("Invalid username or password");
        /** Testing for regular creation*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/addUser?username=addUserTest&password=noSecurity",
                String.class)).contains("Saved User!");
    }

    @Test
    @Order(2)
    void getUser() {
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/getUserByUsername?username=",
                String.class)).contains("User not found.");
        /** Testing for an existing id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/getUserByUsername?username=addUserTestUp",
                String.class)).contains("UserId:");
    }

    @Test
    @Order(3)
    void updateUser() {
        /** grabbing id */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/userApi/getUserByUsername?username=addUserTest", String.class);
        String toInt = (returnedString.substring(returnedString.indexOf(":")+2,returnedString.indexOf(",")));
        userId = Integer.parseInt(toInt);
        /** Testing for an empty entry */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/updateUser?userID=&username=&password=",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/userApi/updateUser\"");
        /** Testing for a entry that doesn't exist */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/updateUser?userID=10&username=nowork&password=badword",
                String.class)).contains("was not found and could not be updated.");
        /** Testing for regular updating */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/updateUser?userID=" + userId+ "&username=addUserTestUp&password=hahaLessSecurity123",
                String.class)).contains("was found and updated.");
    }

    @Test
    @Order(4)
    void deleteUser() {
        /** grabbing id */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/userApi/getUserByUsername?username=addUserTestUp", String.class);
        String toInt = (returnedString.substring(returnedString.indexOf(":")+2,returnedString.indexOf(",")));
        userId = Integer.parseInt(toInt);
        /** Testing for a non-existent entry */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/deleteUser?userID=",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/userApi/deleteUser\"");
        /** Testing for a bad id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/deleteUser?userID=10",
                String.class)).contains("not found and could not be deleted.");
        /** Testing for an existing name*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/userApi/deleteUser?userID="+ userId,
                String.class)).contains("was found and deleted.");
    }
}

package com.example.group07.project2;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WishListTests {

    private int wishListId;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void addWishList() {
        /** Testing for an regular list */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/addWishList?listName=wishListTestlist&Description=somethingTronRelated",
                String.class)).contains("");
        /** Testing for all empty entries */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/addWishList?listName&=listDescription=",
                String.class)).contains("");
        /** Testing for existing (just created) list */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/addWishList?listName=wishListTestlist&listDescription=somethingTronRelated",
                String.class)).contains("new list Added!");

    }

    @Test
    void getWishList() {
        /** grabbing id for the list for future calls */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=testUserList", String.class);
        wishListId = Integer.getInteger(returnedString.substring(returnedString.indexOf("listId:")));
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/getWishListByName?name=funsies",
                String.class)).contains("");
        /** Testing for an existing id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/getWishListByName?name=wishListTestlist",
                String.class)).contains("listName");
    }

    @Test
    void updateWishList() {
        /** Testing for a entry that doesn't exist */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/updateWishList?listId=&userListId=&listName=&listDescription=",
                String.class)).contains("was not found and could not be updated.");
        /** Testing for regular updating */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/updateWishList?listId="+wishListId+"&userListId=&listName=changed4fun&listDescription=changerino",
                String.class)).contains("was found and updated.");
    }

    @Test
    @Order(2)
    void deleteWishList() {
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/deleteWishList?userListID=1234&listID=1234",
                String.class)).contains("");
        /** Testing for an existing name*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/deleteWishList?userListID=&listID="+wishListId+"",
                String.class)).contains("Success!");
    }
}

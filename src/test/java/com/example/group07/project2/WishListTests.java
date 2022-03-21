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
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/addWishList?listName=wishListTestlist&Description=somethingTronRelated&userListId=",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/wishListApi/addWishList\"");
        /** Testing for all empty entries */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/addWishList?listName&=listDescription=&userListId=",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/wishListApi/addWishList\"");
        /** Testing for existing (just created) list */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/addWishList?listName=wishListTestlist&listDescription=somethingTronRelated&userListId=3",
                String.class)).contains("new list Added!");

    }

    @Test
    @Order(2)
    void getWishList() {
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/getWishListByName?name=funsies",
                String.class)).contains("Wishlist not found.");
        /** Testing for an existing id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/getWishListByName?name=changed4fun",
                String.class)).contains("listId:");
    }

    @Test
    @Order(3)
    void updateWishList() {
        /** grabbing id for the list */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/getWishListByName?name=wishListTestlist", String.class);
        String toInt = (returnedString.substring(returnedString.indexOf(":")+2,returnedString.indexOf(",")));
        wishListId = Integer.parseInt(toInt);
        /** Testing for a entry that doesn't exist */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/updateWishList?listId=5&userListId=8&listName=&listDescription=",
                String.class)).contains("was not found and could not be updated.");
        /** Testing for regular updating */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/updateWishList?listId="+wishListId+"&userListId=69&listName=changed4fun&listDescription=changerino",
                String.class)).contains("was found and updated.");
    }

    @Test
    @Order(4)
    void deleteWishList() {
        /** grabbing id for the list */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/getWishListByName?name=changed4fun", String.class);
        String toInt = (returnedString.substring(returnedString.indexOf(":")+2,returnedString.indexOf(",")));
        wishListId = Integer.parseInt(toInt);
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/deleteWishList?userListID=1234&listID=1234",
                String.class)).contains("");
        /** Testing for an existing name*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wishListApi/deleteWishList?userListID=69&listID="+wishListId+"",
                String.class)).contains("Success!");
    }
}

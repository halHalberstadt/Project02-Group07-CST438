package com.example.group07.project2;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemTests {

    private int itemId;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void addItem() throws Exception {
        /** Testing for an existing item*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/addItem?itemName=crumpled soda can&itemDescription=it's garbage, fun!&itemCategory=garbage&itemPrice=$0.25&itemQuantity=1&itemImage=https://thumbs.dreamstime.com/z/red-crushed-soda-can-empty-smashed-pop-isolated-white-background-71920441.jpg",
                String.class)).contains("item already in database or is missing fields.");
        /** Testing for all empty entries*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/addItem?itemName=&itemDescription=&itemCategory=&itemPrice=&itemQuantity=&itemImage=",
                String.class)).contains("item already in database or is missing fields.");
        /** Testing for regular creation*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/addItem?itemName=tron on dvd&itemDescription=the 1980's one&itemCategory=great movies&itemPrice=$1000000&itemQuantity=12&itemImage=www.tron.png",
                String.class)).contains("new item Added!");
    }

    @Test
    public void getItem() throws Exception {
        /** grabbing id for the list for future calls */
        String returnedString = this.restTemplate.getForObject("http://localhost:" + port + "/userListApi/getUserListByTitle?title=testUserList", String.class);
        String toInt = (returnedString.substring(returnedString.indexOf(":")+2,returnedString.indexOf(",")-1));
        itemId = Integer.parseInt(toInt);
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/getItemId?id=1",
                String.class)).contains("Item not found.");
        /** Testing for a bad id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/getItemId?id=test",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/itemApi/getItemId\"");
        /** Testing for an existing id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/getItemId?id=6",
                String.class)).contains("itemName");
    }

    @Test
    void updateItem() throws Exception  {
        /** Testing for a entry that doesn't exist */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/updateItem?itemID=1&listId&itemName=crumpled soda can&itemDescription=it's garbage, fun!&itemCategory&itemPrice=$0.25&itemQuantity&itemImage",
                String.class)).contains("The Item was not found and could not be updated.");
        /** Testing for regular updating */
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/updateItem?itemID=6&listId&itemName=crumpled soda can&itemDescription=it's garbage, fun!&itemCategory&itemPrice=$0.25&itemQuantity&itemImage",
                String.class)).contains("The Item was found and updated!");
    }

    @Test
    @Order(2)
    void deleteItem() throws Exception {
        /** Testing for an absent id*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/deleteItemByName?name=test4delete",
                String.class)).contains("Item with name: test4delete was not found and could not be deleted.");
        /** Testing for an existing name*/
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/itemApi/deleteItemByName?name=tron on dvd",
                String.class)).contains("Item: tron on dvd was found and deleted.");
    }
}

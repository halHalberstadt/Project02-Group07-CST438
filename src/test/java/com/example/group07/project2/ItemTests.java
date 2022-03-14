package com.example.group07.project2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemTests {

    ItemApi testApi = new ItemApi();

    @Test
    void addItem() {
        testApi.addItem();
    }

    @Test
    void getItem() {
        testApi.getAllItems();
    }

    @Test
    void updateItem() {
        testApi.updateItem();
    }

    @Test
    void deleteItem() {
        testApi.deleteItem();
    }
}

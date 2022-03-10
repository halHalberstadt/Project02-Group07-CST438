package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Entry point for our API. This is how we will obtain our user data
 */
@Controller
@RequestMapping(path="/itemApi")

public class ItemApi {

    /**
     * User Autowired annotation knows to connect
     * to a database. It imports dependencies at the time
     * we need them.
     */
    @Autowired
    private ItemRepository itemRepository;

    /**
     * This path will actually return all items without
     * us having to type so much code!
     */
    @GetMapping(path = "/allItems")
    public @ResponseBody Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * This PostMapping will allow us to add items to our
     * database entity
     *
     * Literally this is all we need to add stuff to our database
     */
    @PostMapping(path="/addItem")
    public @ResponseBody String addItem (@RequestParam String itemName, @RequestParam String itemDescription, @RequestParam String itemCategory, @RequestParam String itemPrice, @RequestParam Integer itemQuantity, @RequestParam String itemImage) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setItemDescription(itemDescription);
        item.setItemCategory(itemCategory);
        item.setItemPrice(itemPrice);
        item.setItemQuantity(itemQuantity);
        item.setItemImage(itemImage);

        itemRepository.save(item);

        return "new item Added!";
    }


}
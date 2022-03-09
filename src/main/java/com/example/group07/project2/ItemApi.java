package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping(path="/itemApi")

public class ItemApi {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(path = "/allItems")
    public @ResponseBody Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }


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

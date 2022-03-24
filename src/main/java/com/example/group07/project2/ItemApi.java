package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
     * This path will return just the item from the id in the url,
     * or returns null.
     */
    @GetMapping(path = "/getItemId")
    public @ResponseBody String getItemId(@RequestParam @NonNull Integer id) {
        for(Item i:itemRepository.findAll()){
            if(i.getItemId().equals(id))
                return "{\n\titemId:" + id +
                        "\n\tlistId: " + i.getListId() + "," +
                        "\n\titemName: \"" + i.getItemName() + "\"," +
                        "\n\titemDescription: \"" + i.getItemDescription() + "\"," +
                        "\n\titemCategory: \"" + i.getItemCategory() + "\"," +
                        "\n\titemPrice: \"" + i.getItemPrice() + "\"," +
                        "\n\titemQuantity: " + i.getItemQuantity() + "," +
                        "\n\titemImage: \"" + i.getItemImage() + "\"\n}";
        }
        return "Item not found.";
    }

    /**
     * This PostMapping will allow us to add items to our
     * database entity
     *
     * Literally this is all we need to add stuff to our database
     */
    @GetMapping(path="/addItem")
    public @ResponseBody String addItem (@RequestParam @NonNull String itemName, @RequestParam @NonNull String itemDescription, @RequestParam @NonNull String itemCategory, @RequestParam String itemPrice, @RequestParam String itemQuantity, @RequestParam String itemImage) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setItemDescription(itemDescription);
        item.setItemCategory(itemCategory);
        item.setItemPrice(itemPrice);
        item.setItemQuantity(Integer.getInteger(itemQuantity));
        item.setItemImage(itemImage);

        boolean canSave = true;
        for (Item i:
                itemRepository.findAll()) {
            if(i.getItemName().equals(item.getItemName()) &&
                    i.getItemDescription().equals(item.getItemDescription()) &&
                    i.getItemCategory().equals(item.getItemCategory())){
                canSave = false;
            }
        }
        if(canSave) {
            itemRepository.save(item);
            return "new item Added!";
        }
        return "item already in database or is missing fields.";
    }

    /**
     * deleteItem:
     * Deletes item off of the db.
     * TODO: add admin restriction
     */
    @GetMapping("/deleteItem")
    public @ResponseBody String deleteItemById(@RequestParam @NonNull Integer itemID) {
        for(Item currItem: itemRepository.findAll()){
            if(currItem.getItemId().equals(itemID)){
                itemRepository.delete(currItem);
                return "Item with ID:" + itemID + " was found and deleted.";
            }
        }

        return "Item with ID: " + itemID + " was not found and could not be deleted.";
    }
    @GetMapping("/deleteItemByName")
    public @ResponseBody String deleteItemById(@RequestParam @NonNull String name) {
        for(Item currItem: itemRepository.findAll()){
            if(currItem.getItemName().equals(name)){
                itemRepository.delete(currItem);
                return "Item: " + name + " was found and deleted.";
            }
        }

        return "Item with name: " + name + " was not found and could not be deleted.";
    }

    /**
     * UpdateItem:
     * this updates the fields of a User on the system.
     * TODO: add admin restriction/ from that same user
     */
    @GetMapping("/UpdateItem")
    public @ResponseBody String UpdateItem(@RequestParam Integer itemID,
                                           @RequestParam(required = false) String listId,
                                           @RequestParam(required = false) String itemName,
                                           @RequestParam(required = false) String itemDescription,
                                           @RequestParam(required = false) String itemCategory,
                                           @RequestParam(required = false) String itemPrice,
                                           @RequestParam(required = false) String itemQuantity,
                                           @RequestParam(required = false) String itemImage) {
        for(Item currItem: itemRepository.findAll()){
            if(currItem.getItemId().equals(itemID)){
                if(!listId.isBlank())
                    currItem.setListId(Integer.getInteger(listId));

                if(!itemName.isBlank())
                    currItem.setItemName(itemName);

                if(!itemDescription.isBlank())
                    currItem.setItemDescription(itemDescription);

                if(!itemCategory.isBlank())
                    currItem.setItemCategory(itemCategory);

                if(!itemPrice.isBlank())
                    currItem.setItemPrice(itemPrice);

                if(!itemQuantity.isBlank())
                    currItem.setItemQuantity(Integer.getInteger(itemQuantity));

                if(!itemImage.isBlank())
                    currItem.setItemImage(itemImage);

                itemRepository.save(currItem);

                return "User: " + currItem.getItemName() + " with ID:" + itemID + " was found and updated.";
            }
        }

        return "User with ID: " + itemID + " was not found and could not be updated.";
    }


}
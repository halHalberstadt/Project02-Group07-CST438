package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Entry point for our API. This is how we will obtain our user data
 */
@Controller
@RequestMapping(path="/wishListApi")
public class WishListApi {

    /**
     * User Autowired annotation knows to connect
     * to a database. It imports dependencies at the time
     * we need them.
     */
    @Autowired
    private WishListRepository wishListRepository;

    /**
     * This path will actually return all lists without
     * us having to type so much code!
     */
    @GetMapping(path = "/allWishLists")
    public @ResponseBody Iterable<WishList> getAllWishLists() {
        return wishListRepository.findAll();
    }

    /**
     * This path will return just the user from the id in the url,
     * or returns null.
     */
    @GetMapping(path = "/getWishListById")
    public @ResponseBody WishList getWishListById(@RequestParam @NonNull Integer id) {
        for(WishList i:wishListRepository.findAll()){
            if(i.getListId().equals(id))
                return i;
        }
        return null;
    }
    @GetMapping(path = "/getWishListByName")
    public @ResponseBody WishList getWishListByName(@RequestParam @NonNull String name) {
        for(WishList i:wishListRepository.findAll()){
            if(i.getListName().equals(name))
                return i;
        }
        return null;
    }

    /**
     * This PostMapping will allow us to add lists to our
     * database entity
     *
     * Literally this is all we need to add stuff to our database
     */
    @GetMapping(path="/addWishList")
    public @ResponseBody String addList (@RequestParam String listName, @RequestParam String listDescription) {
        WishList list = new WishList();
        list.setListName(listName);
        list.setListDescription(listDescription);

        wishListRepository.save(list);

        return "new list Added!";
    }

    /**
     * deleteListItem:
     * removes an Item from a list, but not the system.
     * (disconnecting the link)
     * TODO: add admin restriction/ from that same user
     */
    @GetMapping("/deleteWishList")
    public @ResponseBody String deleteWishList (@RequestParam @NonNull Integer userListID, @RequestParam @NonNull Integer listID) {
        for(WishList currList: wishListRepository.findAll()){
            if(currList.getUserListId().equals(userListID) && currList.getListId().equals(listID)){
                wishListRepository.delete(currList);
            }
        }
        return "Success!";
    }

    /**
     * UpdateItemList:
     * this updates the fields of a User on the system.
     * TODO: add admin restriction/ from that same user
     */
    @GetMapping("/updateWishList")
    public @ResponseBody String updateWishList(@RequestParam Integer listId,
                                               @RequestParam(required = false) String userListId,
                                               @RequestParam(required = false) String listName,
                                               @RequestParam(required = false) String listDescription) {
        for(WishList currItemList: wishListRepository.findAll()){
            if(currItemList.getListId().equals(listId)){
                if(!userListId.isBlank())
                    currItemList.setUserListId(Integer.getInteger(userListId));

                if(!listName.isBlank())
                    currItemList.setListName(listName);

                if(!listDescription.isBlank())
                    currItemList.setListDescription(listDescription);

                wishListRepository.save(currItemList);

                return "ItemList: " + currItemList.getListName() + " with ID:" + listId + " was found and updated.";
            }
        }

        return "ItemList with ID: " + listId + " was not found and could not be updated.";
    }
}
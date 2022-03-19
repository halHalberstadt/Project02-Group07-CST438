package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

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
    public @ResponseBody String getWishListById(@RequestParam @NonNull Integer id) {
        for(WishList i:wishListRepository.findAll()){
            if(i.getListId().equals(id))
                return "listId: " + i.getListId() + "," +
                        "userListId: " + i.getUserListId() + "," +
                        "listName: " + i.getListName() + "";;
        }
        return "Wishlist not found.";
    }
    @GetMapping(path = "/getWishListByName")
    public @ResponseBody String getWishListByName(@RequestParam @NonNull String name) {
        for(WishList i:wishListRepository.findAll()){
            if(i.getListName().equals(name))
                return "listId: " + i.getListId() + "," +
                        "userListId: " + i.getUserListId() + "," +
                        "listName: " + i.getListName() + "";;
        }
        return "Wishlist not found.";
    }

    /**
     * This PostMapping will allow us to add lists to our
     * database entity
     *
     * Literally this is all we need to add stuff to our database
     */
    @GetMapping(path="/addWishList")
    public @ResponseBody String addList (@RequestParam @NonNull String listName,
                                         @RequestParam @NonNull String listDescription,
                                         @RequestParam @NonNull Integer userListId) {
        WishList list = new WishList();

        for(WishList wl : wishListRepository.findAll())
            if(wl.getUserListId().equals(userListId))
                if(wl.getListName().equals(listName)) {
                    return "Create a unique list name.";
                }

        if(!listName.isBlank())
            list.setListName(listName);
        if(!listDescription.isBlank())
            list.setListDescription(listDescription);
        if(!userListId.toString().isBlank())
            list.setUserListId(userListId);


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
            if(currList.getListId().equals(userListID) && currList.getListId().equals(listID)){
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
                                               @RequestParam Integer userListId,
                                               @RequestParam(required = false) String listName,
                                               @RequestParam(required = false) String listDescription) {
        for(WishList currItemList: wishListRepository.findAll()){
            if(currItemList.getListId().equals(listId)){
                if(!userListId.toString().isBlank())
                    currItemList.setUserListId(userListId);


                if(!listName.isBlank())
                    currItemList.setListName(listName);

//                if(!listDescription.isBlank())
//                    currItemList.setListDescription(listDescription);

                wishListRepository.save(currItemList);

                return "ItemList: " + currItemList.getListName() + " with ID:" + listId + " was found and updated.";
            }
        }

        return "ItemList with ID: " + listId + " was not found and could not be updated.";
    }
}
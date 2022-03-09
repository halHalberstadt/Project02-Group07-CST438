package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
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
     * This PostMapping will allow us to add lists to our
     * database entity
     *
     * Literally this is all we need to add stuff to our database
     */
    @PostMapping(path="/addWishList")
    public @ResponseBody String addList (@RequestParam String listName, @RequestParam String listDescription) {
        WishList list = new WishList();
        list.setListName(listName);
        list.setListDescription(listDescription);

        wishListRepository.save(list);

        return "new list Added!";
    }
}


package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WishlistController {

    @Autowired
    WishListRepository wishListRepository;

    @RequestMapping(value="/allWishLists",method= RequestMethod.GET)
    public String wishList(Model model) {
        model.addAttribute("lists", wishListRepository.findAll());
        return "allWishLists";
    }
}
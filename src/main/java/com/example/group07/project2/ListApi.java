package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/listApi")
public class ListApi {

    @Autowired
    private ListRepository listRepository;

    @GetMapping(path = "/allLists")
    public @ResponseBody
    Iterable<List> getAllLists() {
        return listRepository.findAll();
    }



    @PostMapping(path="/addList")
    public @ResponseBody String addList (@RequestParam String listName, @RequestParam String listDescription) {
        List list = new List();
        list.setListName(listName);
        list.setListDescription(listDescription);

        listRepository.save(list);

        return "new list Added!";
    }
}

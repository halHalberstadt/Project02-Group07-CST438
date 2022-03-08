package com.example.group07.project2.apis;

import com.example.group07.project2.models.UserList;
import com.example.group07.project2.repos.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * This is how we will obtain data from our UserList entity
 */
@Controller
@RequestMapping(path = "/userListApi")
public class UserListApi {

    /**
     * User Autowired annotation knows to connect
     * to a database. It imports dependencies at the time
     * we need them.
     */
    @Autowired
    private UserListRepository userListRepository;

    /**
     * This path will actually return all users' lists
     * without us having to type so much code!
     */
    @GetMapping(path = "/allUserLists")
    public @ResponseBody Iterable<UserList> getAllUserLists() {
        return userListRepository.findAll();
    }

    /**
     * This PostMapping will allow us to add users to our
     * database entity
     *
     * Literally this is all we need to add stuff to our database
     *
     * Honestly, idk if we need more parameters in the addList method,
     * the id is our primary key and the userId is a foreign key, so I don't think
     * we set it. I'll need to find out about it!!
     */
    @PostMapping(path="/addList")
    public @ResponseBody String addList (@RequestParam String list) {
        UserList userList = new UserList();
        userList.setList(list);

        userListRepository.save(userList);

        return "Saved!";
    }
}

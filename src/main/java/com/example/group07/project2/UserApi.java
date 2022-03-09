package com.example.group07.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Entry point for our API. This is how we will obtain our user data
 */
@Controller
@RequestMapping(path="/userApi")
public class UserApi {

    /**
     * User Autowired annotation knows to connect
     * to a database. It imports dependencies at the time
     * we need them.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * This path will actually return all users without
     * us having to type so much code!
     */
    @GetMapping(path = "/allUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * This PostMapping will allow us to add users to our
     * database entity
     *
     * Literally this is all we need to add stuff to our database
     */
    @PostMapping(path="/addUser")
    public @ResponseBody String addUser (@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userRepository.save(user);

        return "Saved User!";
    }

    @GetMapping(path="/findByName")
    public @ResponseBody
    List<User> findUserByName(@RequestParam (defaultValue = "daisy") String name) {
        return userRepository.findUserByName(name);
    }
}

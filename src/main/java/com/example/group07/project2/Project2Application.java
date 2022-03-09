package com.example.group07.project2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Project2Application {

    /**
     * Landing page (When someone is not logged in)
     */
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Landing page!\n We have the following endpoints:\n/itemApi/allItems\n/userApi/allUsers\n/userApi/findByName\n/userListApi/allUserList" +
                "\n/wishListApi/allWishList";
    }


    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }

}

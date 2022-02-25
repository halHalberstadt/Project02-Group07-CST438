package com.example.practiceApp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishListController {

  @GetMapping("/allLists")
  List<String> all(){
    List<String> temp = new ArrayList<>();
    temp.add("foo");
    temp.add("bar");
    temp.add("baz");
    return temp;
  }

}

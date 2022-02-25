package com.example.practiceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class DemoApplication {

  @RequestMapping("/")
  @ResponseBody
  String home(@RequestParam(defaultValue = "test") String id) {
    if(id.equals("21233")){
      return "AHHHHHHHHHH yeah";
    }
    return "Hello world! param == " + id;
  }

  @RequestMapping(value = "/name")
  @ResponseBody
  String name(){
    return "name";
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);

  }

}

package com.example.todolist.resources;


import com.example.todolist.model.User;
import com.example.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(){
        return "This is homepage";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> userMap) {
        System.out.println("sadsa");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);

        return user.getFirstName() + ", your user-id is: " + user.getUserId();


    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, Object> userMap) {
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.registerUser(firstName, lastName, email, password);
        return firstName + ", your user-id is: " + user.getUserId();
    }


}

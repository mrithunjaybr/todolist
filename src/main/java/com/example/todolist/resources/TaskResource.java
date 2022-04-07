package com.example.todolist.resources;

import com.example.todolist.model.User;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskResource {

    @Autowired
    private UserResource userResource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    @GetMapping("/list")
    HashMap<Integer,String> listTasks(){
        User user = userRepository.findByEmail(userResource.getEmailId());
        System.out.println("ITS working 1");
        return taskRepository.listTasks(user.getUserId());

    }

    @GetMapping("/add")
    public String addTasks(@RequestBody Map<String, String> userMap) {
        User user = userRepository.findByEmail(userResource.getEmailId());
        taskRepository.addTasks(user.getUserId(),userMap.get("task"));
        return "task added!!!";
    }
}

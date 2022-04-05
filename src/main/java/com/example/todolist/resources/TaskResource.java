package com.example.todolist.resources;

import com.example.todolist.model.User;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    String listTasks(){
        User user = userRepository.findByEmail(userResource.getEmailId());
        return "dummy lists";
    }
}

package com.example.todolist.services;

import com.example.todolist.model.User;

public interface UserService {
    User registerUser(String firstName, String lastName, String email, String password);
    User validateUser(String email, String password);

}

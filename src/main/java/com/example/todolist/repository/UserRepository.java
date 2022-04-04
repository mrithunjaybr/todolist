package com.example.todolist.repository;

import com.example.todolist.exception.ETAuthException;
import com.example.todolist.model.User;


public interface UserRepository {
    Integer createUser(String firstName, String lastName, String email, String password) throws ETAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);

    User findByEmailAndPassword(String email, String password) throws ETAuthException;

    User findByEmail(String email);
}

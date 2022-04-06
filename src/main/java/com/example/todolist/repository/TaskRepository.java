package com.example.todolist.repository;

import java.util.ArrayList;

public interface TaskRepository {
    ArrayList<String> listTasks(Integer userId);

    ArrayList<String> addTasks(Integer userId, String task);

    ArrayList<String> removeTasks(Integer userTaskId);

}

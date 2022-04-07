package com.example.todolist.repository;

import java.util.ArrayList;
import java.util.HashMap;

public interface TaskRepository {
    HashMap<Integer,String> listTasks(Integer userId);

    void addTasks(Integer userId, String task);

    ArrayList<String> removeTasks(Integer userTaskId);

}

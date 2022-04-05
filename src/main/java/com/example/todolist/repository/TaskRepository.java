package com.example.todolist.repository;

import com.example.todolist.model.Tasks;

public interface TaskRepository {
    Tasks listTasks(Integer userId);
    Tasks addTasks(Integer userId);
    Tasks removeTasks(Integer userTaskId);

}

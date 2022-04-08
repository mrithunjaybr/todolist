package com.example.todolist.model;

public class Tasks {
    private Integer userId;
    private Integer userTaskId;
    private String taskDescription;

    public Tasks() {
    }

    public Tasks(Integer userId, Integer userTaskId, String taskDescription) {
        this.userId = userId;
        this.userTaskId = userTaskId;
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "userId=" + userId +
                ", userTaskId=" + userTaskId +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}

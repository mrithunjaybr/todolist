package com.example.todolist.repository;

public class TaskRepositoryImpl {
    private static final String LIST_BY_USERID = "SELECT TASK FROM TDL_TASKS WHERE USER_ID = ?";

    private static final String ADD_TASK_BY_USERID = "INSERT INTO TDL_TASKS (USERTASK_ID, USER_ID, TASK) VALUES (NEXTVAL('TDL_CATEGORIES_SEQ')," +
            "?,?)";

    private static final String REMOVE_TASK_BY_USERID = "DELETE FROM TDL_TASKS WHERE USERTASK_ID = ?";

}

package com.example.todolist.repository;

import com.example.todolist.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private static final String LIST_BY_USERID = "SELECT USERTASK_ID,TASK FROM TDL_TASKS WHERE USER_ID = ?";

    private static final String LIST_BY_USERID_TWO = "SELECT * FROM TDL_TASKS";

    private static final String ADD_TASK_BY_USERID = "INSERT INTO TDL_TASKS (USERTASK_ID, USER_ID, TASK) VALUES (NEXTVAL('TDL_TASKS_SEQ')," +
            "?,?)";

    private static final String REMOVE_TASK_BY_USERID = "DELETE FROM TDL_TASKS WHERE USERTASK_ID = ?";

    private final RowMapper<Tasks> tasksRowMapper = ((rs, rowNum) -> new Tasks(rs.getInt("USERTASK_ID")
            , rs.getInt("USER_ID")
            , rs.getString("TASK")));
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public HashMap<Integer,String> listTasks(Integer userId) {
        HashMap<Integer,String> tasks = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/todolistdb", "postgres", "password");
             PreparedStatement preparedStatement = conn.prepareStatement(LIST_BY_USERID)) {
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Integer userTaskId = resultSet.getInt("USERTASK_ID");
                String task = resultSet.getString("TASK");

                Tasks obj = new Tasks();
                obj.setUserTaskId(userTaskId);
                obj.setUserId(userId);
                obj.setTaskDescription(task);
                tasks.put(userTaskId,task);

            }


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }


    @Override
    public void addTasks(Integer userId, String task) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/todolistdb", "postgres", "password");
             PreparedStatement preparedStatement = conn.prepareStatement(ADD_TASK_BY_USERID)) {
            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,task);
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeTasks(Integer userTaskId) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/todolistdb", "postgres", "password");
             PreparedStatement preparedStatement = conn.prepareStatement(REMOVE_TASK_BY_USERID)) {
            preparedStatement.setInt(1,userTaskId);
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

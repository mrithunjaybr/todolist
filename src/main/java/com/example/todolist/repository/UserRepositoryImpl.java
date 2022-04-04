package com.example.todolist.repository;

import com.example.todolist.exception.ETAuthException;
import com.example.todolist.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@SuppressWarnings("deprecation")
@Repository
public class UserRepositoryImpl implements UserRepository {


    private static final String SQL_CREATE = "INSERT INTO TDL_USERS(USER_ID, FIRST_NAME, LAST_NAME, EMAIL,PASSWORD) " +
            "VALUES(NEXTVAL('TDL_USERS_SEQ'),?,?,?,?)";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM TDL_USERS WHERE EMAIL=?";
    private static final String FIND_BY_USER_ID = "SELECT * FROM TDL_USERS WHERE USER_ID = ?";

    private static final String FIND_BY_EMAIL = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL,PASSWORD " +
            "FROM TDL_USERS WHERE EMAIL = ?";


    private final RowMapper<User> userRowMapper = ((rs, rowNum) -> new User(rs.getInt("USER_ID"),
            rs.getString("FIRST_NAME"),
            rs.getString("LAST_NAME"),
            rs.getString("EMAIL"),
            rs.getString("PASSWORD")));

    @Autowired
    JdbcTemplate jdbcTemplate;

    @SuppressWarnings({"UnnecessaryLocalVariable", "deprecation"})
    @Override
    public User findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
            return user;

        } catch (EmptyResultDataAccessException e) {
            throw new ETAuthException("Invalid email!");
        }
    }


    @Override
    public Integer createUser(String firstName, String lastName, String email, String password) throws ETAuthException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        } catch (Exception e) {
            throw new ETAuthException("Invalid Details. Failed to create account!");
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);

    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(FIND_BY_USER_ID, new Object[]{userId}, userRowMapper);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws ETAuthException {
        try {
            User user = jdbcTemplate.queryForObject(FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
            if (!BCrypt.checkpw(password, user.getPassword())) {
                throw new ETAuthException("Invalid email/password !");
            }
            return user;

        } catch (EmptyResultDataAccessException e) {
            throw new ETAuthException("Invalid email/password !");
        }
    }

}

package com.example.todolist.services;

import com.example.todolist.exception.ETAuthException;
import com.example.todolist.model.User;
import com.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) {
        //add user to database
        email = patternChecker(email);
        if (userRepository.getCountByEmail(email) > 0) {
            throw new ETAuthException("Email already in use");
        }
        Integer userId = userRepository.createUser(firstName, lastName, email, password);
        return userRepository.findById(userId);

    }

    @Override
    public User validateUser(String email, String password) {
        email = patternChecker(email);
        Integer count = userRepository.getCountByEmail(email);
        if (count > 0) {
            return userRepository.findByEmailAndPassword(email, password);
        }
        throw new ETAuthException("Email-ID not registered");
    }

    private String patternChecker(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) email = email.toLowerCase();
        if (!pattern.matcher(email).matches()) {
            throw new ETAuthException("Invalid email format");
        }
        return email;
    }
}

package com.myjdbc.Customer.customerServiceImpl;

import com.myjdbc.Customer.model.User;
import com.myjdbc.Customer.repository.UserRepository;
import com.myjdbc.Customer.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public boolean isValidLogin(User userInfo) {
        String username = userInfo.getUser_name();
        String password = userInfo.getPassword();

        User user = userRepository.getUserByUsername(username);

        // Check if a user with the given username exists and the password matches
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}


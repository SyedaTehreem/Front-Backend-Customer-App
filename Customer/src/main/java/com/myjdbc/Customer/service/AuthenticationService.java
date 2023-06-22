package com.myjdbc.Customer.service;

import com.myjdbc.Customer.model.User;

public interface AuthenticationService {
    boolean isValidLogin(User user);
}

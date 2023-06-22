package com.myjdbc.Customer.repository;

import com.myjdbc.Customer.model.User;

public interface UserRepository {
    User getUserByUsername(String user_name);
}

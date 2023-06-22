package com.myjdbc.Customer.repoImpl;

import com.myjdbc.Customer.model.User;
import com.myjdbc.Customer.repository.UserRepository;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/customer_application";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Systemlimited@90";
    @Override
    public User getUserByUsername(String username) {
        User user = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE user_name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User();
                        user.setUser_name(resultSet.getString("user_name"));
                        user.setPassword(resultSet.getString("password"));
                    }
                }
            }
        } catch (SQLException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }

        return user;
    }

}
package ru.mail.ilya6697089.app.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.mail.ilya6697089.app.repository.UserRepository;
import ru.mail.ilya6697089.app.repository.model.User;
import ru.mail.ilya6697089.app.repository.model.Role;

public class UserRepositoryImpl extends GeneralRepositoryImpl<User> implements UserRepository {

    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User add(Connection connection, User user) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user(username, password, createdBy) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getCreatedBy());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUser_id(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return user;
        }
    }

    @Override
    public List<User> findAll(Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user u " +
                                " LEFT JOIN role rl on  u.role_id = rl.id"
                )
        ) {
            List<User> users = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = getUser(rs);
                    users.add(user);
                }
                return users;
            }
        }
    }

    @Override
    public void delete(Connection connection, int id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM user WHERE user_id=?"
                )
        ) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected.");
            }
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        int id = rs.getInt("id");
        user.setUser_id(id);

        String username = rs.getString("username");
        user.setUsername(username);

        String password = rs.getString("password");
        user.setPassword(password);

        String createdBy = rs.getString("createdBy");
        user.setCreatedBy(createdBy);

        Role role = new Role();
        role.setName(rs.getString("name"));
        role.setDescription(rs.getString("description"));
        user.setRole(role);

        return user;
    }

}

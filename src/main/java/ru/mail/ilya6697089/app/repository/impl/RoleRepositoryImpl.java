package ru.mail.ilya6697089.app.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ru.mail.ilya6697089.app.repository.RoleRepository;
import ru.mail.ilya6697089.app.repository.model.Role;

public class RoleRepositoryImpl extends GeneralRepositoryImpl<Role> implements RoleRepository {

    private static RoleRepository instance;

    public static RoleRepository getInstance() {
        if (instance == null) {
            instance = new RoleRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Role add(Connection connection, Role role) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO role(id, name, description) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setInt(1, role.getRole_id());
            statement.setString(2, role.getName());
            statement.setString(3, role.getDescription());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating role failed, no rows affected.");
            }
            return role;
        }
    }

    @Override
    public void delete(Connection connection, int id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM role WHERE id=?"
                )
        ) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting role failed, no rows affected.");
            }
        }
    }

    @Override
    public List<Role> findAll(Connection connection) {
        throw new UnsupportedOperationException();
    }

}

package ru.mail.ilya6697089.app.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.ilya6697089.app.repository.ConnectionRepository;
import ru.mail.ilya6697089.app.repository.RoleRepository;
import ru.mail.ilya6697089.app.repository.UserRepository;
import ru.mail.ilya6697089.app.repository.impl.ConnectionRepositoryImpl;
import ru.mail.ilya6697089.app.repository.impl.RoleRepositoryImpl;
import ru.mail.ilya6697089.app.repository.impl.UserRepositoryImpl;
import ru.mail.ilya6697089.app.repository.model.User;
import ru.mail.ilya6697089.app.repository.model.Role;
import ru.mail.ilya6697089.app.service.UserService;
import ru.mail.ilya6697089.app.service.model.UserDTO;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private static UserService instance;

    private ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private UserRepository userRepository = UserRepositoryImpl.getInstance();
    private RoleRepository roleRepository = RoleRepositoryImpl.getInstance();

    public UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public List<UserDTO> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<User> people = userRepository.findAll(connection);
                List<UserDTO> userDTOList = people.stream()
                        .map(this::convertObjectToDTO)
                        .collect(Collectors.toList());
                connection.commit();
                return userDTOList;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public void addUser(UserDTO userDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                User user = convertDTOToObject(userDTO);
                userRepository.add(connection, user);
                user.getRole().setRole_id(user.getUser_id());
                roleRepository.add(connection, user.getRole());
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteUserById(int userId) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                roleRepository.delete(connection, userId);
                userRepository.delete(connection, userId);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private UserDTO convertObjectToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUser_id());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreatedBy(user.getCreatedBy());
        if (user.getRole() != null) {
            userDTO.setName(user.getRole().getName());
            userDTO.setDescription(user.getRole().getDescription());
        }
        return userDTO;
    }

    private User convertDTOToObject(UserDTO userDTO) {
        User user = new User();
        user.setUser_id(user.getUser_id());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setCreatedBy(userDTO.getCreatedBy());

        Role role = new Role();
        role.setName(userDTO.getName());
        role.setDescription(userDTO.getDescription());

        user.setRole(role);
        return user;
    }

}

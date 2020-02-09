package ru.mail.ilya6697089.app.service;

import java.util.List;

import ru.mail.ilya6697089.app.service.model.UserDTO;

public interface UserService {

    List<UserDTO> findAll();

    void addUser(UserDTO userDTO);

    void deleteUserById(int userId);

}

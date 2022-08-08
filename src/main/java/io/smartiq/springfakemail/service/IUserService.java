package io.smartiq.springfakemail.service;

import io.smartiq.springfakemail.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO findOne(Long id);

    void delete(Long id);

    void clearCache();
}

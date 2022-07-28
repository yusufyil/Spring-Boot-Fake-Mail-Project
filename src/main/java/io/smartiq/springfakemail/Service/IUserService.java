package io.smartiq.springfakemail.Service;

import io.smartiq.springfakemail.DTO.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO findOne(Long id);

    void delete(Long id);
}

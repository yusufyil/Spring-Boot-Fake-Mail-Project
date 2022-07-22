package io.smartiq.springfakemail.Service;

import io.smartiq.springfakemail.DTO.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO save(UserDTO emailDTO);
    UserDTO update(UserDTO emailDTO);
    List<UserDTO> findAll();
    UserDTO findOne(Long id);
    void delete(Long id);
}

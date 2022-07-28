package io.smartiq.springfakemail.Service;

import io.smartiq.springfakemail.DTO.RoleDTO;

public interface IRoleService {
    RoleDTO saveRole(RoleDTO roleDTO);
    void addRoleToUser(String username, String roleName);
}

package io.smartiq.springfakemail.service;

import io.smartiq.springfakemail.dto.RoleDTO;

public interface IRoleService {
    RoleDTO saveRole(RoleDTO roleDTO);

    void addRoleToUser(String username, String roleName);
}

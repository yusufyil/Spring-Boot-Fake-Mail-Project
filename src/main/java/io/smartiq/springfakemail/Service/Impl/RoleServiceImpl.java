package io.smartiq.springfakemail.Service.Impl;

import io.smartiq.springfakemail.DTO.RoleDTO;
import io.smartiq.springfakemail.Model.Role;
import io.smartiq.springfakemail.Model.User;
import io.smartiq.springfakemail.Repository.IRole;
import io.smartiq.springfakemail.Repository.IUser;
import io.smartiq.springfakemail.Service.IRoleService;
import io.smartiq.springfakemail.Util.MappingHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements IRoleService {

    private final IRole iRole;
    private final IUser iUser;
    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        Role role = MappingHelper.map(roleDTO, Role.class);
        Role result = iRole.save(role);
        log.info("{} role has been added to database.", role.getName());
        return MappingHelper.map(result, RoleDTO.class);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = iUser.findByUsername(username);
        Role role = iRole.findByName(roleName);
        log.info("{} role has been added to {} {}.", role.getName(), user.getName(), user.getSurname());
        user.getRoles().add(role);
    }
}

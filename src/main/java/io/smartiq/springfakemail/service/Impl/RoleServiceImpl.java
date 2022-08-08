package io.smartiq.springfakemail.service.Impl;

import io.smartiq.springfakemail.dto.RoleDTO;
import io.smartiq.springfakemail.exception.Role.RoleNameAlreadyAddedException;
import io.smartiq.springfakemail.exception.Role.RoleNotFoundException;
import io.smartiq.springfakemail.exception.User.UserNotFoundException;
import io.smartiq.springfakemail.model.Role;
import io.smartiq.springfakemail.model.User;
import io.smartiq.springfakemail.repository.RoleRepository;
import io.smartiq.springfakemail.repository.UserRepository;
import io.smartiq.springfakemail.service.IRoleService;
import io.smartiq.springfakemail.util.MappingHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        Role role = MappingHelper.map(roleDTO, Role.class);
        List<Role> listOfAllRoles = roleRepository.findAll();
        for (Role temp : listOfAllRoles) {
            if (temp.getName().equals(roleDTO.getName())) {
                String message = roleDTO.getName() + " is already added.";
                log.error(message);
                throw new RoleNameAlreadyAddedException(message);
            }
        }
        Role result = roleRepository.save(role);
        log.info("{} role has been added to database.", role.getName());
        return MappingHelper.map(result, RoleDTO.class);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        if (user == null) {
            throw new UserNotFoundException("There is no user in database with " + username +
                    " username");
        } else if (role == null) {
            throw new RoleNotFoundException("There is no role in database named " + roleName);
        } else {
            List<Role> listOfAllRoles = (List<Role>) user.getRoles();
            for (Role temp : listOfAllRoles) {
                if (temp.getName().equals(roleName)) {
                    String message = roleName + " is already added to given users role list.";
                    log.error(message);
                    throw new RoleNameAlreadyAddedException(message);
                }
            }
        }
        log.info("{} role has been added to {} {}.", role.getName(), user.getName(), user.getSurname());
        user.getRoles().add(role);
    }
}

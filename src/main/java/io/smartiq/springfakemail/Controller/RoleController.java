package io.smartiq.springfakemail.Controller;

import io.smartiq.springfakemail.DTO.RoleDTO;
import io.smartiq.springfakemail.Model.RoleToUserForm;
import io.smartiq.springfakemail.Service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final IRoleService iRoleService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public RoleDTO saveRole(@RequestBody RoleDTO roleDTO) {
        return iRoleService.saveRole(roleDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addtouser")
    public void addRoleToUser(@RequestBody RoleToUserForm form) {
        iRoleService.addRoleToUser(form.getUsername(), form.getRoleName());
    }
}

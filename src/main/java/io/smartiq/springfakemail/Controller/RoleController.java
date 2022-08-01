package io.smartiq.springfakemail.Controller;

import io.smartiq.springfakemail.DTO.RoleDTO;
import io.smartiq.springfakemail.Model.RoleToUserForm;
import io.smartiq.springfakemail.Service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final IRoleService iRoleService;

    @Operation(summary = "Save a new role to database.")
    @PostMapping()
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO roleDTO) {
        return new ResponseEntity<>(iRoleService.saveRole(roleDTO), CREATED);
    }

    @Operation(summary = "Assign a new role to a user using an already saved role.")
    @PostMapping("/addtouser")
    public ResponseEntity addRoleToUser(@RequestBody RoleToUserForm form) {
        return new ResponseEntity(OK);
    }
}

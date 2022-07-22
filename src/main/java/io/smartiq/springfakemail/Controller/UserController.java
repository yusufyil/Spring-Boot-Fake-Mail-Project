package io.smartiq.springfakemail.Controller;

import io.smartiq.springfakemail.DTO.RoleDTO;
import io.smartiq.springfakemail.DTO.UserDTO;
import io.smartiq.springfakemail.Service.IUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.findAll();
    }
    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.findOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/role")
    public RoleDTO saveRole(@RequestBody RoleDTO roleDTO){
        System.out.println(roleDTO);
        return userService.saveRole(roleDTO);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/role/addtouser")
    public void addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
    }
}
@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}

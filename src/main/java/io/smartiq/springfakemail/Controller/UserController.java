package io.smartiq.springfakemail.Controller;

import io.smartiq.springfakemail.DTO.UserDTO;
import io.smartiq.springfakemail.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    /*
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{userId}")
    public UserDTO getUser(@PathVariable("{userId}") Long userId){
    }
     */
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
}

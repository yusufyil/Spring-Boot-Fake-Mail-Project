package io.smartiq.springfakemail.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smartiq.springfakemail.DTO.RoleDTO;
import io.smartiq.springfakemail.DTO.UserDTO;
import io.smartiq.springfakemail.Model.Role;
import io.smartiq.springfakemail.Model.User;
import io.smartiq.springfakemail.Repository.IUser;
import io.smartiq.springfakemail.Service.IUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    private final IUser iUser;
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = iUser.findByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer("SmartIQ")
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                response.setHeader("Error!", "authorization filter does not allow this request");
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error message", "authorization filter does not allow this request");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
                exception.printStackTrace();
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}

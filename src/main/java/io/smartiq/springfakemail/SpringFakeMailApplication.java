package io.smartiq.springfakemail;

import io.smartiq.springfakemail.DTO.RoleDTO;
import io.smartiq.springfakemail.DTO.UserDTO;
import io.smartiq.springfakemail.Service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringFakeMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFakeMailApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(IUserService userService){
        return args -> {
            userService.save(new UserDTO(null, "Yusuf", "Yıldırım", "yusufyil@smart"
            ,"passpass", null));
            userService.save(new UserDTO(null, "Mustafa", "Yıldırım", "musmus@orion"
                    ,"password", null));
            userService.save(new UserDTO(null, "Ahmet", "Can", "ahmet@can"
                    ,"sifresifsif", null));

            userService.saveRole(new RoleDTO(null, "ROLE_ADMIN"));
            userService.saveRole(new RoleDTO(null, "ROLE_USER"));
            userService.saveRole(new RoleDTO(null, "ROLE_MANAGER"));

            userService.addRoleToUser("yusufyil@smart","ROLE_ADMIN");
            userService.addRoleToUser("musmus@orion","ROLE_USER");
            userService.addRoleToUser("ahmet@can", "ROLE_MANAGER");
            userService.addRoleToUser("musmus@orion","ROLE_MANAGER");

        };
    }
}

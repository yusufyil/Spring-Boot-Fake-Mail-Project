package io.smartiq.springfakemail;

import io.smartiq.springfakemail.DTO.RoleDTO;
import io.smartiq.springfakemail.DTO.UserDTO;
import io.smartiq.springfakemail.Service.IUserService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
public class SpringFakeMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFakeMailApplication.class, args);
    }
    //localhost:8080/swagger-ui address is host of documentation.

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner commandLineRunner(IUserService userService){
        return args -> {
            userService.save(new UserDTO(null, "Yusuf", "Yıldırım", "yusufyil@smart"
            ,"passpass", true, null));
            userService.save(new UserDTO(null, "Mustafa", "Yıldırım", "musmus@orion"
                    ,"password", true, null));
            userService.save(new UserDTO(null, "Ahmet", "Can", "ahmet@can"
                    ,"sifresifsif", true,null));

            userService.saveRole(new RoleDTO(null, "ROLE_ADMIN", true));
            userService.saveRole(new RoleDTO(null, "ROLE_USER", true));
            userService.saveRole(new RoleDTO(null, "ROLE_MANAGER", true));

            userService.addRoleToUser("yusufyil@smart","ROLE_ADMIN");
            userService.addRoleToUser("yusufyil@smart","ROLE_USER");
            userService.addRoleToUser("musmus@orion","ROLE_USER");
            userService.addRoleToUser("ahmet@can", "ROLE_MANAGER");
            userService.addRoleToUser("musmus@orion","ROLE_MANAGER");

        };
    }
    @Bean
    public OpenAPI customOpenApi(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version){
        return new OpenAPI()
                .info(new Info()
                        .title("SmartIQ Task Api")
                        .version(version)
                        .description(description)
                        .license(new License().name("SmartIQ Licence")));
    }
}

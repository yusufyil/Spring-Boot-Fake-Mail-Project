package io.smartiq.springfakemail;

import io.smartiq.springfakemail.DTO.RoleDTO;
import io.smartiq.springfakemail.DTO.UserDTO;
import io.smartiq.springfakemail.Service.IRoleService;
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
    //alternative url -> http://localhost:8080/swagger-ui/index.html#/
    //docker run -p 6379:6379 --name redisserver -d redis
    //bin/zookeeper-server-start.sh config/zookeeper.properties
    //bin/kafka-server-start.sh config/server.properties
    //bin/kafka-console-consumer.sh --topic myTopic --from-beginning --bootstrap-server localhost:9092
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(IUserService userService, IRoleService roleService) {
        return args -> {
            userService.save(new UserDTO(null, "Yusuf", "Yıldırım", "yusufyil@smart"
                    , "passpass", true, null));
            userService.save(new UserDTO(null, "Mustafa", "Yıldırım", "musmus@orion"
                    , "password", true, null));
            userService.save(new UserDTO(null, "Ahmet", "Can", "ahmet@can"
                    , "sifresifsif", true, null));

            roleService.saveRole(new RoleDTO(null, "ROLE_ADMIN", true));
            roleService.saveRole(new RoleDTO(null, "ROLE_USER", true));
            roleService.saveRole(new RoleDTO(null, "ROLE_MANAGER", true));

            roleService.addRoleToUser("yusufyil@smart", "ROLE_ADMIN");
            roleService.addRoleToUser("yusufyil@smart", "ROLE_USER");
            roleService.addRoleToUser("musmus@orion", "ROLE_USER");
            roleService.addRoleToUser("ahmet@can", "ROLE_MANAGER");
            roleService.addRoleToUser("musmus@orion", "ROLE_MANAGER");

        };
    }

    @Bean
    public OpenAPI customOpenApi(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version) {
        return new OpenAPI()
                .info(new Info()
                        .title("SmartIQ Task Api")
                        .version(version)
                        .description(description)
                        .license(new License().name("SmartIQ Licence")));
    }
}

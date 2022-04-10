package me.sekayasin.lab1;

import me.sekayasin.lab1.domain.Role;
import me.sekayasin.lab1.domain.dto.UserNameDto;
import me.sekayasin.lab1.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Lab1Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.save(new UserNameDto("sekayasin", "pass123"));
            userService.save(new UserNameDto("maha", "pass123"));
            userService.save(new UserNameDto("badi", "pass123"));

            userService.addRoleToUser("sekayasin", "ROLE_ADMIN");
            userService.addRoleToUser("sekayasin", "ROLE_USER");
            userService.addRoleToUser("badi", "ROLE_USER");
            userService.addRoleToUser("maha", "ROLE_USER");

        };
    }

}

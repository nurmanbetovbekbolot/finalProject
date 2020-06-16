package kg.itacademy.gsg.bootstrap;

import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.entities.UserRole;
import kg.itacademy.gsg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User admin = User.builder()
                .email("admin@mail.ru")
                .firstName("Admin")
                .lastName("Admin")
                .password(passwordEncoder.encode("123"))
                .roles(Arrays.asList(new UserRole("ROLE_ADMIN")))
                .build();
        userRepository.save(admin);
    }
}


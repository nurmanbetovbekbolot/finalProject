package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.models.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(Long id, User user);

    User saveUser(UserRegistrationDto UserRegistrationDto);

    void deleteUserById(Long id);

    User findByEmail(String email);

}

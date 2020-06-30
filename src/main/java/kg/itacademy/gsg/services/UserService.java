package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(UserModel userModel);

    User saveUser(UserModel userModel);

    void deleteUserById(Long id);

    User findByEmail(String email);

    Page<UserModel> findAll(Pageable pageable);

    List<UserModel> getByRole(String name);

}
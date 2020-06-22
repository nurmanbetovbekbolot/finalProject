package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.User;
import kg.itacademy.gsg.entities.UserRole;
import kg.itacademy.gsg.exceptions.RecordNotFoundException;
import kg.itacademy.gsg.models.UserModel;
import kg.itacademy.gsg.repositories.UserRepository;
import kg.itacademy.gsg.repositories.UserRoleRepository;
import kg.itacademy.gsg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAllUsersWithPagination(pageable);
    }

    @Override
    public List<User> getByRole(String name) {
        return userRepository.getByRole(name);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    @Override
    public User updateUser(UserModel userModel) {
        return userRepository.findById(userModel.getId())
                .map(newUser -> {
                    newUser.setFirstName(userModel.getFirstName());
                    newUser.setLastName(userModel.getLastName());
                    if(userModel.getPassword() != null)
                        newUser.setPassword(passwordEncoder.encode(userModel.getPassword()));
                    newUser.setEmail(userModel.getEmail());
                    return userRepository.save(newUser);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("User not found with id:" + userModel.getId()));
    }

    @Override
    public User saveUser(UserModel userModel){
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        if(userModel.getRole().getRoleName().equals("ROLE_MANAGER") || userModel.getRole().getRoleName().equals("ROLE_USER")){
            user.setRole(userModel.getRole());
        }
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRole()));
    }


    private List<GrantedAuthority> mapRolesToAuthorities(UserRole role) {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role.getRoleName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(auth);
        return authorities;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}

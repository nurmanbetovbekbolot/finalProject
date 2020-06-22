package kg.itacademy.gsg.models;

import kg.itacademy.gsg.entities.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModel {
    Long id;
    String email;
    String password;
    String firstName;
    String lastName;
    UserRole role;
}

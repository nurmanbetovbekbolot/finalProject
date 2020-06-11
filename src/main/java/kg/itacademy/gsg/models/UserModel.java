package kg.itacademy.gsg.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModel {
    Long id;
    String login;
    String password;
    String firstName;
    String lastName;
    String phoneNumber;
    Date registrationDate;
}

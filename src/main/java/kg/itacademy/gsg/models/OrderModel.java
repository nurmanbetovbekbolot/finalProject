package kg.itacademy.gsg.models;

import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderModel {
    Long id;
    String title;
    User clientId;
    User managerId;
    Package packageId;
    Date createdDate;
}

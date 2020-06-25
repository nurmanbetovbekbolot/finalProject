package kg.itacademy.gsg.models;

import kg.itacademy.gsg.entities.Order;
import kg.itacademy.gsg.entities.Task;
import kg.itacademy.gsg.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientTasksModel {
    Long id;
    User client;
    User manager;
    Order order;
    Task task;
}

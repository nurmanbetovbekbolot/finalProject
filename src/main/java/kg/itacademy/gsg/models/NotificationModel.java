package kg.itacademy.gsg.models;

import kg.itacademy.gsg.entities.ClientTasks;
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
public class NotificationModel {
    Long id;
    String message;
    ClientTasks clientTask;
    Date createdDate;
    Boolean isOpen;
}

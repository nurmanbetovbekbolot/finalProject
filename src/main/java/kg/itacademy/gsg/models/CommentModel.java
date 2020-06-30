package kg.itacademy.gsg.models;

import kg.itacademy.gsg.entities.ClientTasks;
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
public class CommentModel {
    Long id;
    String commentText;
    User user;
    ClientTasks clientTask;
    Date createdDate;
}

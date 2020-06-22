package kg.itacademy.gsg.models;

import kg.itacademy.gsg.entities.Category;
import kg.itacademy.gsg.enums.Status;
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
public class TaskModel {
    Long id;
    String title;
    String description;
    Status status;
    Category categoryId;
    Date createdDate;


}

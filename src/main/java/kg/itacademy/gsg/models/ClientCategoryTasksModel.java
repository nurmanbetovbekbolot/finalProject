package kg.itacademy.gsg.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ClientCategoryTasksModel {
    Long id;
    String title;
    List<TaskModel> taskModels;
}

package kg.itacademy.gsg.models;

import kg.itacademy.gsg.entities.ClientTasks;
import kg.itacademy.gsg.entities.Task;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaFileModel {
    Long id;
    String title;
    byte[] file;
    ClientTasks clientTasks;
    Date createdDate;
}

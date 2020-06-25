package kg.itacademy.gsg.models;

import kg.itacademy.gsg.entities.Package;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryModel {
    Long id;
    String title;
    Long packageId;
}

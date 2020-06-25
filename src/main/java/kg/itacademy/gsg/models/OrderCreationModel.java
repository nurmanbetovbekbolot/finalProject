package kg.itacademy.gsg.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationModel {
    Long id;
    String title;
    Long clientId;
    Long managerId;
    Long packageId;
}

package kg.itacademy.gsg.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "gsg_categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "title")
    String title;

    @ManyToOne
    @JoinColumn(name = "package_id")
    Package packageId;

    public Category(String title, Package packageId) {
        this.title = title;
        this.packageId = packageId;
    }
}

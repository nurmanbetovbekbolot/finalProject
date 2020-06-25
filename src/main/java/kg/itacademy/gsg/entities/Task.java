package kg.itacademy.gsg.entities;

import kg.itacademy.gsg.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "gsg_tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "description",columnDefinition = "text")
    String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    Category categoryId;

    @CreationTimestamp
    @Column(name = "created_date")
    Date createdDate;

    public Task(String title, String description, Status status, Category categoryId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
    }
}

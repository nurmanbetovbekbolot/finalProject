package kg.itacademy.gsg.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "gsg_notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "message")
    String message;

    @ManyToOne
    @JoinColumn(name = "client_task")
    ClientTasks clientTask;

    @CreationTimestamp
    @Column(name = "created_date")
    Date createdDate;

    @Column(name = "is_open")
    Boolean isOpen;
}
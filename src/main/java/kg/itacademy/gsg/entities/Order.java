package kg.itacademy.gsg.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "gsg_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "title")
    String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    User clientId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    User managerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "package_id")
    Package packageId;

    @CreationTimestamp
    @Column(name = "created_date")
    Date createdDate;
}

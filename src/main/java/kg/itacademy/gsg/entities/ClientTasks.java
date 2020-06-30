package kg.itacademy.gsg.entities;

import kg.itacademy.gsg.enums.Status;
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
@Table(name = "gsg_client_tasks")
public class ClientTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    User client;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    User manager;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "task_id")
    Task task;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_manager")
    Status statusManager;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_client")
    Status statusClient;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "clients_comment",
//            joinColumns = {@JoinColumn(name = "client_id")},
//            inverseJoinColumns = {@JoinColumn(name = "comment_id")})
//    List<Comment> comments;

    public ClientTasks(User client, User manager, Order order, Task task, Status statusManager) {
        this.client = client;
        this.manager = manager;
        this.order = order;
        this.task = task;
        this.statusManager = statusManager;
    }

    public ClientTasks(User client, User manager, Order order, Task task, Status statusManager, Status statusClient) {
        this.client = client;
        this.manager = manager;
        this.order = order;
        this.task = task;
        this.statusManager = statusManager;
        this.statusClient = statusClient;
    }
}
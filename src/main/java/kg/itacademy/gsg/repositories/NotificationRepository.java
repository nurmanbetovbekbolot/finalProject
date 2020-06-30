package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.Notification;
import kg.itacademy.gsg.models.NotificationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query("select new kg.itacademy.gsg.models.NotificationModel(n.id,n.message,n.clientTask,n.createdDate,n.isOpen) FROM Notification n join ClientTasks ct on ct.id = n.clientTask.id where ct.client.id = :id")
    Page<NotificationModel> getAllNotificationsByClientId(Long id, Pageable pageable);
}

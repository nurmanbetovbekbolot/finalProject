package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Notification;
import kg.itacademy.gsg.models.NotificationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotifications();

    Notification getNotificationById(Long id);

    void updateNotification(Long id, NotificationModel notificationModel);

    Notification saveNotification(NotificationModel notificationModel);

    Notification saveNotification(Notification notification);

    void deleteNotificationById(Long id);

    Page<NotificationModel> getAllNotificationsByClientId(Long id, Pageable pageable);

    Notification isOpened(Long id);
}

package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllNotifications();

    Notification getNotificationById(Long id);

    void updateNotification(Long id, Notification notification);

    void saveNotification(Notification notification);

    void deleteNotificationById(Long id);
}

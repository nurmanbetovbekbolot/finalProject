package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.Notification;
import kg.itacademy.gsg.repositories.NotificationRepository;
import kg.itacademy.gsg.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        return notification.orElse(new Notification());
    }

    @Override
    public void updateNotification(Long id, Notification notification) {

    }

    @Override
    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void deleteNotificationById(Long id) {
        notificationRepository.deleteById(id);
    }
}

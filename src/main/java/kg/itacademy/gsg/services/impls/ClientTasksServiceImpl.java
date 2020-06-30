package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.*;
import kg.itacademy.gsg.enums.Status;
import kg.itacademy.gsg.exceptions.RecordNotFoundException;
import kg.itacademy.gsg.models.ClientTasksModel;
import kg.itacademy.gsg.models.NotificationModel;
import kg.itacademy.gsg.models.TaskModel;
import kg.itacademy.gsg.repositories.ClientTasksRepository;
import kg.itacademy.gsg.services.ClientTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientTasksServiceImpl implements ClientTasksService {

    @Autowired
    ClientTasksRepository clientTasksRepository;

    @Autowired
    NotificationServiceImpl notificationService;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    TaskServiceImpl taskService;

    @Override
    public List<ClientTasksModel> getAll() {
        return clientTasksRepository.findAllClientTasks();
    }

    @Override
    public ClientTasks getById(Long id) {
        Optional<ClientTasks> clientTasks = clientTasksRepository.findById(id);
        return clientTasks.orElse(new ClientTasks());
    }

    @Override
    public ClientTasks updateClientTask(ClientTasksModel clientTasksModel) {
        return clientTasksRepository.findById(clientTasksModel.getId())
                .map(newClientTaskModel -> {
                    newClientTaskModel.setStatusClient(clientTasksModel.getStatusClient());
                    newClientTaskModel.setStatusManager(clientTasksModel.getStatusManager());
                    return clientTasksRepository.save(newClientTaskModel);
                })
                .orElseThrow(() -> new RecordNotFoundException("Task not found with id:" + clientTasksModel.getId()));
    }


    @Override
    public ClientTasks save(ClientTasksModel clientTasksModel) {
        ClientTasks clientTasks = new ClientTasks();
        clientTasks.setTask(clientTasksModel.getTask());
        clientTasks.setClient(clientTasksModel.getClient());
        clientTasks.setOrder(clientTasksModel.getOrder());
        clientTasks.setManager(clientTasksModel.getManager());
        clientTasks.setStatusClient(Status.TODO);
        return clientTasksRepository.save(clientTasks);
    }

    @Override
    public ClientTasks save(ClientTasks clientTasks) {
        return clientTasksRepository.save(clientTasks);
    }

    @Override
    public Page<ClientTasksModel> findAllClientTasksByOrderId(Long id, Pageable pageable) {
        return clientTasksRepository.findAllClientTasksByOrder(id, pageable);
    }

    @Override
    public Page<ClientTasksModel> findAllClientTasksByStatus(Status status, Pageable pageable) {
        return clientTasksRepository.findAllClientTasksByStatus(status, pageable);
    }

    @Override
    public ClientTasks changeClientTasksStatus(Long id, Status status, String userRole) {
        return clientTasksRepository.findById(id)
                .map(newClientTaskModel -> {
                    if (userRole.equals("ROLE_MANAGER") || userRole.equals("ROLE_ADMIN")) {
                        newClientTaskModel.setStatusManager(status);
                        if (status.equals(Status.DONE)) {
                            Notification notification = new Notification();
                            notification.setMessage("Ваша задача " + newClientTaskModel.getTask().getTitle() + " выполнена");
                            notification.setIsOpen(Boolean.FALSE);
                            notification.setClientTask(newClientTaskModel);
                            notificationService.saveNotification(notification);
                        }

                    } else {
                        newClientTaskModel.setStatusClient(status);
                        if (newClientTaskModel.getStatusClient().equals(Status.DECLINED)) {
                            newClientTaskModel.setStatusManager(Status.INPROGRESS);
                        }
                    }
                    return clientTasksRepository.save(newClientTaskModel);
                })
                .orElseThrow(() -> new RecordNotFoundException("Task not found with id:" + id));
    }

    @Override
    public ClientTasksModel findClientTaskById(Long id) {
        return clientTasksRepository.findClientTaskById(id);
    }

//    @Override
//    public Page<ClientTasksModel> findAllClientTasksByStatus(Status status, Pageable pageable) {
//        return clientTasksRepository.findAllClientTasksByStatus(status,pageable);
//    }

//    @Override
//    public Page<ClientTasksModel> findAllClientTasksByClientId(Long id, Pageable pageable) {
//        return clientTasksRepository.findAllClientTasksByClientId(id, pageable);
//    }

    @Override
    public void deleteById(Long id) {
        clientTasksRepository.deleteById(id);
    }

    @Override
    public void deleteClientTasksByOrder(Long id) {
        clientTasksRepository.deleteClientTasksByOrder(id);
    }

    @Override
    public ClientTasks saveClientTaskInOrder(Long orderId, TaskModel taskModel) {
        Order order = orderService.getOrderById(orderId);
        Task task = taskService.saveTask(taskModel);
        ClientTasks clientTasks = new ClientTasks();
        clientTasks.setTask(task);
        clientTasks.setClient(order.getClientId());
        clientTasks.setManager(order.getManagerId());
        clientTasks.setStatusManager(Status.TODO);
        clientTasks.setOrder(order);
        return clientTasksRepository.save(clientTasks);
    }
}

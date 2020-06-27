package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.ClientTasks;
import kg.itacademy.gsg.enums.Status;
import kg.itacademy.gsg.models.ClientTasksModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientTasksService {
    List<ClientTasksModel> getAll();

    ClientTasks getById(Long id);

    ClientTasks updateClientTask(ClientTasksModel clientTasksModel);


    ClientTasks save(ClientTasksModel clientTasksModel);

    ClientTasks save(ClientTasks clientTasks);

//    Page<ClientTasksModel> findAllClientTasksByClientId(Long id, Pageable pageable);

//    Page<ClientTasksModel> findAllClientTasksByStatus(Status status, Pageable pageable);

    void deleteById(Long id);
}

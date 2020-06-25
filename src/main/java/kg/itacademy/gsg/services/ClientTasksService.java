package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.ClientTasks;
import kg.itacademy.gsg.models.ClientTasksModel;

import java.util.List;

public interface ClientTasksService {
    List<ClientTasksModel> getAll();

    ClientTasks getById(Long id);

    ClientTasks save(ClientTasksModel clientTasksModel);

    ClientTasks save(ClientTasks clientTasks);

    void deleteById(Long id);
}

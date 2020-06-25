package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.ClientTasks;
import kg.itacademy.gsg.models.ClientTasksModel;
import kg.itacademy.gsg.repositories.ClientTasksRepository;
import kg.itacademy.gsg.services.ClientTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientTasksServiceImpl implements ClientTasksService {

    @Autowired
    ClientTasksRepository clientTasksRepository;

    @Override
    public List<ClientTasksModel> getAll() {
        return clientTasksRepository.findAllClientTasksModels();
    }

    @Override
    public ClientTasks getById(Long id) {
        Optional<ClientTasks> clientTasks = clientTasksRepository.findById(id);
        return clientTasks.orElse(new ClientTasks());
    }

    @Override
    public ClientTasks save(ClientTasksModel clientTasksModel) {
        ClientTasks clientTasks = new ClientTasks();
        clientTasks.setTask(clientTasksModel.getTask());
        clientTasks.setClient(clientTasksModel.getClient());
        clientTasks.setOrder(clientTasksModel.getOrder());
        clientTasks.setClient(clientTasksModel.getManager());
        return clientTasksRepository.save(clientTasks);
    }

    @Override
    public ClientTasks save(ClientTasks clientTasks) {
        return clientTasksRepository.save(clientTasks);
    }

    @Override
    public void deleteById(Long id) {
        clientTasksRepository.deleteById(id);
    }
}

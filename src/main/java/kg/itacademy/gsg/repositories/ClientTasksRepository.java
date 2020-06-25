package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.ClientTasks;
import kg.itacademy.gsg.models.ClientTasksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientTasksRepository extends JpaRepository<ClientTasks, Long> {
    @Query("select new kg.itacademy.gsg.models.ClientTasksModel(c.id,c.client,c.manager,c.order,c.task) FROM ClientTasks c")
    List<ClientTasksModel> findAllClientTasksModels();
}

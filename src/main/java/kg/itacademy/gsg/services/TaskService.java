package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Task;
import kg.itacademy.gsg.models.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Page<TaskModel> findAll(Pageable pageable);

    Task getTaskById(Long id);

    Task updateTask(TaskModel taskModel);

    Task saveTask(TaskModel taskModel);

    void deleteTaskById(Long id);

    void deleteTaskByPackageId(Long id);

    void deleteTaskByCategoryId(Long id);
}

package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskById(Long id);

    void updateTask(Long id, Task task);

    void saveTask(Task task);

    void deleteTaskById(Long id);
}

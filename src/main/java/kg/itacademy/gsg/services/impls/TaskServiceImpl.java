package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.Task;
import kg.itacademy.gsg.enums.Status;
import kg.itacademy.gsg.exceptions.RecordNotFoundException;
import kg.itacademy.gsg.models.TaskModel;
import kg.itacademy.gsg.repositories.TaskRepository;
import kg.itacademy.gsg.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Page<TaskModel> findAll(Pageable pageable) {
        return taskRepository.findAllTasksWithPagination(pageable);
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(new Task());
    }

    @Override
    public Page<TaskModel> findAllByCategoryId(Long id, Pageable pageable) {
        return taskRepository.findAllByCategoryId(id, pageable);
    }

    @Override
    public List<TaskModel> findAllByCategoryId(Long id) {
        return taskRepository.findAllByCategoryId(id);
    }

    @Override
    public Task updateTask(TaskModel taskModel) {
        return taskRepository.findById(taskModel.getId())
                .map(newTask -> {
                    newTask.setTitle(taskModel.getTitle());
                    newTask.setDescription(taskModel.getDescription());
                    newTask.setStatus(taskModel.getStatus());
                    return taskRepository.save(newTask);
                })
                .orElseThrow(() -> new RecordNotFoundException("Task not found with id:" + taskModel.getId()));
    }

    @Override
    public Task saveTask(TaskModel taskModel) {
        Task task = new Task();
        task.setTitle(taskModel.getTitle());
        task.setDescription(taskModel.getDescription());
        task.setCategoryId(taskModel.getCategoryId());
        task.setStatus(Status.TODO);
        task.setCreatedDate(taskModel.getCreatedDate());
        return taskRepository.save(task);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);    }


    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteTaskByPackageId(Long id) {
        taskRepository.deleteTaskByPackageId(id);
    }

    @Override
    public void deleteTaskByCategoryId(Long id) {
        taskRepository.deleteTaskByCategoryId(id);
    }
}

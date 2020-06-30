package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.Task;
import kg.itacademy.gsg.models.TaskModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select new kg.itacademy.gsg.models.TaskModel(t.id,t.title,t.description,t.categoryId,t.createdDate) FROM Task t ORDER BY t.title ASC")
    Page<TaskModel> findAllTasksWithPagination(Pageable pageable);

    @Query("select new kg.itacademy.gsg.models.TaskModel(t.id,t.title,t.description,t.categoryId,t.createdDate) FROM Task t where t.categoryId.id = :id ORDER BY t.title ASC")
    Page<TaskModel> findAllByCategoryId(@Param("id") Long id, Pageable pageable);

    @Query("select new kg.itacademy.gsg.models.TaskModel(t.id,t.title,t.description,t.categoryId,t.createdDate) FROM Task t where t.categoryId.id = :id ORDER BY t.title ASC")
    List<TaskModel> findAllByCategoryId(@Param("id") Long id);

    @Query(value = "select gt.* from gsg_tasks gt WHERE gt.category_id = :id ORDER BY gt.title ASC", nativeQuery = true)
    List<Task> findAllTasksByCatId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "delete FROM gsg_tasks WHERE  category_id =(select id from gsg_categories where package_id=:package_id)", nativeQuery = true)
    void deleteTaskByPackageId(@Param("package_id") Long id);

    @Modifying
    @Transactional
    @Query(value = "delete FROM gsg_tasks WHERE  category_id =:category_id", nativeQuery = true)
    void deleteTaskByCategoryId(@Param("category_id") Long id);

    @Query("select new kg.itacademy.gsg.models.TaskModel(t.id,t.title,t.description,t.categoryId,t.createdDate) FROM Task t join ClientTasks ct on ct.task.id = t.id where ct.client.id = :clientId and ct.order.id = :orderId")
    List<TaskModel> getAllByClientAndOrder(Long clientId, Long orderId);

    @Query("select new kg.itacademy.gsg.models.TaskModel(t.id,t.title,t.description,t.categoryId,t.createdDate) FROM Task t join ClientTasks ct on ct.task.id = t.id where ct.client.id = :clientId and ct.order.id = :orderId")
    Page<TaskModel> getAllByClientAndOrder(Long clientId, Long orderId, Pageable pageable);
}
package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.Category;
import kg.itacademy.gsg.models.CategoryModel;
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
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select new kg.itacademy.gsg.models.CategoryModel(c.id,c.title,c.packageId.id) FROM Category c ORDER BY c.title ASC")
    Page<CategoryModel> findAllCategoriesWithPagination(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "delete FROM gsg_categories WHERE package_id = :package_id", nativeQuery = true)
    void deleteCategoryByPackageId(@Param("package_id")Long id);

    @Query("select new kg.itacademy.gsg.models.CategoryModel(c.id,c.title,c.packageId.id) FROM Category c where c.packageId.id = :id ORDER BY c.title ASC")
    Page<CategoryModel> findAllCategoriesByPackageId(Long id, Pageable pageable);

    @Query("select new kg.itacademy.gsg.models.CategoryModel(c.id,c.title,c.packageId.id) FROM Category c where c.packageId.id = :id ORDER BY c.title ASC")
    List<CategoryModel> findAllCategoriesByPackageId(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete FROM gsg_categories WHERE id = :id", nativeQuery = true)
    void deleteByCategoryId(@Param("id")Long id);

    @Query("select new kg.itacademy.gsg.models.CategoryModel(c.id,c.title,c.packageId.id) FROM Category c join Task t on t.categoryId.id = c.id join ClientTasks ct on ct.task.id = t.id where ct.client.id = :clientId and ct.order.id = :orderId ORDER BY c.title ASC")
    List<CategoryModel> getAllByOrderAndClient(Long clientId, Long orderId);
}

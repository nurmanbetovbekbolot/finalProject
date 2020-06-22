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

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select new kg.itacademy.gsg.models.CategoryModel(c.id,c.title,c.packageId) FROM Category c ORDER BY c.title ASC")
    Page<CategoryModel> findAllCategoriesWithPagination(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "delete FROM gsg_categories WHERE package_id = :package_id", nativeQuery = true)
    void deleteCategoryByPackageId(@Param("package_id")Long id);
}

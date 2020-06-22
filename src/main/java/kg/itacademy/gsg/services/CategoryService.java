package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Category;
import kg.itacademy.gsg.models.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Page<CategoryModel> findAll(Pageable pageable);

    Category updateCategory(CategoryModel categoryModel);

    Category saveCategory(CategoryModel categoryModel);

    void deleteCategoryById(Long categoryId);

    void deleteCategoryByPackageId(Long packageId);

}

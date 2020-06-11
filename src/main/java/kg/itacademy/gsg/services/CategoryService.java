package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    void updateCategory(Long id, Category category);

    Category saveCategory(Category category);

    void deleteCategoryById(Long categoryId);

}

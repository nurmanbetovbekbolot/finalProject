package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.Category;
import kg.itacademy.gsg.exceptions.RecordNotFoundException;
import kg.itacademy.gsg.models.CategoryModel;
import kg.itacademy.gsg.repositories.CategoryRepository;
import kg.itacademy.gsg.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TaskServiceImpl taskService;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(CategoryModel categoryModel) {
        Category category = new Category();
        category.setPackageId(categoryModel.getPackageId());
        category.setTitle(categoryModel.getTitle());
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(new Category());
    }

    @Override
    public Page<CategoryModel> findAll(Pageable pageable) {
        return categoryRepository.findAllCategoriesWithPagination(pageable);
    }

    @Override
    public Category updateCategory(CategoryModel categoryModel) {
        return categoryRepository.findById(categoryModel.getId())
                .map(newCategory -> {
                    newCategory.setPackageId(categoryModel.getPackageId());
                    newCategory.setTitle(categoryModel.getTitle());
                    return categoryRepository.save(newCategory);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Category not found with id:" + categoryModel.getId()));
    }

    @Override
    public void deleteCategoryById(Long id) {
        taskService.deleteTaskByCategoryId(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteCategoryByPackageId(Long packageId) {
        categoryRepository.deleteCategoryByPackageId(packageId);
    }
}

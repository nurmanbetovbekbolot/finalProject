package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

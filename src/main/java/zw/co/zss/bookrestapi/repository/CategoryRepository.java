package zw.co.zss.bookrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zss.bookrestapi.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitleOrId(String title, Long id);
}

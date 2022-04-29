package zw.co.zss.bookrestapi.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zss.bookrestapi.model.Category;
import zw.co.zss.bookrestapi.repository.CategoryRepository;
import zw.co.zss.bookrestapi.service.CategoryService;
import zw.co.zss.bookrestapi.utils.Constants;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public String createCategory(Category category) {

        Optional<Category> cat = this.repository
                .findByTitleOrId(category.getTitle(), category.getId());

        if (cat.isPresent()) {
            return Constants.STATUS_DUPLICATE;
        }
        this.repository.save(category);
        return Constants.STATUS_SUCCESS;
    }
    @Override
    public List<Category> findAllCategories() {
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}

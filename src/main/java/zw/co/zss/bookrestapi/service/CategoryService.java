package zw.co.zss.bookrestapi.service;

import zw.co.zss.bookrestapi.model.Category;

import java.util.List;

public interface CategoryService {

    String createCategory(Category category);

    List<Category> findAllCategories();
}

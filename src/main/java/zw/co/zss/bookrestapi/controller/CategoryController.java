package zw.co.zss.bookrestapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zss.bookrestapi.model.Category;
import zw.co.zss.bookrestapi.service.CategoryService;
import zw.co.zss.bookrestapi.utils.Constants;
import zw.co.zss.bookrestapi.utils.ValidationResponse;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Value("${narration.category.duplicate}")
    private String narrationDuplicateCategory;

    @Value("${narration.category.saved}")
    private String narrationCreatedCategory;

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        String cat = this.service.createCategory(category);

        if (cat.equals(Constants.STATUS_SUCCESS)) {
            return ResponseEntity.status(Constants.RESPONSE_CODE_CREATED)
                    .body(validationResponse(Boolean.TRUE, Constants.RESPONSE_CODE_CREATED,
                            narrationCreatedCategory));
        }
        return ResponseEntity.status(Constants.RESPONSE_CODE_BAD_REQUEST)
                .body(validationResponse(Boolean.FALSE, Constants.RESPONSE_CODE_BAD_REQUEST,
                        narrationDuplicateCategory));
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategories() {
        return ResponseEntity.ok(this.service.findAllCategories());
    }

    private ValidationResponse validationResponse(Boolean successful, Integer responseCode, String description) {
        return
                ValidationResponse.builder()
                        .successful(successful)
                        .description(description)
                        .responseCode(responseCode)
                        .build();
    }
}

package zw.co.zss.bookrestapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.zss.bookrestapi.model.Book;
import zw.co.zss.bookrestapi.service.BookRestService;
import zw.co.zss.bookrestapi.utils.Constants;
import zw.co.zss.bookrestapi.utils.ValidationResponse;

import java.util.List;

/**
 * @author Sanders
 */

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRestService service;

    @Value("${narration.book.saved}")
    private String narrationBookSaved;

    @Value("${narration.book.duplicate}")
    private String narrationDuplicateBook;

    @Value("${narration.book.notFound}")
    private String narrationNotFoundBook;

    @Value("${narration.book.notFoundBookInCategory}")
    private String narrationNotFoundBookInCategory;

    public BookController(BookRestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        String bookResponse = this.service.createBook(book);

        switch (bookResponse) {

            case Constants.STATUS_SUCCESS:
                return ResponseEntity.status(Constants.RESPONSE_CODE_CREATED)
                        .body(validationResponse(Boolean.TRUE, Constants.RESPONSE_CODE_CREATED,
                                narrationBookSaved));

            case Constants.STATUS_DUPLICATE:
                return ResponseEntity.status(Constants.RESPONSE_CODE_BAD_REQUEST)
                        .body(validationResponse(Boolean.FALSE, Constants.RESPONSE_CODE_BAD_REQUEST,
                                narrationDuplicateBook));
            default:
                return ResponseEntity.status(Constants.RESPONSE_CODE_NOT_FOUND)
                        .body(validationResponse(Boolean.FALSE, Constants.RESPONSE_CODE_NOT_FOUND,
                                narrationNotFoundBook));
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(this.service.findAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findBooksByCategory(@PathVariable Long id) {
        List<Book> bookList = this.service.findBooksByCategory(id);

        if (bookList.isEmpty()) {
            return ResponseEntity.status(Constants.RESPONSE_CODE_NOT_FOUND)
                    .body(validationResponse(Boolean.FALSE, Constants.RESPONSE_CODE_NOT_FOUND,
                            narrationNotFoundBookInCategory + " : " + id));
        }
        return ResponseEntity.ok(bookList);
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

package zw.co.zss.bookrestapi.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zss.bookrestapi.model.Book;
import zw.co.zss.bookrestapi.model.Category;
import zw.co.zss.bookrestapi.repository.BookRepository;
import zw.co.zss.bookrestapi.repository.CategoryRepository;
import zw.co.zss.bookrestapi.service.BookRestService;
import zw.co.zss.bookrestapi.utils.Constants;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookRestService {

    private final BookRepository repository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository repository,
                           CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String createBook(Book b) {

        Category categoryId = findCategory(b.getCategory().getId());
        Book bookTitle = findBookByTitle(b.getTitle());

        if (bookTitle != null) {
            return Constants.STATUS_DUPLICATE; //book already present.

        } else if (categoryId == null) {
            return Constants.STATUS_NOT_FOUND; //category not found

        } else {
            Book book = Book.builder()
                    .price(b.getPrice() * 100)
                    .category(categoryId)
                    .title(b.getTitle())
                    .description(b.getDescription())
                    .build();

            this.repository.save(book);
            return Constants.STATUS_SUCCESS;
        }
    }

    @Override
    public List<Book> findAllBooks() {

        List<Book> bookList = repository.findAll(Sort.by(Sort.Direction.ASC, "title"));

        for (Book book : bookList) {
            book.setPrice(book.getPrice() / 100);
        }
        return bookList;
    }

    @Override
    public List<Book> findBooksByCategory(Long id) {
       return this.repository.findBooksByCategoryId(id);
    }

    private Category findCategory(Long categoryId) {
        Optional<Category> category = this.categoryRepository.findById(categoryId);
        return category.orElse(null);
    }

    private Book findBookByTitle(String title) {
        Optional<Book> book = this.repository.findByTitle(title);
        return book.orElse(null);
    }
}

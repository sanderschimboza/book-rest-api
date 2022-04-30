package zw.co.zss.bookrestapi.service;

import zw.co.zss.bookrestapi.model.Book;

import java.util.List;

public interface BookRestService {

    String createBook(Book book);

    List<Book> findAllBooks();

    List<Book> findBooksByCategory(Long id);
}

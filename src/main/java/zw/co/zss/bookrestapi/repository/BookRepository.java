package zw.co.zss.bookrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zw.co.zss.bookrestapi.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    @Query(value = "Select * from book_db.book where category_id = :categoryId ", nativeQuery = true)
    List<Book> findBooksByCategoryId(Long categoryId);
}

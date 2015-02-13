package acme.sales.bookstore.domain.repositories;

import acme.sales.bookstore.domain.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author vmuravlev
 */

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByTitle(String title);

    @Query("from Book b where UPPER(b.title) LIKE '%' || UPPER(:title) || '%'")
    List<Book> findByTitleLike(@Param("title") String title);
}
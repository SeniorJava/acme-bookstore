package acme.sales.bookstore.domain.test;

import acme.sales.bookstore.domain.entities.Book;
import acme.sales.bookstore.domain.repositories.BookRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author vmuravlev
 */
@ContextConfiguration(locations = "/test-domain-context.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "txManager")
public class RepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private BookRepository bookRepository;

    @Test
    public void shouldSelectBooks() {
        Iterable<Book> books = bookRepository.findAll();
        assertNotNull(books, "All books");
    }

    @Test
    public void shouldInsertNewBook() {
        List<Book> books = bookRepository.findByTitleLike("tom");
        assertTrue(books.isEmpty(), "No books found");

        Book newBook = new Book();
        newBook.setTitle("Tom Sawyer");
        newBook.setAuthor("Mark Twain");
        newBook.setGenre("Children reading");
        newBook.setPrice(109.22);

        bookRepository.save(newBook);

        assertTrue(newBook.getId() > 0, "Book was saved");

        books = bookRepository.findByTitleLike("tom");
        assertEquals(books.size(), 1, "Qty of books");
    }
}
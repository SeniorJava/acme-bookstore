package acme.sales.bookstore.integration;

import acme.sales.bookstore.domain.entities.Book;
import acme.sales.bookstore.domain.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vmuravlev
 */
public class BookItemWriter implements ItemWriter<Book> {

    private BookRepository bookRepository;

    @Inject
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private Logger logger = LoggerFactory.getLogger(BookItemWriter.class);

    @Override
    @Transactional
    public void write(List<? extends Book> books) throws Exception {
        for (Book book : books) {
            String title = book.getTitle();

            List<Book> foundBooks = bookRepository.findByTitle(title);
            if (foundBooks.isEmpty()) {
                logger.info("Import book {} as new one", title);
                bookRepository.save(book);
            } else {
                logger.info("Update book {}", title);

                Book foundBook = foundBooks.get(0);
                foundBook.setAuthor(book.getAuthor());
                foundBook.setPrice(book.getPrice());
                foundBook.setGenre(book.getGenre());

                bookRepository.save(book);
            }
        }
    }
}

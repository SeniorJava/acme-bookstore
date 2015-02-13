package acme.sales.bookstore.domain.test;

import acme.sales.bookstore.domain.entities.*;
import acme.sales.bookstore.domain.repositories.BookRepository;
import acme.sales.bookstore.domain.repositories.ClientRepository;
import acme.sales.bookstore.domain.services.BookOrderService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static acme.sales.bookstore.domain.test.BookBuilder.aBook;
import static org.testng.Assert.*;

/**
 * @author vmuravlev
 */
@ContextConfiguration(locations = "/test-domain-context.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "txManager")
public class BookOrderServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private BookOrderService bookOrderService;

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private BookRepository bookRepository;

    @Test
    public void shouldCreateNewOrder() {
        Client client = aClient();

        BookOrder newOrder = bookOrderService.prepareOrder(client);

        assertNotNull(newOrder, "New order");
        assertEquals(newOrder.getStatus(), Status.NEW, "New order status");
        assertTrue(newOrder.getId() > 0, "Order saved");
        assertTrue(newOrder.getLines().isEmpty(), "No lines in new order");
        assertEquals(newOrder.getClient(), client, "Order client");
    }

    private Client aClient() {
        Client client = new Client();
        client.setFirstName("John");
        client.setLastName("Doe");

        clientRepository.save(client);
        return client;
    }

    @Test
    public void shouldCreateNewOrderLineWhenAddBookToOrder() {
        BookOrder bookOrder = bookOrderService.prepareOrder(aClient());

        Book book = aBook("War and peace")
                .author("Leo Tolstoy").genre("Prose").priced(200.22).build();
        bookRepository.save(book);

        bookOrderService.addBook(bookOrder, book, 2);

        assertEquals(bookOrder.getLines().size(), 1, "Lines qty");
        BookOrderLine newLine = bookOrder.getLines().iterator().next();
        assertTrue(newLine.getId() > 0, "Order line saved");
        assertEquals(newLine.getBook().getTitle(), "War and peace", "Book title");
    }
}
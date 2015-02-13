package acme.sales.bookstore.domain.test;

import acme.sales.bookstore.domain.entities.Book;
import acme.sales.bookstore.domain.entities.BookOrder;
import acme.sales.bookstore.domain.entities.BookOrderLine;
import acme.sales.bookstore.domain.entities.Client;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.testng.Assert.assertNotNull;

/**
 * @author vmuravlev
 */
@ContextConfiguration(locations = "/test-domain-context.xml")
@TransactionConfiguration(defaultRollback = true)
public class ValidDomainTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void shouldSelectBooks() {
        validateEntity(Book.class);
    }

    private void validateEntity(Class entityClass) {
        List books = entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList();
        assertNotNull(books, entityClass + " list");
    }

    @Test
    public void shouldSelectClients() {
        validateEntity(Client.class);
    }

    @Test
    public void shouldSelectOrders() {
        validateEntity(BookOrder.class);
    }

    @Test
    public void shouldSelectOrderLines() {
        validateEntity(BookOrderLine.class);
    }
}
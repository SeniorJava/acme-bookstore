package acme.sales.bookstore.domain.test;

import acme.sales.bookstore.domain.entities.Book;
import acme.sales.bookstore.domain.entities.BookOrderLine;
import acme.sales.bookstore.domain.repositories.BookRepository;
import acme.sales.bookstore.domain.services.BookOrderService;
import acme.sales.bookstore.domain.services.DashboardService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;

import static org.testng.Assert.assertEquals;

/**
 * @author vmuravlev
 */
@ContextConfiguration(locations = "/test-domain-context.xml")
@TransactionConfiguration(defaultRollback = true)
public class DashboardServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private DashboardService dashboardService;

    @Inject
    private BookOrderService bookOrderService;

    @Inject
    private BookRepository bookRepository;

    @Test
    public void shouldModifyStatsWhenNewOrderCreated() {
        Date today = new Date();
        int oldQty = dashboardService.getStats(today).getOrdersQty();

        Book anyBook = bookRepository.findAll().iterator().next();
        BookOrderLine orderLine = new BookOrderLine(anyBook, 2);
        bookOrderService.createOrder(Arrays.asList(orderLine));

        dashboardService.collectStats();

        int newQty = dashboardService.getStats(today).getOrdersQty();

        assertEquals(newQty, oldQty + 1, "Orders qty");
    }
}
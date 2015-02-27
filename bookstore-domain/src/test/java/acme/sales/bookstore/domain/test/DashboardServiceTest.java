package acme.sales.bookstore.domain.test;

import acme.sales.bookstore.domain.entities.Book;
import acme.sales.bookstore.domain.entities.BookOrderLine;
import acme.sales.bookstore.domain.repositories.BookRepository;
import acme.sales.bookstore.domain.services.BookOrderService;
import acme.sales.bookstore.domain.services.DashboardService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Arrays;

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

    @Inject
    private DataSource dataSource;

    @Test
    public void shouldModifyStatsWhenNewOrderCreated() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String qtyQuery = "select count(orders_qty) from dashboard_stats";
        int oldQty = jdbcTemplate.queryForObject(qtyQuery, Integer.class);

        Book anyBook = bookRepository.findAll().iterator().next();
        BookOrderLine orderLine = new BookOrderLine(anyBook, 2);
        bookOrderService.createOrder(Arrays.asList(orderLine));

        dashboardService.collectStats();

        int newQty = jdbcTemplate.queryForObject(qtyQuery, Integer.class);

        Assert.assertEquals(newQty, oldQty + 1, "Orders qty");
    }
}
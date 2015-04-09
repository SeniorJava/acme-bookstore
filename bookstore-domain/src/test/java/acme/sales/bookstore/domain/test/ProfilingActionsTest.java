package acme.sales.bookstore.domain.test;

import acme.sales.bookstore.domain.services.AdministrationException;
import acme.sales.bookstore.domain.services.DashboardService;
import acme.sales.bookstore.domain.services.ProfilingAspect;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author vmuravlev
 */
@ContextConfiguration(locations = "/test-domain-context.xml")
@TransactionConfiguration(defaultRollback = true)
public class ProfilingActionsTest extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private ProfilingAspect aspect;

    @Inject
    private DashboardService dashboardService;

    @Test
    public void shouldVerboseCollectStatistic() throws AdministrationException {
        aspect.reset();

        dashboardService.collectStats();
        dashboardService.getStats(new Date());

        Assert.assertEquals(aspect.getCallCount(), 2, "Call count");
    }
}
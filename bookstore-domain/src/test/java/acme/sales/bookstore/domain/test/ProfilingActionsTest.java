package acme.sales.bookstore.domain.test;

import acme.sales.bookstore.domain.entities.Client;
import acme.sales.bookstore.domain.services.AdministrationException;
import acme.sales.bookstore.domain.services.AdministrationService;
import acme.sales.bookstore.domain.services.ProfilingAspect;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * @author vmuravlev
 */
@ContextConfiguration(locations = "/test-domain-context.xml")
@TransactionConfiguration(defaultRollback = true)
public class ProfilingActionsTest extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private ProfilingAspect aspect;

    @Inject
    private AdministrationService administrationService;

    @Test
    public void shouldVerboseCreateNewUser() throws AdministrationException {
        aspect.reset();

        Client client = new Client();
        client.setFirstName("Fred");
        client.setLastName("Flinstone");
        client.setAddress("Jurassic Period");
        client.setPhone("111-33-22");

        administrationService.hasDuplicate(client);
        administrationService.createNewClient(client);

        Assert.assertEquals(aspect.getCallCount(), 2, "Call count");
    }
}
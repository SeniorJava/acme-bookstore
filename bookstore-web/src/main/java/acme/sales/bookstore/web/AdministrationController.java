package acme.sales.bookstore.web;

import acme.sales.bookstore.domain.entities.Client;
import acme.sales.bookstore.domain.entities.User;
import acme.sales.bookstore.domain.repositories.BookOrderRepository;
import acme.sales.bookstore.domain.repositories.BookRepository;
import acme.sales.bookstore.domain.repositories.UserRepository;
import acme.sales.bookstore.domain.services.AdministrationException;
import acme.sales.bookstore.domain.services.AdministrationService;
import acme.sales.bookstore.domain.services.DashboardService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vmuravlev
 */
@Controller
@Scope("request")
public class AdministrationController {

    @Inject
    private AdministrationService administrationService;

    @Inject
    private DashboardService dashboardService;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PurchaseController purchaseController;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private BookOrderRepository orderRepository;

    @RequestMapping("/signUp.action")
    public ModelAndView prepareNewClient() {
        return new ModelAndView("userProfile", "client", new Client());
    }

    @RequestMapping("/createNewClient.action")
    public ModelAndView createNewClient(Client client) {
        List<String> errors = validate(client);

        if (!errors.isEmpty()) {
            return new ModelAndView("userProfile", "errors", errors);
        }

        try {
            administrationService.createNewClient(client);
            return new ModelAndView("login");
        } catch (AdministrationException e) {
            return new ModelAndView("userProfile", "errors", e.getMessage());
        }
    }

    @RequestMapping("/onLogon.action")
    public ModelAndView onLogon(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        if (user.getAuthorities().contains("ROLE_MANAGER")) {
            return showDashboard(new Date());
        } else {
            return purchaseController.selectBooks();
        }
    }

    @RequestMapping("/manager/showDashboard.action")
    public ModelAndView showDashboard(Date date) {
        Date statsDate = date == null ? new Date() : date;
        return new ModelAndView("dashboard", "stats", dashboardService.getStats(statsDate));
    }

    @RequestMapping("/manager/showAllBooks.action")
    public ModelAndView showBookList() {
        return new ModelAndView("bookList", "allBooks", bookRepository.findAll());
    }

    @RequestMapping("/manager/showAllOrders.action")
    public ModelAndView showOrderList() {
        return new ModelAndView("orderList", "orders", orderRepository.findAll());
    }

    private List<String> validate(Client client) {
        List<String> errors = new ArrayList<>();

        if (client.getFirstName() == null || "".equals(client.getFirstName())) {
            errors.add("First name is required");
        }

        if (client.getLastName() == null || "".equals(client.getLastName())) {
            errors.add("Last name is required");
        }
        return errors;
    }
}
package acme.sales.bookstore.web;

import acme.sales.bookstore.domain.entities.Book;
import acme.sales.bookstore.domain.entities.Client;
import acme.sales.bookstore.domain.repositories.BookRepository;
import acme.sales.bookstore.domain.repositories.ClientRepository;
import acme.sales.bookstore.domain.services.BookOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.security.Principal;
import java.util.Map;

/**
 * @author vmuravlev
 */
@Controller
@Scope("request")
@RequestMapping("/purchase")
public class PurchaseController {

    @Inject
    private Cart cart;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private BookOrderService orderService;

    @RequestMapping("/newPurchase.action")
    public ModelAndView clearCart(Principal principal) {
        cart.clear();
        return selectBooks(principal);
    }

    @RequestMapping(value = "/selectBooks.action", method = RequestMethod.GET)
    public ModelAndView selectBooks(Principal principal) {
        return selectBooksByGenre(StringUtils.EMPTY, principal);
    }

    @RequestMapping(value = "/selectBooksByGenre.action", method = RequestMethod.GET)
    public ModelAndView selectBooksByGenre(@RequestParam(required = true) String genre,
                                    Principal principal) {
        Iterable<Book> books;
        ModelAndView modelAndView = new ModelAndView("selectBooks");
        if (StringUtils.isEmpty(genre)) {
            books = bookRepository.findAll();
        } else {
            books = bookRepository.findByGenre(genre);
            modelAndView.addObject("selectedGenre", genre);
        }

        modelAndView.addObject("allBooks", books);
        addUserInfo(modelAndView, principal);
        modelAndView.addObject("genres", bookRepository.findAllGenres());
        return modelAndView;
    }

    private void addUserInfo(ModelAndView modelAndView, Principal principal) {
        Client loggedClient = clientRepository.findOneByFirstName(principal.getName());
        modelAndView.addObject("userInfo", String.format("%s %s",
                loggedClient.getFirstName(), loggedClient.getLastName()));
    }

    @RequestMapping(value = "/addToCart.action", method = RequestMethod.POST)
    public ModelAndView addToCart(@RequestParam Map<String, String> lines, Principal principal) {
        cart.addLines(lines);
        ModelAndView modelAndView = selectBooks(principal);
        modelAndView.addObject("message", "Books were successfully added");
        return modelAndView;
    }

    @RequestMapping(value = "/addBookToCart.action", method = RequestMethod.GET)
    public ModelAndView addToCart(int id, Principal principal) {
        cart.addBook(id);
        ModelAndView modelAndView = selectBooks(principal);
        modelAndView.addObject("message", "Book were successfully added");
        return modelAndView;
    }

    @RequestMapping(value = "/showCart.action")
    public ModelAndView showCart(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("showCart");
        addUserInfo(modelAndView, principal);
        return modelAndView;
    }

    @RequestMapping("/makePurchase.action")
    public ModelAndView makePurchase() {
        orderService.createOrder(cart.getLines());
        return showOrders();
    }

    @RequestMapping("/showOrders.action")
    public ModelAndView showOrders() {
        return new ModelAndView("purchaseList", "orders", orderService.getCurrentUserOrders());
    }

    @RequestMapping("/showOrder.action")
    public ModelAndView showOrder() {
        return new ModelAndView("purchaseDetails", "allClients", clientRepository.findAll());
    }
}
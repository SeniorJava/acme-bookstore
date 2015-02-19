package acme.sales.bookstore.web;

import acme.sales.bookstore.domain.repositories.BookRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author vmuravlev
 */
@Controller
@Scope("request")
public class BookstoreController {

    @Inject
    private Cart cart;

    @Inject
    private BookRepository bookRepository;

    @RequestMapping("/newPurchase")
    public ModelAndView newPurchase() {
        cart.clear();
        return selectBooks();
    }

    @RequestMapping(value = "/selectBooks", method = RequestMethod.GET)
    public ModelAndView selectBooks() {
        return new ModelAndView("selectBooks", "allBooks", bookRepository.findAll());
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public ModelAndView addToCart(@RequestParam Map<String, String> lines) {
        cart.addLines(lines);
        return selectBooks();
    }

    @RequestMapping(value = "/showCart")
    public String showCart() {
        return "showCart";
    }
}
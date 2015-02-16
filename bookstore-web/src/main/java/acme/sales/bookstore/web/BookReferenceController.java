package acme.sales.bookstore.web;

import acme.sales.bookstore.domain.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * @author vmuravlev
 */
@Controller
public class BookReferenceController {

    @Inject
    private BookRepository bookRepository;

    @RequestMapping(value = "/allBooks", method = RequestMethod.GET)
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }
}
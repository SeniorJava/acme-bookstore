package acme.sales.bookstore.web;

import acme.sales.bookstore.domain.entities.Book;
import acme.sales.bookstore.domain.entities.BookOrderLine;
import acme.sales.bookstore.domain.entities.Client;
import acme.sales.bookstore.domain.repositories.BookRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @author vmuravlev
 */
@Component("bookCart")
@Scope(value = "session")
public class CartImpl implements Cart {

    @Inject
    private BookRepository bookRepository;

    private List<BookOrderLine> lines;
    private Client client;

    public CartImpl() {
        this.lines = new ArrayList<>();
    }

    @Override
    public List<BookOrderLine> getLines() {
        return lines;
    }

    public void setLines(List<BookOrderLine> lines) {
        this.lines = lines;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void clear() {
        this.client = null;
        this.lines.clear();
    }

    @Override
    public void addLines(Map<String, String> lines) {
        for (Map.Entry<String, String> lineEntry : lines.entrySet()) {
            int bookID = parseInt(lineEntry.getKey());
            int qty = parseInt(lineEntry.getValue());
            if (qty <= 0) {
                continue;
            }

            Book book = bookRepository.findOne(bookID);
            this.lines.add(new BookOrderLine(book, qty));
        }
    }

    @Override
    public String getStatus() {
        int qty = 0;
        double total = 0.0;
        for (BookOrderLine line : lines) {
            qty += line.getQty();
            total += line.getQty() * line.getBook().getPrice();
        }

        return String.format("Books selected: %d, total price: %0.2f", qty, total);
    }
}
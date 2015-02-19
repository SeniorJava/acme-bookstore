package acme.sales.bookstore.web;

import acme.sales.bookstore.domain.entities.Book;
import acme.sales.bookstore.domain.entities.BookOrderLine;
import acme.sales.bookstore.domain.entities.Client;
import acme.sales.bookstore.domain.repositories.BookRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
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

    private Map<Integer, BookOrderLine> bookLines;
    private Client client;

    public CartImpl() {
        this.bookLines = new HashMap<>();
    }

    @Override
    public Iterable<BookOrderLine> getLines() {
        return bookLines.values();
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
        this.bookLines.clear();
    }

    @Override
    public void addLines(Map<String, String> lines) {
        for (Map.Entry<String, String> lineEntry : lines.entrySet()) {
            int bookID = parseInt(lineEntry.getKey());
            int qty = parseInt(lineEntry.getValue());

            if (qty <= 0) {
                continue;
            }

            if (bookLines.containsKey(bookID)) {
                BookOrderLine line = bookLines.get(bookID);
                line.setQty(line.getQty() + qty);
            } else {
                Book book = bookRepository.findOne(bookID);
                this.bookLines.put(bookID, new BookOrderLine(book, qty));
            }
        }
    }

    @Override
    public String getStatus() {
        int qty = 0;
        double total = 0.0;
        for (BookOrderLine line : bookLines.values()) {
            qty += line.getQty();
            total += line.getQty() * line.getBook().getPrice();
        }

        return String.format("Books selected: %d, total price: %.2f", qty, total);
    }
}
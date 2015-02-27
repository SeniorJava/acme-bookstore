package acme.sales.bookstore.web;

import acme.sales.bookstore.domain.entities.BookOrderLine;

import java.util.Collection;
import java.util.Map;

/**
 * @author vmuravlev
 */
public interface Cart {
    void clear();
    void addLines(Map<String, String> lines);
    Collection<BookOrderLine> getLines();
    String getStatus();
    int getBooksQty();
}
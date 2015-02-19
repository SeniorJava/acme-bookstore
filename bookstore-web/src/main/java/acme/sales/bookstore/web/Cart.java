package acme.sales.bookstore.web;

import acme.sales.bookstore.domain.entities.BookOrderLine;

import java.util.List;
import java.util.Map;

/**
 * @author vmuravlev
 */
public interface Cart {
    void clear();
    void addLines(Map<String, String> lines);
    List<BookOrderLine> getLines();
    String getStatus();
}